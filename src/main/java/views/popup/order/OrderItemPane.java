/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.popup.order;

import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import models.FoodItem;
import models.OrderItem;

/**
 *
 * @author P51
 */
public class OrderItemPane extends javax.swing.JPanel {
    private OrderItem orderItem;
    private DecimalFormat formatter = new DecimalFormat("###,###,###");
    /**
     * Creates new form OrderItemPane
     */
    public OrderItemPane(OrderItem o) {
        initComponents();
        this.orderItem = o;
        render();
    }
    
    public void render() {
        FoodItem foodItem = orderItem.getFoodItem();
        FoodItem topping = orderItem.getToppingItem();
        foodNameLabel.setText(foodItem.getName());
        if (foodItem.getCategoryId() != 2) {
            toppingNameLabel.setText("");
        } else {
            toppingNameLabel.setText(topping.getName());
        }
        priceLabel.setText(orderItem.getFoodPrice() + orderItem.getToppingPrice() + " ");
        spnQuantity.setValue(orderItem.getQuantity());
    }

    public JLabel getFoodNameLabel() {
        return foodNameLabel;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

    public JLabel getToppingNameLabel() {
        return toppingNameLabel;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        foodNameLabel = new javax.swing.JLabel();
        toppingNameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        priceTagLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        spnQuantity = new javax.swing.JSpinner();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        foodNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        foodNameLabel.setText("Trà Sữa Trân Châu (500ml)");

        toppingNameLabel.setText("No Topping");

        priceLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        priceLabel.setText("20,000");

        priceTagLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        priceTagLabel.setText("VNĐ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(foodNameLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(toppingNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(priceTagLabel)
                .addGap(131, 131, 131))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(foodNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toppingNameLabel)
                    .addComponent(priceLabel)
                    .addComponent(priceTagLabel))
                .addGap(18, 18, 18))
        );

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public JSpinner getSpnQuantity() {
        return spnQuantity;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel foodNameLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel priceTagLabel;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JLabel toppingNameLabel;
    // End of variables declaration//GEN-END:variables
}
