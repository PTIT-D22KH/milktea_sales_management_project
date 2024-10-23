/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.Order;

/**
 *
 * @author P51
 */
public class OrderManagerView extends ManagerPaneView<Order>{

    private String list[] = {
        "Mã đơn"
    };
    public OrderManagerView() {
        super();
        setTableModel();
        renderTable();
    }
    @Override
    public void setTableModel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Nhân viên tạo");
        tableModel.addColumn("Bàn");
        tableModel.addColumn("Loại");
        tableModel.addColumn("Trạng thái");
        tableModel.addColumn("Ngày lập HĐ");
        tableModel.addColumn("Ngày thanh toán");
        tableModel.addColumn("Đã thanh toán");
        this.getComboSearchField().setModel(new DefaultComboBoxModel(list));
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
