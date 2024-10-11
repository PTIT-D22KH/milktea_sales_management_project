/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Employee;
import views.ForgotPasswordView;

/**
 *
 * @author P51
 */
public class ForgotPasswordController {
    private ForgotPasswordView view;
    private EmployeeDao employeeDao;
    
    public ForgotPasswordController(ForgotPasswordView view) {
        this.view = view;
        this.employeeDao = new EmployeeDao();
        view.setVisible(true);
        addEvent();
    }
    
    private void addEvent() {
        view.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPassword();
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
        });
        
    }
    
    private void resetPassword() {
        String username = view.getUsernameTxtField().getText();
        String newPassword = new String(view.getPasswordField().getPassword());
        String confirmPassword = new String(view.getConfirmPassField().getPassword());
        
        if (username.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            view.showError("Hãy điền đầy đủ thông tin");
            return;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            view.showError("Xác nhận mật khẩu không trùng khớp");
            return;
        }
        try {
            Employee employee = employeeDao.findByUsername(username);
            if (employee == null) {
                view.showError("Username không tồn tại!");
                return;
            }

            employee.setPassword(newPassword);
            employeeDao.update(employee);
            view.showMessage("Cập nhật mật khẩu mới thành công !");
            view.setVisible(false);
            
        } catch (Exception e) {
            view.showError("Cập nhật mật khẩu mới thất bại: " + e.getMessage());
        }
    }
}
