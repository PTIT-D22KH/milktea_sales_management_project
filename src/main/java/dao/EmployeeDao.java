package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Employee;

public class EmployeeDao extends Dao<Employee>{
    
    
    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee;";
        try(Statement statement = conn.createStatement()) {
            try(ResultSet rs = statement.executeQuery(query)) {
                while (rs.next()){
                    Employee e = Employee.getFromResultSet(rs);
                    employees.add(e);
                }
            }
        }
        return employees;
    }
    
    @Override
    public Employee getById(int id) throws SQLException {
        String query = "SELECT * FROM employee WHERE employeeId = ?";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setInt(1, id);
            try (ResultSet rs = prStatement.executeQuery()) {
                if (rs.next()) {
                    Employee e = Employee.getFromResultSet(rs);
                    return e;
                }
            }
        }
        return null;
    }
    
    @Override
    public void save(Employee t) throws SQLException{
        validate(t);
        String query = "INSERT INTO `employee` "
                + "(`username`, `password`, `name`, `phoneNumber`, `startDate`, `permission`, `salary`)"
                + " VALUES (?, ?, ?, ?, current_timestamp(), ?, ?)";
        
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setString(1, t.getUsername());
            prStatement.setString(2, t.getPassword());
            prStatement.setString(3, t.getName());
            prStatement.setString(4, t.getPhoneNumber());
            prStatement.setString(5, t.getPermission().getId());
            prStatement.setInt(6, t.getSalary());
            
            prStatement.executeUpdate();
        }
    }
    
    @Override
    public void update(Employee t) throws SQLException{
        validate(t);
        String query = "UPDATE `employee` SET `username` = ?, `password` = ?, `name` = ?, `phoneNumber` = ?, `permission` = ?, `salary` = ? "
                + "WHERE `employeeId` = ?";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setString(1, t.getUsername());
            prStatement.setString(2, t.getPassword());
            prStatement.setString(3, t.getName());
            prStatement.setString(4, t.getPhoneNumber());
            prStatement.setString(5, t.getPermission().getId());
            prStatement.setInt(6, t.getSalary());
            prStatement.setInt(7, t.getEmployeeId());
            
            prStatement.executeUpdate();
        }
    }

    
    @Override
    public void delete(Employee t) throws SQLException {
        validate(t);
        deleteById(t.getEmployeeId());
    }
    
    @Override
    public void deleteById(int id) throws SQLException {
        if (id <= 0) {
            throw new SQLException("employeeId must > 0");
        }
        String query = "DELETE FROM `employee` WHERE `employeeId` = ?";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setInt(1, id);
            prStatement.executeUpdate();
        }
    }
    
    /**
     * find an employee using the username
     * @param username
     * @return
     * @throws SQLException 
     */
    public Employee findByUsername(String username) throws SQLException {
        if (username == null) {
            throw new SQLException("Username cannot be null");
        }
        String query = "SELECT * FROM `employee` WHERE `username` = ?";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setString(1, username);
            try (ResultSet rs = prStatement.executeQuery()) {
                if (rs.next()) {
                    return Employee.getFromResultSet(rs);
                }
            }
        }
        return null;
    }
    
    @Override
    public ArrayList<Employee> searchByKey(String key, String word) throws SQLException {
        if (key == null || word == null || key.trim().isEmpty() || word.trim().isEmpty()) {
            throw new SQLException("Từ khoá không được null/trống");
        }
        ArrayList<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM `employee` WHERE `" + key + "` LIKE ?";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setString(1, "%" + word + "%");
            try (ResultSet rs = prStatement.executeQuery()) {
                while (rs.next()) {
                    Employee e = Employee.getFromResultSet(rs);
                    employees.add(e);
                }
            }
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
        String query = "SELECT * FROM employee"
                + " WHERE permission <> 'inactive';";
        try (Statement statement = conn.createStatement()) {
            try(ResultSet rs = statement.executeQuery(query)) {
                while (rs.next()){
                    Employee e = Employee.getFromResultSet(rs);
                    employees.add(e);
                }
            }
        }
        return employees;
    }

    @Override
    protected void validate(Employee t) throws SQLException {
        if (t == null){ 
            throw new SQLException("Employee object cannot be null");
        }
    }
}
