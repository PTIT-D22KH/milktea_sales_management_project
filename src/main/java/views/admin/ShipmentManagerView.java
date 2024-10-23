/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.Shipment;

/**
 *
 * @author P51
 */
public class ShipmentManagerView extends ManagerPaneView<Shipment>{
    private String list[] = {
      "Mã đơn"
    };
    
    public ShipmentManagerView() {
        super();
        setTableModel();
        renderTable();
    }

    @Override
    public void setTableModel() {
        tableModel.addColumn("Mã hoá đơn");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Tên nhân viên ship");
        tableModel.addColumn("Giá ship");
        tableModel.addColumn("Trạng thái");
        tableModel.addColumn("Ngày bắt đầu");
        tableModel.addColumn("Ngày kết thúc");
        this.getComboSearchField().setModel(new DefaultComboBoxModel(list));
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
