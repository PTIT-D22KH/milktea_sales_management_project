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
     * Database connection instance
     */
    protected Connection conn = DatabaseConnector.getInstance().getConn();
    
    /**
     * Get all entities from the table
     * @return ArrayList of all entities
     * @throws SQLException if database error occurs
     */
    public abstract ArrayList<T> getAll() throws SQLException;
    
    /**
     * Get an entity by its ID
     * @param id The ID to search for
     * @return The entity if found, null otherwise
     * @throws SQLException if database error occurs
     */
    public abstract T getById(int id) throws SQLException;
    
    /**
     * Save a new entity
     * @param t The entity to save
     * @throws SQLException if database error occurs or entity is invalid
     */
    public abstract void save(T t) throws SQLException;
    
    /**
     * Update an existing entity
     * @param t The entity to update
     * @throws SQLException if database error occurs or entity is invalid
     */
    public abstract void update(T t) throws SQLException;
    
    /**
     * Delete an entity
     * @param t The entity to delete
     * @throws SQLException if database error occurs or entity is invalid
     */
    public abstract void delete(T t) throws SQLException;
    
    /**
     * Delete an entity by its ID
     * @param id The ID of the entity to delete
     * @throws SQLException if database error occurs
     */
    public abstract void deleteById(int id) throws SQLException;
    
    /**
     * Search for entities by a specific field and value
     * @param key The field to search by
     * @param value The value to search for
     * @return ArrayList of matching entities
     * @throws SQLException if database error occurs
     */
    public abstract ArrayList<T> searchByKey(String key, String value) throws SQLException;
    
    /**
     * Validate an entity before saving or updating
     * @param t The entity to validate
     * @throws SQLException if validation fails
     */
    protected abstract void validate(T t) throws SQLException;
}
