/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author P51
 */
public class CustomerTest {
    public CustomerTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testConstructorNoParameter() {
        Customer customer = new Customer();
        assertEquals(customer.getAddress(), "");
        assertEquals(customer.getName(), "");
        assertEquals(customer.getPhoneNumber(), "");
    }
    
    @Test
    public void testGetterAndSetterCustomerId() {
        Customer customer = new Customer();
        int customerId = 1;
        customer.setCustomerId(customerId);
        assertEquals(customer.getCustomerId(), customerId);
    }
    
    @Test
    public void testGetterAndSetterAddress() {
        Customer customer = new Customer();
        String address = "Hanoi";
        customer.setAddress(address);
        assertEquals(customer.getAddress(), address);
    }
    
    @Test
    public void testGetterAndSetterName() {
        Customer customer = new Customer();
        String name = "Nguyen Van A";
        customer.setName(name);
        assertEquals(customer.getName(), name);
    }
    
    @Test
    public void testGetterAndSetterPhoneNumber() {
        Customer customer = new Customer();
        String phoneNumber = "09007508";
        customer.setPhoneNumber(phoneNumber);
        assertEquals(customer.getPhoneNumber(), phoneNumber);
    }
    
//    @Test
//    public void testToString() {
//        int customerId = 1;
//        String address = "Hanoi";
//        String name = "Nguyen Van A";
//        String phoneNumber = "09007508";
//        Customer customer = new Customer();
//        customer.setAddress(address);
//        customer.setCustomerId(customerId);
//        customer.setName(name);
//        customer.setPhoneNumber(phoneNumber);
//        String res = "" + customerId + " " + phoneNumber + " " + name + " " + address;
////        System.out.println(res);
//        assertEquals(customer.toString(), res);
//    }
}
