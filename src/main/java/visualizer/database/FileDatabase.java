/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.database;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import visualizer.model.AdvancedElement;

/**
 *
 * @author Anytram
 */
public class FileDatabase implements Database2 {

    private static FileDatabase instance;
    private Map<String, AdvancedElement> data;

    private FileDatabase(){
        try {
            this.data = new HashMap<>();
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
            AdvancedElement[] temp = mapper.readValue(new File(FileDatabase.class.getResource("/periodic-table.json").toURI()), AdvancedElement[].class);
            
            for (AdvancedElement advancedElement : temp) {
                data.put(advancedElement.getName(), advancedElement);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(FileDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static FileDatabase getInstance() {
        if (instance == null) {
            instance = new FileDatabase();
        }
        return instance;
    }

    @Override
    public AdvancedElement findByName(String name) {
        return data.get(name);
    }

    @Override
    public String[] getAllNames() {

        String[] names = data.keySet().toArray(new String[data.size()]);

        Arrays.sort(names);

        return names;
    }

    @Override
    public double[][] getAllXYNumbers() {

        double[][] atomicNumbers = new double[data.size()][2];

        int i = 0;
        for (AdvancedElement object : data.values()) {
            atomicNumbers[i][0] = object.getAtomicNumber();
            atomicNumbers[i][1] = object.getNeutronNumber();
            i++;
        }
        return atomicNumbers;
    }

    @Override
    public double[][] getStableAtoms() {
        int size = 0;
        for (AdvancedElement object : data.values()) {
            if (((double) object.getNeutronNumber() / object.getAtomicNumber()) < 1.4 && object.getAtomicNumber() < 83) {
                size++;
            }
        }

        double[][] stable = new double[size][2];
        int i = 0;
        for (AdvancedElement object : data.values()) {
            if (((double) object.getNeutronNumber() / object.getAtomicNumber()) < 1.4 && object.getAtomicNumber() < 83) {
                stable[i][0] = object.getAtomicNumber();
                stable[i][1] = object.getNeutronNumber();
                i++;
            }
        }
        return stable;
    }

    @Override
    public double[][] getSelectedAtom(String name) {
        double[][] select = new double[1][2];
        select[0][0] = findByName(name).getAtomicNumber();
        select[0][1] = findByName(name).getNeutronNumber();

        return select;

    }

    public double[][] getAtombyPhase(String phase) {

        List<AdvancedElement> results = data.values().stream().filter((e) -> e.getPhase().equalsIgnoreCase(phase)).collect(Collectors.toList());
        results.sort((o1, o2) -> {
            return (int) o1.getAtomicNumber() - (int) o2.getAtomicNumber();
        });

        double[][] coordinates = new double[results.size()][2];

        for (int i = 0; i < results.size(); i++) {
            coordinates[i][0] = results.get(i).getAtomicNumber();
            coordinates[i][1] = results.get(i).getMelt();
        }

        return coordinates;

    }
    
        public  List<AdvancedElement> getAllAndSortByAtomicNumber() {

        List<AdvancedElement> results = data.values().stream().sorted().collect(Collectors.toList());
//        results.sort((o1, o2) -> {
//            return (int) o1.getAtomicNumber() - (int) o2.getAtomicNumber();
//        });
//
//        double[][] coordinates = new double[results.size()][2];
//
//        for (int i = 0; i < results.size(); i++) {
//            coordinates[i][0] = results.get(i).getAtomicNumber();
//            coordinates[i][1] = results.get(i).getMelt();
//        }

        return results;

    }

}
