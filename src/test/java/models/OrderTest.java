//package models;
//
//import java.sql.Date;
//import java.sql.SQLException;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.Timestamp;
//import utils.EmployeePermission;
//import utils.OrderStatus;
//import utils.OrderType;
//
///**
// * Test class for Order
// */
//public class OrderTest {
//    
//    public OrderTest() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
//    @Test
//    public void testOrderConstructor() {
//        Order order = new Order();
//        assertNotNull(order);
//        assertEquals(OrderStatus.UNPAID, order.getStatus());
//    }
//
//    @Test
//    public void testSetAndGetOrderId() {
//        Order order = new Order();
//        order.setOrderId(1);
//        assertEquals(1, order.getOrderId());
//    }
//
//    @Test
//    public void testSetAndGetEmployeeId() {
//        Order order = new Order();
//        order.setEmployeeId(2);
//        assertEquals(2, order.getEmployeeId());
//    }
//
//    @Test
//    public void testSetAndGetTableId() throws  SQLException{
//        Order order = new Order();
//        order.setTableId(3);
//        assertEquals(3, order.getTableId());
//    }
//
//    @Test
//    public void testSetAndGetCustomerId() {
//        Order order = new Order();
//        order.setCustomerId(4);
//        assertEquals(4, order.getCustomerId());
//    }
//
//    @Test
//    public void testSetAndGetDiscount() {
//        Order order = new Order();
//        order.setDiscount(10);
//        assertEquals(10, order.getDiscount());
//    }
//
//    @Test
//    public void testSetAndGetStatus() {
//        Order order = new Order();
//        order.setStatus(OrderStatus.PAID);
//        assertEquals(OrderStatus.PAID, order.getStatus());
//    }
//
//    @Test
//    public void testSetAndGetType() {
//        Order order = new Order();
//        order.setType(OrderType.LOCAL);
//        assertEquals(OrderType.LOCAL, order.getType());
//    }
//
//    @Test
//    public void testSetAndGetOrderDate() {
//        Order order = new Order();
//        Timestamp orderDate = Timestamp.valueOf("2023-01-01 10:00:00");
//        order.setOrderDate(orderDate);
//        assertEquals(orderDate, order.getOrderDate());
//    }
//
//    @Test
//    public void testSetAndGetPayDate() {
//        Order order = new Order();
//        Timestamp payDate = Timestamp.valueOf("2023-01-01 12:00:00");
//        order.setPayDate(payDate);
//        assertEquals(payDate, order.getPayDate());
//    }
//
//    @Test
//    public void testSetAndGetPaidAmount() {
//        Order order = new Order();
//        order.setPaidAmount(1000);
//        assertEquals(1000, order.getPaidAmount());
//    }
//
//    @Test
//    public void testSetAndGetTotalAmount() {
//        Order order = new Order();
//        order.setTotalAmount(1200);
//        assertEquals(1200, order.getTotalAmount());
//    }
//
//    @Test
//    public void testSetAndGetEmployee() {
//        Order order = new Order();
//        Employee employee = new Employee();
//        employee.setEmployeeId(2);
//        order.setEmployee(employee);
//        assertEquals(employee, order.getEmployee());
//        assertEquals(2, order.getEmployeeId());
//    }
//
//    @Test
//    public void testSetAndGetTable() {
//        Order order = new Order();
//        Table table = new Table();
//        table.setTableId(3);
//        order.setTable(table);
//        assertEquals(table, order.getTable());
//        assertEquals(3, order.getTableId());
//    }
//
//    @Test
//    public void testSetAndGetCustomer() {
//        Order order = new Order();
//        Customer customer = new Customer();
//        customer.setCustomerId(4);
//        order.setCustomer(customer);
//        assertEquals(customer, order.getCustomer());
//        assertEquals(4, order.getCustomerId());
//    }
//
//    @Test
//    public void testToString() throws SQLException{
//        Order order = new Order();
//        order.setOrderId(1);
//        order.setEmployeeId(2);
//        order.setTableId(3);
//        order.setCustomerId(4);
//        order.setDiscount(10);
//        order.setStatus(OrderStatus.PAID);
//        order.setType(OrderType.LOCAL);
//        Timestamp orderDate = Timestamp.valueOf("2023-01-01 10:00:00");
//        order.setOrderDate(orderDate);
//        Timestamp payDate = Timestamp.valueOf("2023-01-01 12:00:00");
//        order.setPayDate(payDate);
//        order.setPaidAmount(1000);
//        order.setTotalAmount(1200);
//
//        Employee employee = new Employee();
//        employee.setEmployeeId(2);
//        employee.setSalary(50000);
//        employee.setUsername("john_doe");
//        employee.setPassword("password123");
//        employee.setName("John Doe");
//        employee.setPhoneNumber("1234567890");
//        employee.setPermission(EmployeePermission.MANAGER);
//        employee.setStartDate(Date.valueOf("2020-01-01"));
//        order.setEmployee(employee);
//
//        Table table = new Table();
//        table.setTableId(3);
//        table.setName("Table 1");
//        order.setTable(table);
//
//        Customer customer = new Customer();
//        customer.setCustomerId(4);
//        customer.setName("Jane Doe");
//        customer.setPhoneNumber("0987654321");
//        order.setCustomer(customer);
//
//        String expected = "Order{" +
//                "orderId=" + order.getOrderId() +
//                ", employeeId=" + order.getEmployeeId() +
//                ", tableId=" + order.getTableId() +
//                ", customerId=" + order.getCustomerId() +
//                ", discount=" + order.getDiscount() +
//                ", status=" + order.getStatus() +
//                ", type=" + order.getType() +
//                ", orderDate=" + order.getOrderDate() +
//                ", payDate=" + order.getPayDate() +
//                ", paidAmount=" + order.getPaidAmount() +
//                ", totalAmount=" + order.getTotalAmount() +
//                ", employee=" + order.getEmployee().toString() +
//                ", table=" + order.getTable().toString() +
//                ", customer=" + order.getCustomer().toString() +
//                '}';
//
//        assertEquals(expected, order.toString());
//    }
//}