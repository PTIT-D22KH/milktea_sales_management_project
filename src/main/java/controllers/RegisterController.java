/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import models.Employee;
import utils.EmployeePermission;
import views.RegisterView;

/**
 *
 * @author P51
 */
public class RegisterController extends AuthenticationController<RegisterView>{
    public RegisterController(RegisterView view) {
        super(view);
    }
    public RegisterController(RegisterView view, EmployeeDao employeeDao) {
        super(view, employeeDao);
    }
    
    @Override
    protected void addEvent(){
        view.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                register();
            }
            
        });
    }
    
    private void register(){
        String name = view.getNameTxtField().getText();
        String phoneNumber = view.getPhoneNumberTxtField().getText();
        String username = view.getUsernameTxtField().getText();
        String password = new String(view.getPasswordField().getPassword());
        String confirmPassword = new String(view.getConfirmPassField().getPassword());
        
        if (name.isEmpty() || phoneNumber.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            view.showMessage("Xác nhận mật khẩu sai");
            return;
        }
        try {
            if (employeeDao.findByUsername(username) != null) {
                view.showMessage("Username đã có người sử dụng");
                return;
            }
        } catch (Exception e) {
            view.showError(e);
        }

        try {
            Employee employee = new Employee();
            employee.setUsername(username);
            employee.setName(name);
            employee.setPhoneNumber(phoneNumber);
            employee.setPassword(password);
            employee.setPermission(EmployeePermission.STAFF);
            employee.setSalary(0); // Set salary to 0
            employee.setStartDate(new Date(System.currentTimeMillis())); // Set startDate to current date
            employeeDao.save(employee);
            view.showMessage("Đăng ký thành công");
            view.setVisible(false);
        } catch (Exception e) {
            view.showError("Đăng ký thất bại: " + e.getMessage());
            
        }
       
    }
  
    
    
}