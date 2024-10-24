/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.FoodItem;

/**
 *
 * @author P51
 */
public class FoodItemManagerView extends ManagerPaneView<FoodItem>{
    
    private String list[] = {
      "Mã món", "Mã loại món", "Tên món"
    };
    
    public FoodItemManagerView() {
        super();
        setTableModel();
        renderTable();
    }
    @Override
    public void setTableModel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên món");
        tableModel.addColumn("Mô tả");
        tableModel.addColumn("Đường dẫn ảnh");
        tableModel.addColumn("Đơn vị");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Mã loại món");
        this.getComboSearchField().setModel(new DefaultComboBoxModel(list));
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
