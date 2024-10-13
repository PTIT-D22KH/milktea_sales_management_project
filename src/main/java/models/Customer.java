/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author P51
 */
public class Customer extends Model{
    private int customerId;
    private String phoneNumber, name, address;
    
    public Customer() { 
        this.phoneNumber = this.name  = this.address = "";
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public static Customer getFromResultSet(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(result.getInt("customerId"));
        customer.setAddress(result.getNString("address"));
        customer.setName(result.getNString("name"));
        customer.setPhoneNumber(result.getNString("phoneNumber"));
        return customer;
    }
    
    @Override
    public String toString (){
        return name + "\nSDT:" + phoneNumber + "\nDia chi:" + address;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[] {
            customerId, name, phoneNumber, address
        };
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String getClassName(){ 
        return "khách hàng";
    }
}
