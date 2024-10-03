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
import dao.TableDao;
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
        Table table = new Table();
        TableDao t = new TableDao();
//        table.setName("bàn 13");
//        table.setTableId(16);
//        table.setStatus(TableStatus.FREE);
//        t.save(table);
        ArrayList<Table> a = t.getAll();
        for(Table x : a){
            System.out.println(x);
        }
    }
}
