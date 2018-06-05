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
public class StabilityChart extends Plot2DPanel {

    private FileDatabase database;

    /**
     * Creates new form StabilityChart
     */
    public StabilityChart() {

        database = FileDatabase.getInstance();
        prepare();
        addLegend("SOUTH");

    }

    public void setSelectedAtom(double[][] selectedAtom) {

        getPlots().clear();

        prepare();

        addScatterPlot("Selected nuclei", Color.blue, selectedAtom);
    }

    public void prepare() {

        addScatterPlot("Unstable nuclei", Color.black, database.getAllXYNumbers());
        addScatterPlot("Stable nuclei", Color.red, database.getStableAtoms());

        setAxisLabel(1, "Number of nucleons");
        setAxisLabel(0, "Atomic Number");

        // add a title
        BaseLabel title = new BaseLabel("Stability Chart", Color.black, 0.5, 1.1);
        title.setFont(new Font("Courier", Font.BOLD, 30));
        addPlotable(title);

    }

}
