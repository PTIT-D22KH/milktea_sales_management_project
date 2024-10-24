package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Employee;

public class EmployeeDao extends Dao<Employee>{
    
    public EmployeeDao(){
        
    }
    
    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM employee;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Employee e = Employee.getFromResultSet(rs);
            employees.add(e);
        }
        return employees;
    }
    
    @Override
    public Employee getById(int id) throws SQLException{
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM employee WHERE employeeId = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()){
            Employee e = Employee.getFromResultSet(rs);
            return e;
        }
        return null;
    }
    
    @Override
    public void save(Employee t) throws SQLException{
        if (t == null){
            throw new SQLException("Cannot insert into table null object(employee)");
        }
        String query = "INSERT INTO `employee` (`username`, `password`, `name`, `phoneNumber`, `startDate`, `permission`, `salary`)"
                + " VALUES (?, ?, ?, ?, current_timestamp(), ?, ?)";
        
        PreparedStatement prStatement = conn.prepareStatement(query);
        prStatement.setNString(1, t.getUsername());
        prStatement.setNString(2, t.getPassword());
        prStatement.setNString(3, t.getName());
        prStatement.setNString(4, t.getPhoneNumber());
        prStatement.setNString(5, t.getPermission().getId());
        prStatement.setInt(6, t.getSalary());
        
        int row = prStatement.executeUpdate();
    }
    
    @Override
    public void update(Employee t) throws SQLException{
        if (t == null) {
            throw new SQLException("Cannot update employee table when employee instance is null");
        }
        String query = "UPDATE `employee` SET `username` = ?, `password` = ?, `name` = ?, `phoneNumber` = ?, `permission` = ?, `salary` = ? WHERE `employeeId` = ?";
        PreparedStatement prStatement = conn.prepareStatement(query);
        prStatement.setNString(1, t.getUsername());
        prStatement.setNString(2, t.getPassword());
        prStatement.setNString(3, t.getName());
        prStatement.setNString(4, t.getPhoneNumber());
        prStatement.setNString(5, t.getPermission().getId());
        prStatement.setInt(6, t.getSalary());
        prStatement.setInt(7, t.getEmployeeId());
        
        int row = prStatement.executeUpdate();
    }
    
    @Override
    public void delete(Employee t) throws SQLException{
        String query = "DELETE " 
                + "FROM `employee`"
                + "WHERE `employeeId` = ?";
        PreparedStatement prStatement = conn.prepareStatement(query);
        prStatement.setInt(1, t.getEmployeeId());
        prStatement.executeUpdate();
    }
    
    @Override
    public void deleteById(int id) throws SQLException{
        String query = "DELETE " 
                + "FROM `employee`"
                + "WHERE `employeeId` = ?";
        PreparedStatement prStatement = conn.prepareStatement(query);
        prStatement.setInt(1, id);
        prStatement.executeUpdate();
    }
    
    /**
     * find an employee using the username
     * @param username
     * @return
     * @throws SQLException 
     */
    public Employee findByUsername(String username) throws SQLException{
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `employee` "
                + "WHERE `username` = '" + username + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()){
            Employee e = Employee.getFromResultSet(rs);
            return e;
        }
        return null;
    }
    
    public ArrayList<Employee> searchByKey(String key, String word) throws SQLException{
        ArrayList<Employee> employees = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `employee` "
                + "WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Employee e = Employee.getFromResultSet(rs);
            employees.add(e);
        }
        
        return employees;
    }
       
    /**
     * get all active employee (staff/manager)
     * @return
     * @throws SQLException 
     */
    public ArrayList<Employee> getAllActiveEmployees() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM employee"
                + " WHERE employee.permission <> 'inactive';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Employee e = Employee.getFromResultSet(rs);
            employees.add(e);
        }
        return employees;
    }
}
