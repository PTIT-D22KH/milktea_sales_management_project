/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.employee;

import controllers.popup.ErrorCallback;
import controllers.popup.SuccessCallback;
import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import models.Employee;
import utils.SessionManager;
import views.employee.ChangePasswordView;

/**
 *
 * @author P51
 */
public class ChangePasswordController {
    private EmployeeDao employeeDao;
    private JFrame previousView;
    public ChangePasswordController() {
        this.employeeDao = new EmployeeDao();
    }
    
    public void show(ChangePasswordView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
            
        });
        view.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePassword(view);
                    sc.onSuccess();
                } catch (Exception exception) {
                    ec.onError(exception);
                }
                
            }
            
        });
    }
    private void changePassword(ChangePasswordView view) throws  Exception{
        String oldPass = new String(view.getOldPasswordField().getPassword());
        String newPass = new String(view.getNewPasswordField().getPassword());
        String confirmPass = new String(view.getConfirmNewPassField().getPassword());
        Employee currentLoginEmployee = SessionManager.getSession().getEmployee();
        if (oldPass.isEmpty() || newPass.isEmpty()|| confirmPass.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ các trường");
        }
        if (!oldPass.equals(currentLoginEmployee.getPassword())) {
            throw new Exception("Mật khẩu cũ không chính xác!");
        }
        if (!newPass.equals(confirmPass)) {
            throw  new Exception("Xác nhận mật khẩu sai!");
        }
        currentLoginEmployee.setPassword(newPass);
        employeeDao.update(currentLoginEmployee);
        view.dispose();
    }
}
