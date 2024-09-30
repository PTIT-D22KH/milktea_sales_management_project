/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.math.BigInteger;
import java.sql.Timestamp;
import utils.OrderStatus;
import utils.OrderType;

/**
 *
 * @author DELL
 */
public class Order extends Model{
    private int orderId, employeeId,tableId,customerId,discount;
    private OrderStatus status;
    private OrderType type;
    private Timestamp orderDate,payDate;
    private BigInteger paidAmount,rebate,totalAmount;
    private Employee employee;
    private Table table;
    
    public Order() {
        status = OrderStatus.UNPAID;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
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

    public BigInteger getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigInteger paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigInteger getRebate() {
        return rebate;
    }

    public void setRebate(BigInteger rebate) {
        this.rebate = rebate;
    }

    public BigInteger getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigInteger totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", employeeId=" + employeeId + ", tableId=" + tableId + ", customerId=" + customerId + ", discount=" + discount + ", status=" + status + ", type=" + type + ", orderDate=" + orderDate + ", payDate=" + payDate + ", paidAmount=" + paidAmount + ", rebate=" + rebate + ", totalAmount=" + totalAmount + ", employee=" + employee + ", table=" + table + '}';
    }
    
    
}
