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
public class ForgotPasswordController extends AuthenticationController<ForgotPasswordView>{
    public ForgotPasswordController(ForgotPasswordView view) {
        super(view);
    }
    public ForgotPasswordController(ForgotPasswordView view, EmployeeDao employeeDao) {
        super(view, employeeDao);
    }
    @Override
    protected void addEvent() {
        view.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPassword();
            }
            
        });
        
    }
    
    private void resetPassword() {
        String username = view.getUsernameTxtField().getText();
        String newPassword = new String(view.getPasswordField().getPassword());
        String confirmPassword = new String(view.getConfirmPassField().getPassword());
        
        if (username.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            view.showMessage("Hãy điền đầy đủ thông tin");
            return;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            view.showMessage("Xác nhận mật khẩu sai");
            return;
        }
        try {
            Employee employee = employeeDao.findByUsername(username);
            if (employee == null) {
                view.showMessage("Username không tồn tại!");
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
