/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import models.Employee;
import views.popup.SelectEmployeePopupView;
import views.popup.SelectEntityPopupView;

/**
 *
 * @author buiva
 */
public class SelectEmployeePopupController extends SelectEntityPopupController<SelectEmployeePopupView, EmployeeDao, Employee>{
//    private EmployeeDao employeeDao;
//    private JFrame previousView;
    public SelectEmployeePopupController() {
        this.entityDao = new EmployeeDao();
    }
//
    
    public SelectEmployeePopupController(EmployeeDao employeeDao) {
        this.entityDao = employeeDao;
    }
//    
//    public interface Callback {
//        public abstract void run(Employee employee);
//    }
    @Override
    public void select(SelectEmployeePopupView view, Callback<Employee> callback) {
        super.select(view, callback);
        view.getBtnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee c = view.getEntityList().getSelectedValue();
                if(c == null) {
                    view.showError("Bạn chưa chọn nhân viên nào!");
                    return;
                }
                callback.run(c);
                view.dispose();
            }
            
        });
        view.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txtSearch = view.getEntityNameTxtField().getText();
                try {
                    view.renderEntity(entityDao.searchByKey("name", txtSearch));          
                }
                catch (Exception exception) {
                    view.showError(exception);
                }
            }
            
        });
    }
}
