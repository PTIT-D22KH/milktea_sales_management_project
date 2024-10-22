/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controllers.LoginController;
import controllers.popup.CustomerPopupController;
import controllers.popup.EmployeePopupController;
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
import dao.FoodItemDao;
import dao.OrderItemDao;
import dao.TableDao;
import models.FoodItem;
import models.OrderItem;
import models.Table;
import utils.ShutdownHook;
import utils.TableStatus;
import views.ForgotPasswordView;
import views.LoginView;
import views.RegisterView;
import views.popup.CustomerPopupView;
import views.popup.EmployeePopupView;

/**
 *
 * @author P51
 */
public class Main {
    public static void main(String[] args) throws SQLException{
        Connection conn = DatabaseConnector.getInstance().getConn();
        System.out.println("Ket noi csdl thanh cong!");
        try {
            javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            System.out.println("Set up UI Theme successfully!");
        } catch (Exception ex) {
            System.err.println("Set up UI Theme failed!");   
        }
        LoginController controller = new LoginController(new LoginView(), new ForgotPasswordView(), new RegisterView());
        controller.showView();
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    }
}