/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import utils.DatabaseConnector;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;
/**
 *
 * @author P51
 * @param <T>
 */
public abstract class Dao<T> {
    /**
     * Get connection 
     */
    Connection conn = DatabaseConnector.getInstance().getConn();
    
    /**
     * Get all the rows from table
     * @return ArrayList<T>
     * @throws SQLException 
     */
    public abstract ArrayList<T> getAll() throws SQLException;
    
    /**
     * Get the object with a specific id
     * @param id
     * @return T
     * @throws SQLException 
     */
    public abstract T getById(int id) throws SQLException;
    
    /**
     * Insert new row to the table
     * @param t
     * @throws SQLException 
     */
    public abstract void save(T t) throws SQLException;
    
    /**
     * Update a specific row in the table
     * @param t
     * @throws SQLException 
     */
    public abstract void update(T t) throws SQLException;
    
    /**
     * Delete a specific row in the table
     * @param t
     * @throws SQLException 
     */
    public abstract void delete(T t) throws SQLException;
    
    /**
     * 
     * @param id
     * @throws SQLException 
     */
    public abstract void deleteById(int id) throws SQLException;
}
