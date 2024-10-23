/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import models.Employee;
import views.popup.SelectEmployeePopupView;

/**
 *
 * @author buiva
 */
/**
 * Controller for managing employee selection popups.
 * Adheres to SRP by focusing only on employee selection popup management.
 */
public class SelectEmployeePopupController extends SelectEntityPopupController<SelectEmployeePopupView, EmployeeDao, Employee>{
    public SelectEmployeePopupController() {
        super(new EmployeeDao());
    }

    public SelectEmployeePopupController(EmployeeDao employeeDao) {
        super(employeeDao);
    }
    @Override
    public void select(SelectEmployeePopupView view, Callback<Employee> callback) {
        if (getPreviousView() != null && getPreviousView().isDisplayable()) {
            getPreviousView().requestFocus();
            return;
        }
        setPreviousView(view);
        view.setVisible(true);
        try {
            view.renderEntity(getEntityDao().getAllActiveEmployees());
        } catch (SQLException e) {
            view.showError(e);
        }
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
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
                    view.renderEntity(getEntityDao().searchByKey("name", txtSearch));          
                }
                catch (Exception exception) {
                    view.showError(exception);
                }
            }
            
        });
    }
}
