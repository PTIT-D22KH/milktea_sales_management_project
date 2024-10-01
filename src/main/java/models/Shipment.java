/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

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

    @Override
    public String toString() {
        return "Shipment{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
    
   
}
