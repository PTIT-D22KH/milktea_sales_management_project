/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controllers.LoginController;
import controllers.popup.CustomerPopupController;
import controllers.popup.ErrorCallback;
import controllers.popup.SuccessCallback;
import dao.CustomerDao;
import utils.DatabaseConnector;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Customer;
import models.Employee;
import models.FoodCategory;
import utils.EmployeePermission;
import dao.EmployeeDao;
import dao.FoodCategoryDao;
import dao.TableDao;
import models.Table;
import utils.TableStatus;
import views.LoginView;
import views.popup.CustomerPopupView;

/**
 *
 * @author P51
 */
public class Main {
    public static void main(String[] args) throws SQLException{
        Connection conn = DatabaseConnector.getInstance().getConn();
        System.out.println("Ket noi csdl thanh cong!");
//        CustomerDao c = new CustomerDao();
//        c.deleteById(3);

        try {
            javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            System.out.println("Set up UI Theme successfully!");
        } catch (Exception ex) {
            System.err.println("Set up UI Theme failed!");
        }
//        LoginController controller = new LoginController(new LoginView());
//        EmployeeDao e = new EmployeeDao();
//        System.out.println(e.getById(7));
//        LoginView loginView = new LoginView();
//        loginView.setVisible(true);
//        RegisterView registerView = new RegisterView();
//        registerView.setVisible(true);
        // Initialize the CustomerPopupView and CustomerPopupController
        CustomerPopupView view = new CustomerPopupView();
        CustomerPopupController controller = new CustomerPopupController();

        // Define success and error callbacks
        SuccessCallback successCallback = new SuccessCallback() {
            @Override
            public void onSuccess() {
                System.out.println("Thanh cong");
            }
        };

        ErrorCallback errorCallback = new ErrorCallback() {
            @Override
            public void onError(Exception e) {
                System.out.println("That bai: " + e.getMessage());
            }
        };
//        // Example usage: Adding a new customer
//        controller.add(view, successCallback, errorCallback);
        CustomerDao customerDao = new CustomerDao();
        Customer a = customerDao.getById(7);
        controller.edit(view, a, successCallback, errorCallback);
        
//        CustomerManagerController controller = new CustomerManagerController();
//        controller.actionAdd();
        
    }
}