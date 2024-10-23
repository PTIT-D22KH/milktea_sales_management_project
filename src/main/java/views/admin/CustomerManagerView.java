package views.admin;


import javax.swing.DefaultComboBoxModel;
import models.Customer;
import views.admin.ManagerPaneView;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author P51
 */
public class CustomerManagerView extends ManagerPaneView<Customer>{
    
    String list[] = {
        "Mã khách hàng",
        "SĐT",
        "Tên khách hàng",
        "Địa chỉ"
    };
    public CustomerManagerView() {
        super();
        setTableModel();
        renderTable();
    }
    
    @Override
    public void setTableModel(){ 
        tableModel.addColumn("ID");
        tableModel.addColumn("Họ và tên");
        tableModel.addColumn("SĐT");
        tableModel.addColumn("Địa chỉ");
        this.getComboSearchField().setModel(new DefaultComboBoxModel(list));
    }
}
