/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.Employee;

/**
 *
 * @author P51
 */
public class EmployeeManagerView extends ManagerPaneView<Employee>{
    private String list[] = {
      "Mã nhân viên", "SĐT", "Tên nhân viên"
    };
    public EmployeeManagerView() {
        super();
        setTableModel();
        renderTable();
    }
    @Override
    public void setTableModel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Họ và tên");
        tableModel.addColumn("Tên tài khoản");
        tableModel.addColumn("Mật khẩu");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("Ngày vào làm");
        tableModel.addColumn("Chức vụ");
        tableModel.addColumn("Lương");
        this.getComboSearchField().setModel(new DefaultComboBoxModel(list));
    }
    
    
}
