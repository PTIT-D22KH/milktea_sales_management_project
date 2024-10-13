package models;

import java.sql.Date;
import utils.EmployeePermission;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends Model {
    
    private int employeeId, salary;
    private String username, password, name, phoneNumber;
    private EmployeePermission permission;
    private Date startDate;
    
    public Employee(){
        
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = Math.max(0, salary);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmployeePermission getPermission() {
        return permission;
    }

    public void setPermission(EmployeePermission permission) {
        this.permission = permission;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public static Employee getFromResultSet(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setEmployeeId(rs.getInt("employeeId"));
        e.setUsername(rs.getNString("username"));
        e.setPassword(rs.getNString("password"));
        e.setName(rs.getNString("name"));
        e.setPhoneNumber(rs.getNString("phoneNumber"));
        e.setStartDate(rs.getDate("startDate"));
        e.setPermission(EmployeePermission.getById(rs.getNString("permission")));
        e.setSalary(rs.getInt("salary"));
        return e;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", salary=" + salary + ", username=" + username + ", password=" + password + ", name=" + name + ", phoneNumber=" + phoneNumber + ", permission=" + permission + ", startDate=" + startDate + '}';
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
            employeeId, name, username, password, phoneNumber,
        startDate, permission.getName(), salary
        };
        
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String getClassName() {
        return "nhân viên";
    }
}
