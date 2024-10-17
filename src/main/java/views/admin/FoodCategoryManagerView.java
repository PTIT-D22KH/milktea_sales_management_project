/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.FoodCategory;

/**
 *
 * @author P51
 */
public class FoodCategoryManagerView extends ManagerPaneView<FoodCategory>{
    private String list[] = {
        "FoodCategoryId", "Name"
    };
    
    public FoodCategoryManagerView() {
        super();
        setTableModel();
        renderTable();
    }
    @Override
    public void setTableModel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên loại");
        this.getComboSearchField().setModel(new DefaultComboBoxModel(list));
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
