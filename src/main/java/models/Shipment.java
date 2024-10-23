/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dao.EmployeeDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import utils.ShipmentStatus;

/**
 *
 * @author DELL
 */
public class Shipment extends Model{
    private int orderId, customerId, employeeId;
    private ShipmentStatus status;
    private Timestamp startDate, endDate;
    private Order order;
    private Customer customer;
    private Employee employee;
    private int shipCost;
    

    public Shipment() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        
        this.order = order;
        if (order != null) {
            this.orderId = order.getOrderId();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        
        this.customer = customer;
        if (customer != null) {
            this.customerId = customer.getCustomerId();
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        
        this.employee = employee;
        if (employee != null) {
            this.employeeId = employee.getEmployeeId();
        }
    }

    public int getShipCost() {
        return shipCost;
    }

    public void setShipCost(int shipCost) {
        this.shipCost = shipCost;
    }

    
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
    
    public static Shipment getFromResultSet(ResultSet rs) throws SQLException {
        Shipment shipment = new Shipment();
        shipment.setOrderId(rs.getInt("OrderId"));
        shipment.setCustomerId(rs.getInt("CustomerId"));
        shipment.setEmployeeId(rs.getInt("EmployeeId"));
        shipment.setShipCost(rs.getInt("shipCost"));
        shipment.setStatus(ShipmentStatus.getById(rs.getNString("status")));
        shipment.setStartDate(rs.getTimestamp("startDate"));
        shipment.setEndDate(rs.getTimestamp("endDate"));
        return shipment;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                ", status='" + status + '\'' +
                ",shipCost=" + shipCost +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public Object[] toRowTable() {
        System.out.println(this.customer);
        return new Object[]{
            orderId, customer.getName(), customer.getAddress(), employee.getName(), shipCost, status.getName(), startDate, endDate
        };
    }

    @Override
    public String getClassName() {
        return "đơn giao hàng";
    }
    
   
}
