/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.ui.chart;

import visualizer.database.FileDatabase;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

/**
 *
 * @author Anytram
 */
public class MeltChart extends Plot2DPanel {

    private FileDatabase database;
    private double[][] selectedAtom;

    public MeltChart() {
            database = FileDatabase.getInstance();
        prepare();
        addLegend("SOUTH");
    }
    public void setSelectedAtom(double[][] selectedAtom) {
        this.selectedAtom = selectedAtom;

        getPlots().clear();

        prepare();
        
        addScatterPlot("Selected atom", Color.GREEN, selectedAtom);
    }

    public void prepare() {

        addScatterPlot("Gas", Color.red, database.getAtombyPhase("Gas"));
        addScatterPlot("Solid", Color.GRAY, database.getAtombyPhase("Solid"));
        addScatterPlot("Liquid", Color.blue, database.getAtombyPhase("Liquid"));
        
        

        setAxisLabel(1, "MeltTemperature");
        setAxisLabel(0, "Atomic Number");  
        
        BaseLabel title = new BaseLabel("Melt temperature dependency", Color.black, 0.5, 1.1);
        title.setFont(new Font("Courier", Font.BOLD, 30));
        addPlotable(title);

    }
}
