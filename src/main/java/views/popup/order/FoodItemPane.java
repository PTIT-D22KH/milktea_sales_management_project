/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.popup.order;

import java.text.DecimalFormat;
import models.FoodItem;

/**
 *
 * @author P51
 */
public class FoodItemPane extends javax.swing.JPanel {

    /**
     * Creates new form FoodItemPane
     */
    private DecimalFormat formatter;
    private FoodItem foodItem;
    public FoodItemPane(FoodItem foodItem) {
        
        initComponents();
        this.foodItem = foodItem;
        if (foodItem != null) {
            nameLabel.setText(foodItem.getName());
            priceLabel.setText(formatter.format(foodItem.getUnitPrice()) + "VNĐ / " + foodItem.getUnitName());
        }
 
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameLabel.setText("Trà Sữa Trân Châu Đường Đen (500ml)");

        priceLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(255, 0, 51));
        priceLabel.setText("50,000 VNĐ / Ly");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel)
                    .addComponent(priceLabel))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel)
                .addGap(30, 30, 30)
                .addComponent(priceLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel priceLabel;
    // End of variables declaration//GEN-END:variables
}
