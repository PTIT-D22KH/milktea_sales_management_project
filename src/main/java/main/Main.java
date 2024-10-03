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
import dao.ShipmentDao;
import dao.TableDao;
import models.Shipment;
import models.Table;
import utils.ShipmentStatus;
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
          ShipmentDao s = new ShipmentDao();
          Shipment shipment = new Shipment();
//          shipment.setOrderId(3);
//          shipment.setCustomerId(2);
//          shipment.setEmployeeId(2);
//          shipment.setStatus(ShipmentStatus.TOPAY);
//          s.save(shipment);
          ArrayList<Shipment> shipments = s.getAll();
          for(Shipment x : shipments){
              System.out.println(x);
          }
    }
}
