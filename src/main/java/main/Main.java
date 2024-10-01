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

/**
 *
 * @author P51
 */
public class Main {
    public static void main(String[] args) throws SQLException{
        Connection conn = DatabaseConnector.getInstance().getConn();
        System.out.println("Ket noi csdl thanh cong!");
//        CustomerDao c = new CustomerDao();
//        Customer customer = new Customer();
//        customer.setAddress("Vietnam");
//        customer.setName("testUser");
//        customer.setPhoneNumber("09243712434");
//        c.save(customer);
//        c.deleteById(3);
//        ArrayList<Customer> customers = c.getAll();
//        for (Customer x : customers) {
//            System.out.println(x);
//        }
//        System.out.println(c.getById(6));
//        Customer test4 = c.getById(4);
//        test4.setAddress("Hanoi");
//        c.update(test4);
//        System.out.println(c.getById(4));
//        c.delete(test4);
//        c.deleteById(6);
    }
}
