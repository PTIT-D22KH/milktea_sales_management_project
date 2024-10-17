/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.employee;

import controllers.TimeCounterController;
import controllers.popup.ErrorCallback;
import controllers.popup.SuccessCallback;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Employee;
import utils.ErrorPopup;
import utils.SessionManager;
import views.employee.ChangePasswordView;
import views.employee.EmployeeInformationView;
import views.employee.LoginHistoryView;

/**
 *
 * @author P51
 */
public class EmployeeInformationController {
    private Employee sessionEmployee;
    private EmployeeInformationView view;
    private ChangePasswordController changePasswordController;
    private LoginHistoryController historyController;
    
    public EmployeeInformationController() {
        this.sessionEmployee = SessionManager.getSession().getEmployee();
        changePasswordController = new ChangePasswordController();
        historyController = new LoginHistoryController();
        
    }

    public EmployeeInformationView getView() {
        return view;
    }

    public void setView(EmployeeInformationView view) {
        if (this.view != view) {
            TimeCounterController.start(new TimeCounterController.Callback() {
                @Override
                public void onTick(int second) {
                    view.getTimeSessionLabel().setText(secondToHours(second));
                }
            });
            addEvent(view);
            this.view = view;
        }
        
    }
    private void addEvent(EmployeeInformationView view) {
        view.getChangePassButton().addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                SuccessCallback successCallback = new SuccessCallback() {
                @Override
                public void onSuccess() {
                    JOptionPane.showMessageDialog(view, "Đổi pass thành công");
                }
            };

            ErrorCallback errorCallback = new ErrorCallback() {
                @Override
                public void onError(Exception e) {
                    ErrorPopup.show(e);
                }
            };
                changePasswordController.show(new ChangePasswordView(), successCallback, errorCallback);
            }
            
        });
        view.getHistorySessionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyController.show(new LoginHistoryView());
            }
            
        });
    }
    
    private String secondToHours(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
