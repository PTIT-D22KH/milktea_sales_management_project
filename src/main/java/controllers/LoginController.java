/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import models.Employee;
import utils.SessionManager;
import views.AdminDashboardView;
import views.EmployeeDashboardView;
import views.ForgotPasswordView;
import views.LoginView;
import views.RegisterView;
import views.admin.HomeView;

/**
 *
 * @author P51
 */
public class LoginController extends AuthenticationController<LoginView>{
    private ForgotPasswordController forgotPasswordController;
    private RegisterController registerController;
    
    public LoginController(LoginView view, ForgotPasswordView forgotPasswordView, RegisterView registerView) {
        super(view);
        this.forgotPasswordController = new ForgotPasswordController(forgotPasswordView);
        this.registerController = new RegisterController(registerView);
    }
    public LoginController(LoginView view, EmployeeDao employeeDao, ForgotPasswordView forgotPasswordView, RegisterView registerView) {
        super(view, employeeDao);
        this.forgotPasswordController = new ForgotPasswordController(forgotPasswordView, employeeDao);
        this.registerController = new RegisterController(registerView, employeeDao);
    }
    
    public void login() {
        String username = view.getUsernameTextField().getText();
        String password = new String(view.getPasswordField().getPassword());
        try {
            Employee employee = employeeDao.findByUsername(username);
            if (employee == null) {
                view.showMessage("Không tồn tại tài khoản");
                return;
            }
            if (!employee.checkPassword(password)) {
                view.showMessage("Mật khẩu sai");
                return;
            }
            SessionManager.create(employee);
            switch (employee.getPermission()) {
                case MANAGER:
                    //Admin controller
                    AdminDashboardController controller = new AdminDashboardController(new AdminDashboardView());
                    controller.getView().setPanel(new HomeView());
                    view.dispose();
                    break;
                    
                case STAFF:
                    //employee dashboard controller
                    EmployeeDashboardController controller1 = new EmployeeDashboardController(new EmployeeDashboardView());
                    controller1.getView().setPanel(new HomeView());
                    controller1.getView().setVisible(true);
                    view.dispose();
                    break;
                case INACTIVE:
                    view.showMessage("Tài khoản của bạn đã bị khóa.\nVui lòng liên hệ admin để biết thêm chi tiết");
                    SessionManager.update();
//                    view.dispose();
                    break;
                default:
                    view.showMessage("Vui lòng liên hệ admin để biết thêm chi tiết");
                    SessionManager.update();
//                    view.dispose();
                    break;
            }
            
           
                   
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    protected void addEvent() {
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
                forgotPasswordController.showView();
            }
        });
        view.getRegisterLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerController.showView();
            }
        });
    }
    
    
}