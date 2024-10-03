/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import utils.OrderStatus;
import utils.OrderType;

/**
 *
 * @author DELL
 */
public class Order extends Model{
    private int orderId, employeeId, tableId, customerId;
    private int paidAmount, totalAmount, discount;
    private OrderStatus status;
    private OrderType type;
    private Timestamp orderDate, payDate;
    private Employee employee;
    private Table table;
    private Customer customer;
    
    public static Order getFromResultSet(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setOrderId(rs.getInt("OrderId"));
        o.setEmployeeId(rs.getInt("EmployeeId"));
        o.setTableId(rs.getInt("TableId"));
        o.setType(OrderType.getById(rs.getNString("type")));
        o.setStatus(OrderStatus.getById(rs.getNString("status")));
        o.setOrderDate(rs.getTimestamp("orderDate"));
        o.setPayDate(rs.getTimestamp("payDate"));
        o.setPaidAmount(rs.getInt("paidAmount"));
        o.setTotalAmount(rs.getInt("totalAmount"));
        o.setDiscount(rs.getInt("discount"));
        return o;
    }
    public Order() {
        status = OrderStatus.UNPAID;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId){
        this.orderId = orderId;
    }
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

//    public int getRebate() {
//        return rebate;
//    }
//
//    public void setRebate(int rebate) {
//        this.rebate = rebate;
//    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.employeeId = employee.getEmployeeId();
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
        this.tableId = table.getTableId();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.customerId = customer.getCustomerId();
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", employeeId=" + employeeId + ", tableId=" + tableId + ", customerId=" + customerId + ", discount=" + discount + ", status=" + status + ", type=" + type + ", orderDate=" + orderDate + ", payDate=" + payDate + ", paidAmount=" + paidAmount + ", totalAmount=" + totalAmount + ", employee=" + employee + ", table=" + table + ", customer=" + customer + '}';
    }

   
    
}
