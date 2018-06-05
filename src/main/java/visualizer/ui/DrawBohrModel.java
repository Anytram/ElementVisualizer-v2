/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.ui;

import visualizer.model.AdvancedElement;
import visualizer.database.FileDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import static java.lang.Math.*;
import java.net.URISyntaxException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;

/**
 *
 * @author Anytram
 */
public class DrawBohrModel extends javax.swing.JPanel {

    private FileDatabase database;
    private AdvancedElement element;
    private int shiftAngle;

    int elipseFactorX = 2;
    int elipseFactorY = 1;

    double centerX = getWidth() / 2;
    double centerY = getHeight() / 2;

    /**
     * Creates new form DrawJPanel
     */
    public DrawBohrModel() throws IOException, URISyntaxException {
        database = FileDatabase.getInstance();
        element = database.findByName("Hydrogen");
        initComponents();
        jComboBox1.setModel(new DefaultComboBoxModel<>(database.getAllNames()));

        String legend = "Colors of the nucleus:" + "\n" + "\n" + "WHITE - gas" + "\n" + "GREY - solid" + "\n" + "BLUE - liquid";

        jTextArea1.setText(legend);
    }

    public void setElement(String name) {

        this.shiftAngle = 0;
        this.element = database.findByName(name);
    }

    public void centerString(Graphics g, String s) {
        FontRenderContext frc
                = new FontRenderContext(null, true, true);

        Rectangle r = new Rectangle(getWidth(), getHeight());
        Font font = new Font("Calibri", Font.PLAIN, 25);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY;

        g.setFont(font);
        g.drawString(s, r.x + a, r.y + b);
    }

    @Override
    public void paintComponent(Graphics g) {

        shiftAngle++;

        Graphics2D g2 = (Graphics2D) g;

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        double radius = 15.0 + element.getAtomicMass() / 15.0;

        Ellipse2D nucleus = new Ellipse2D.Double();
        nucleus.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);

        if (element.isGas()) {
            g2.setColor(Color.white);
        } else if (element.isSolid()) {
            g2.setColor(Color.GRAY);
        } else if (element.isLiquid()) {
            g2.setColor(new Color(137, 140, 207));
        }

        g2.fill(nucleus);

        g2.setColor(Color.BLACK);
        g2.draw(nucleus);

        g2.setColor(Color.black);

        centerString(g, element.getSymbol());

        drawShells(g);
        drawElectrons(g);
    }

    public int getInt() {

        return shiftAngle;
    }

    private void drawElectrons(Graphics g) {

        double radius = 25.0 + element.getAtomicMass() / 15.0;
        int[] array = element.getShells();

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                double angle = 360.0 / array[i];
                for (int j = 0; j < array[i]; j++) {
                    g.setColor(Color.black);
                    g.fillOval((int) (cos(
                            toRadians(j * angle + shiftAngle * (3.0 / (i + 1)))) * (2 + i) * radius * elipseFactorX + centerX - 5),
                            (int) (sin(
                                    toRadians(j * angle + shiftAngle * (3.0 / (i + 1)))) * (2 + i) * radius * elipseFactorY + centerY - 5), 10, 10);
                }
            }
        }
    }

    private void drawShells(Graphics g) {

        double radius = 25.0 + element.getAtomicMass() / 15.0;
        int[] array = element.getShells();

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                Ellipse2D shell = new Ellipse2D.Double();
                shell.setFrameFromCenter(centerX, centerY, centerX + (2 + i) * radius * elipseFactorX, centerY + (2 + i) * radius * elipseFactorY);
                g.setColor(Color.LIGHT_GRAY);
                ((Graphics2D) g).draw(shell);
            }
        }
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

        jComboBox1 = new JComboBox<>();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jToggleButton1 = new JToggleButton();

        FormListener formListener = new FormListener();

        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(formListener);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jToggleButton1.setText("Change view");
        jToggleButton1.addActionListener(formListener);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToggleButton1, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox1, 0, 151, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(721, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements ActionListener {
        FormListener() {}
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == jComboBox1) {
                DrawBohrModel.this.jComboBox1ActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButton1) {
                DrawBohrModel.this.jToggleButton1ActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        setElement((String) jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jToggleButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        if (jToggleButton1.isSelected()) {
            elipseFactorX = 1;
            elipseFactorY = 1;
        } else {
            elipseFactorX = 2;
            elipseFactorY = 1;
        }

    }//GEN-LAST:event_jToggleButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<String> jComboBox1;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
