/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import models.Employee;
import utils.EmployeePermission;
import views.popup.EmployeePopupView;

/**
 *
 * @author P51
 */
public class EmployeePopupController {
    EmployeeDao employeeDao = new EmployeeDao();
    JFrame previousView;
    
    public void add(EmployeePopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        
        for (EmployeePermission permission : EmployeePermission.values()) {
            view.getPermissionCbo().addItem(permission.getName());
        }
        view.getBtnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addEmployee(view);
                    view.dispose();
                    view.showMessage("Thêm nhân viên mới thành công!");
                    sc.onSuccess();
                } catch (Exception exception) {
                    ec.onError(exception);
                }
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
                
    }
    
    public void edit(EmployeePopupView view, Employee employee, SuccessCallback sc, ErrorCallback ec) {
       if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        
        view.getLbTitle().setText("Sửa nhân viên - " + employee.getEmployeeId());
        view.getUsernameTxtField().setText(employee.getUsername());
        view.getPasswordField().setText(employee.getPassword());
        view.getConfirmPassField().setText(employee.getPassword());
        view.getNameTxtField().setText(employee.getName());
        view.getPhoneNumberTxtField().setText(employee.getPhoneNumber());
        view.getPermissionCbo().removeAllItems();
        for (EmployeePermission permission : EmployeePermission.values()) {
            view.getPermissionCbo().addItem(permission.getName());
        }
//        view.getPermissionCbo().setSelectedItem(employee.getPermission().getName());
        view.getSalarySpinner().setValue(employee.getSalary());
        view.getBtnOK().setText("Cập nhật");
        view.getBtnOK().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   editEmployee(view, employee);
                   view.dispose();
                   view.showMessage("Sửa nhân viên thành công !");
                   sc.onSuccess();
               } catch (Exception exception) {
                   ec.onError(exception);
               }
//               throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           }
        });
    }
    
    private void addEmployee(EmployeePopupView view) throws Exception {
        String username = view.getUsernameTxtField().getText();
        String password = new String(view.getPasswordField().getPassword());
        String confirmPassword = new String(view.getConfirmPassField().getPassword());
        String phoneNumber = view.getPhoneNumberTxtField().getText();
        String name = view.getNameTxtField().getText();
        int salary = (int)view.getSalarySpinner().getValue(); 
        if (username.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || name.isEmpty() || confirmPassword.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        if (salary < 0) {
            throw new Exception("Lương không thể âm");
        }
        if (employeeDao.findByUsername(username) != null) {
            throw new Exception("Tài khoản đã tồn tại");
        }
        
        if (!password.equals(confirmPassword)) {
            throw new Exception("Bạn nhập mật khẩu xác nhận không trùng khớp");
        }
        
        Employee e = new Employee();
        e.setUsername(username);
        e.setPassword(password);
        e.setName(name);
        e.setPhoneNumber(phoneNumber);
        e.setPermission(EmployeePermission.getByName(view.getPermissionCbo().getSelectedItem().toString()));
        e.setSalary(salary);
        employeeDao.save(e);
    }
    
    private void editEmployee(EmployeePopupView view, Employee e) throws Exception{
        String username = view.getUsernameTxtField().getText();
        String password = new String(view.getPasswordField().getPassword());
        String confirmPassword = new String(view.getConfirmPassField().getPassword());
        String phoneNumber = view.getPhoneNumberTxtField().getText();
        String name = view.getNameTxtField().getText();
        int salary = (int)view.getSalarySpinner().getValue(); 
        if (username.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || name.isEmpty() || confirmPassword.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        if (salary < 0) {
            throw new Exception("Lương không thể âm");
        }      
        if (!password.equals(confirmPassword)) {
            throw new Exception("Bạn nhập mật khẩu xác nhận không trùng khớp");
        }
        Employee temp = employeeDao.findByUsername(username);
        if (temp != null && temp.getEmployeeId() == e.getEmployeeId()) {
            throw new Exception("Tên tài khoản đã tồn tại");
        }
        e.setUsername(username);
        e.setPassword(password);
        e.setName(name);
        e.setPhoneNumber(phoneNumber);
        e.setPermission(EmployeePermission.getByName(view.getPermissionCbo().getSelectedItem().toString()));
        e.setSalary(salary);
        employeeDao.update(e);
    }
}
