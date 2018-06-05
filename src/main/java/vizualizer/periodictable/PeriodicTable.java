/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vizualizer.periodictable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import javax.swing.GroupLayout;
import visualizer.database.FileDatabase;
import visualizer.model.AdvancedElement;

/**
 *
 * @author Anytram
 */
public class PeriodicTable extends javax.swing.JPanel {

    List<AdvancedElement> allAtoms;
    boolean drawInfo = false;

    /**
     * Creates new form PeriodicTable
     */
    private FileDatabase database;
    private AdvancedElement selectedAtom;
    private String summary;
    private int width = 64;
    private int height = 64;

    /**
     * Creates new form PeriodicPanel
     */
    public PeriodicTable() {
        initComponents();
        database = FileDatabase.getInstance();
        initComponents();

        allAtoms = database.getAllAndSortByAtomicNumber();
    }

    public void setSelectedAtom(String selectedAtom) {
        summary = database.findByName(selectedAtom).getSummary();
        drawInfo = true;
    }

    public void paintComponent(Graphics g) {

        AdvancedElement[][] period1 = new AdvancedElement[1][18];

        for (int i = 0; i < 2; i++) {
            period1[0][0] = allAtoms.get(i);
            period1[0][17] = allAtoms.get(i);
            drawAtom(g, i + (i * (17 * width - 1)), 0, allAtoms.get(i));
        }

        AdvancedElement[][] period2 = new AdvancedElement[1][18];

        for (int i = 2; i < 10; i++) {
            period2[0][0] = allAtoms.get(i);
            period2[0][1] = allAtoms.get(i);

            period2[0][10 + i / 2] = allAtoms.get(i);
            if (i > 3) {

                drawAtom(g, width * i + 8 * width, height, allAtoms.get(i));
            } else {
                drawAtom(g, width * i - 2 * width, height, allAtoms.get(i));
            }

        }

        AdvancedElement[][] period3 = new AdvancedElement[1][18];

        for (int i = 10; i < 18; i++) {
            period3[0][0] = allAtoms.get(i);
            period2[0][1] = allAtoms.get(i);

            period3[0][2 + i / 10] = allAtoms.get(i);

            if (i > 11) {

                drawAtom(g, width * i, height * 2, allAtoms.get(i));
            } else {
                drawAtom(g, width * i - 10 * width, height * 2, allAtoms.get(i));
            }

        }

        AdvancedElement[][] period4 = new AdvancedElement[1][18];

        for (int i = 18; i < 36; i++) {
            period4[0][i / 18] = allAtoms.get(i);
            drawAtom(g, width * i - 18 * width, 3 * height, allAtoms.get(i));
        }

        AdvancedElement[][] period5 = new AdvancedElement[1][18];

        for (int i = 36; i < 54; i++) {
            period5[0][i / 36] = allAtoms.get(i);
            drawAtom(g, width * i - 36 * width, 4 * height, allAtoms.get(i));
        }

        AdvancedElement[][] period6 = new AdvancedElement[1][18];

        for (int i = 54; i < 57; i++) {
            period6[0][i / 54] = allAtoms.get(i);
            drawAtom(g, width * i - 54 * width, 5 * height, allAtoms.get(i));
        }
        for (int i = 71; i < 86; i++) {
            period6[0][i / 71] = allAtoms.get(i);
            drawAtom(g, width * i - 68 * width, 5 * height, allAtoms.get(i));
        }

        AdvancedElement[][] period7 = new AdvancedElement[1][18];

        for (int i = 86; i < 89; i++) {
            period7[0][i / 86] = allAtoms.get(i);
            drawAtom(g, width * i - 86 * width, 6 * height, allAtoms.get(i));
        }
        for (int i = 103; i < 118; i++) {
            period7[0][i / 103] = allAtoms.get(i);
            drawAtom(g, width * i - 100 * width, 6 * height, allAtoms.get(i));
        }

        AdvancedElement[][] sideperiod1 = new AdvancedElement[1][18];

        for (int i = 57; i < 71; i++) {
            sideperiod1[0][i / 57] = allAtoms.get(i);
            drawAtom(g, width * i - 54 * width, 8 * height, allAtoms.get(i));
        }
        AdvancedElement[][] sideperiod2 = new AdvancedElement[1][18];

        for (int i = 89; i < 103; i++) {
            sideperiod2[0][i / 89] = allAtoms.get(i);
            drawAtom(g, width * i - 86 * width, 9 * height, allAtoms.get(i));
        }
    }

    public void drawAtom(Graphics g, int x, int y, AdvancedElement element) {

        Graphics2D g2 = (Graphics2D) g;

        if (drawInfo) {
            g2.drawString(summary, 0, 500);
        }

        Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
        g2.setColor(Color.black);
        g2.draw(rect);

        g2.setFont(new Font("Calibri", Font.PLAIN, 10));
        String atomicNumber = String.valueOf(element.getAtomicNumber());
        g2.drawString(atomicNumber, x + 2, y + 12);

        g2.setFont(new Font("Calibri", Font.PLAIN, 25));
        g2.drawString(element.getSymbol(), x + 2, y + 34);

        g2.setFont(new Font("Calibri", Font.PLAIN, 10));
        g2.drawString(element.getName(), x + 2, y + 46);

        String mass = String.valueOf(element.getAtomicMass());
        g2.setFont(new Font("Calibri", Font.PLAIN, 10));
        g2.drawString(mass, x + 2, y + 58);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 1174, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
