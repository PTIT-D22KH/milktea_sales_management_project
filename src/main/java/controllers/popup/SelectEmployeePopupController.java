/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.EmployeeDao;
import java.sql.SQLException;
import javax.swing.JFrame;
import models.Employee;
import views.popup.SelectEmployeePopupView;

/**
 *
 * @author buiva
 */
public class SelectEmployeePopupController {
    EmployeeDao employeeDao = new EmployeeDao();
    JFrame previousView;
    
    public interface Callback {
        public abstract void run(Employee employee);
    }
    
    public void select(SelectEmployeePopupView view, Callback callback) {
        if(previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        try {         
            view.renderEmployee(employeeDao.getAll());
        }
        catch (SQLException e) {
            view.showError(e);
        }
        view.getBtnOK().addActionListener(evt -> {
            Employee c = view.getListEmployee().getSelectedValue();
            if(c == null) {
                view.showError("Ban chua chon nhan vien nao");
                return;
            }
            callback.run(c);
            view.dispose();
        });
        view.getBtnSearch().addActionListener(evt -> {
            String txtSearch = view.getTxtEmployeeName().getText();
            try {
                view.renderEmployee(employeeDao.searchByKey("name", txtSearch));          
            }
            catch (Exception e) {
                view.showError(e);
            }
        });
        view.getBtnCancel().addActionListener(evt -> {
            view.dispose();
        });
    }
}
