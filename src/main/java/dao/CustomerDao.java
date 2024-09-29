/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Customer;

/**
 *
 * @author P51
 */
public class CustomerDao extends Dao<Customer>{

    public CustomerDao() {
        
    }
    
    public CustomerDao(Connection mockConnection) {
        conn = mockConnection;
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query  = "SELECT * FROM `customer`";
        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            Customer customer = Customer.getFromResultSet(result);
            customers.add(customer);
        } 
        return customers;
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Customer getById(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * "
                + "FROM `customer` "
                + "WHERE `customerId` = " + id;
        ResultSet result = statement.executeQuery(query);
        if (result.next()) {
            Customer customer = Customer.getFromResultSet(result);
            return customer;
        }
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(Customer t) throws SQLException {
        if  (t == null) {
            throw new SQLException("Cannot insert into table null object(customer)");
        }
        String query = "INSERT INTO `customer` (`phoneNumber`, `name`, `address`) "
                + "VALUES (?, ?, ?);";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, t.getPhoneNumber()); // index start from 1
        statement.setNString(2, t.getName());
        statement.setNString(3, t.getAddress());
        
        int row = statement.executeUpdate();
        
        
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Customer t) throws SQLException {
        if (t == null) {
            throw new SQLException("Cannot update customer table when customer instance is null");
        }
        String query = "UPDATE `customer` "
                + "SET `phoneNumber` = ?, `address` = ?, `name` = ? "
                + "WHERE `customerId` = ?";
        
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, t.getPhoneNumber());
        statement.setNString(2, t.getAddress());
        statement.setNString(3, t.getName());
        statement.setInt(4, t.getCustomerId());
        
        int row = statement.executeUpdate();

        
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Customer t) throws SQLException {
        String query = "DELETE "
                + "FROM `customer`"
                + "WHERE `customerId` = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, t.getCustomerId());
        statement.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String query = "DELETE "
                + "FROM `customer`"
                + "WHERE `customerId` = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
