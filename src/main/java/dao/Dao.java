/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;
/**
 *
 * @author P51
 * @param <T>
 */
public abstract class Dao<T> {
    Connection conn = DatabaseConnector.getInstance().getConn();
    
    public abstract ArrayList<T> getAll() throws SQLException;
    
    public abstract T getById(int id) throws SQLException;
    
    public abstract void save(T t) throws SQLException;
    
    public abstract void update(T t) throws SQLException;
    
    public abstract void delete(T t) throws SQLException;
    
    public abstract void deleteById(int id) throws SQLException;
}
