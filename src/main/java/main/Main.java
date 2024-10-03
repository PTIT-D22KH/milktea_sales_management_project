/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

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
import utils.TableStatus;

/**
 *
 * @author P51
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DatabaseConnector.getInstance().getConn();
        System.out.println("Ket noi csdl thanh cong!");
//        CustomerDao customerDao = new CustomerDao();
//        customerDao.deleteById(1);
        try {
            OrderItemDao dao = new OrderItemDao();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(8); // Thay đổi giá trị này theo nhu cầu của bạn
            orderItem.setFoodItemId(2);
            orderItem.setToppingId(3);
            orderItem.setQuantity(4);
            orderItem.setFoodPrice(150);
            orderItem.setToppingPrice(30);
            orderItem.setNote("Less spicy");
            dao.update(orderItem);
            System.out.println("Order item updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
