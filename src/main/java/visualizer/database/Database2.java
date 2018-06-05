/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.database;

import visualizer.model.AdvancedElement;

/**
 *
 * @author Anytram
 */
public interface Database2 {

    public AdvancedElement findByName(String name);

    public String[] getAllNames();

    public double[][] getAllXYNumbers();

    public double[][] getStableAtoms();

    public double[][] getSelectedAtom(String name);

}
