/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.Table;

/**
 *
 * @author P51
 */
public class TableManagerView extends ManagerPaneView<Table>{
    private String list[] = {
        "Mã bàn", "Tên bàn"
    };
    
    public TableManagerView() {
        super();
        setTableModel();
        renderTable();
    }
    @Override
    public void setTableModel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên bàn");
        tableModel.addColumn("Trạng thái");
        this.getComboSearchField().setModel(new DefaultComboBoxModel(list));
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
