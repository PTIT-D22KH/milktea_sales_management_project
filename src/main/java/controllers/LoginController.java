/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Employee;
import utils.SessionManager;
import views.ForgotPasswordView;
import views.LoginView;
import views.RegisterView;

/**
 *
 * @author P51
 */
public class LoginController {
    private LoginView view;
    private EmployeeDao employeeDao;
    
    public LoginController(LoginView view) {
        this.employeeDao = new EmployeeDao();
        this.view = view;
        view.setVisible(true);
        addEvent();
    }

    public LoginView getView() {
        return view;
    }

    public void setView(LoginView view) {
        this.view = view;
    }
    
    public void login() {
        String username = view.getUsernameTextField().getText();
        String password = new String(view.getPasswordField().getPassword());
        try {
            Employee employee = employeeDao.findByUsername(username);
            if (employee == null) {
                view.showError("Không tồn tại tài khoản");
                return;
            }
            if (!employee.checkPassword(password)) {
                view.showError("Mật khẩu sai");
                return;
            }
            SessionManager.create(employee);
            System.out.println("Đăng nhập thành công!");
                   
        } catch (Exception e) {
            view.showError(e);
        }
    }

    private void addEvent() {
        //login event
        view.getPasswordField().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.getLoginButton().doClick();
                }
            }
        });
        view.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                login();
            }
        });
        view.getForgotPassLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ForgotPasswordController controller = new ForgotPasswordController(new ForgotPasswordView());
//                view.showMessage("Chưa hỗ trợ!");
            }
        });
        view.getRegisterLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisterController controller  = new RegisterController(new RegisterView());
//                view.showMessage("Chưa hỗ trợ!");
            }
        });
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
