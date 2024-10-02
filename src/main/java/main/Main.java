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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import dao.EmployeeDao;
import dao.FoodCategoryDao;
import java.io.IOException;
import java.sql.Date;

/**
 *
 * @author P51
 */
public class Main {
    public static void main(String[] args) throws SQLException, ParseException, IOException{
        Connection conn = DatabaseConnector.getInstance().getConn();
        System.out.println("Ket noi csdl thanh cong!");
        
//        FoodCategory f = new FoodCategory();
//        FoodCategoryDao fd = new FoodCategoryDao();
//        
//        f.setName("Tráng miệng");
//        
//        ArrayList<FoodCategory> a = new ArrayList<>();
//        a = fd.searchByKey("name", "n");
//        for (FoodCategory tmp : a){
//            System.out.println(tmp);
//        }
        
//        EmployeeDao ed = new EmployeeDao();
//        Employee e = new Employee();
//        e.setName("Thien");
//        e.setUsername("thienban123");
//        e.setPassword("123");
//        e.setPhoneNumber("0903483984");
//        e.setSalary(20880);
//        java.util.Date x = new SimpleDateFormat("dd/MM/yyyy").parse("20/01/2022");
//        e.setStartDate(new Date(x.getTime()));
//        e.setPermission(EmployeePermission.MANAGER);
//        
//        Employee m = ed.getById(30);
        
//        Employee k = ed.findByUsername("admin");
//        System.out.println(k);
        
//        ArrayList<Employee> a = new ArrayList<>();
//        a = ed.getAll();
        
//        for (Employee tmp : a){
//            System.out.println(tmp);
//        }
        
//        Employee m = ed.getById(0)
//        
//        System.out.println(ed.getById(6));
        
//        CustomerDao cd = new CustomerDao();
//        Customer customer = new Customer();
//        customer.setAddress("Vietnam");
//        customer.setName("testUser");
//        customer.setPhoneNumber("09243712434");
//        //cd.save(customer);
//        cd.deleteById(1);
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
