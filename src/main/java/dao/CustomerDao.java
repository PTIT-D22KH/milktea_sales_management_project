/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Customer;

/**
 *
 * @author P51
 */
public class CustomerDao extends Dao<Customer>{


    /**
     * return all customers in database
     * @return
     * @throws SQLException 
     */
    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        
        String query  = "SELECT * FROM `customer`";
        try(PreparedStatement statement = conn.prepareStatement(query)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Customer customer = Customer.getFromResultSet(result);
                    customers.add(customer);
                } 
            }
        }
        
        
        return customers;
    }

    /**
     * return customer with the id provided
     * @param id
     * @return
     * @throws SQLException 
     */
    @Override
    public Customer getById(int id) throws SQLException {
        String query = "SELECT * "
                + "FROM `customer` "
                + "WHERE `customerId` = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Customer customer = Customer.getFromResultSet(result);
                    return customer;
                }
            }
        }
        return null;
    }

    /**
     * add a customer to the database
     * @param t
     * @throws SQLException 
     */
    @Override
    public void save(Customer t) throws SQLException {
        validate(t);
        String query = "INSERT INTO `customer` (`phoneNumber`, `name`, `address`) "
                + "VALUES (?, ?, ?);";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setNString(1, t.getPhoneNumber()); // index start from 1
            statement.setNString(2, t.getName());
            statement.setNString(3, t.getAddress());
            
            int row = statement.executeUpdate();        
        }
    }

    /**
     * update a customer
     * @param t
     * @throws SQLException 
     */
    @Override
    public void update(Customer t) throws SQLException {
        validate(t);
        String query = "UPDATE `customer` "
                + "SET `phoneNumber` = ?, `address` = ?, `name` = ? "
                + "WHERE `customerId` = ?";
        
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setNString(1, t.getPhoneNumber());
            statement.setNString(2, t.getAddress());
            statement.setNString(3, t.getName());
            statement.setInt(4, t.getCustomerId());
            
            int row = statement.executeUpdate();
        }
    }

    /**
     * delete a customer
     * @param t
     * @throws SQLException 
     */
    @Override
    public void delete(Customer t) throws SQLException {
        validate(t);
        deleteById(t.getCustomerId());
    }

    /**
     * delete the customer with the provided customerId
     * @param id
     * @throws SQLException 
     */
    @Override
    public void deleteById(int id) throws SQLException {
        String query = "DELETE "
                + "FROM `customer`"
                + "WHERE `customerId` = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();        
        }
    }   
    
    /**
     * Search by the column with word pattern
     * @param key is the column of customer table in database
     * @param word
     * @return
     * @throws SQLException 
     */
    @Override
    public ArrayList<Customer> searchByKey(String key, String word) throws SQLException {
        if (key == null || word == null || key.trim().isEmpty() || word.trim().isEmpty()) {
            throw new SQLException("Từ khoá không được null/trống");
        }
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM `customer` WHERE " + key + " LIKE ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, "%" + word + "%");
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Customer customer = Customer.getFromResultSet(rs);
                    customers.add(customer);
                }
            }
        }
        return customers;
    }

    @Override
    protected void validate(Customer t) throws SQLException {
        if (t == null) {
            throw new SQLException("Customer object cannot be null");
        }
    }
}
