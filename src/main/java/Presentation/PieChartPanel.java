/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Presentation;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Joshu
 */
public class PieChartPanel extends javax.swing.JPanel {
    private final int[] data= {30, 20, 15, 35};
    /**
     * Creates new form PieChartPanel
     */
    public PieChartPanel() {
        initComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int total = 0;
        for (int value : data) {
            total += value;
        }

        int startAngle = 0;
        for (int value : data) {
            int arcAngle = (int) (360.0 * value / total);
            g2d.setColor(Color.getHSBColor((float) Math.random(), 0.7f, 0.9f));
            g2d.fillArc(50, 50, 200, 200, startAngle, arcAngle);
            startAngle += arcAngle;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
