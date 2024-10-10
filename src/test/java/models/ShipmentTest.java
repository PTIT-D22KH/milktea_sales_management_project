//package models;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.Timestamp;
//import utils.ShipmentStatus;
//
///**
// * Test class for Shipment
// */
//public class ShipmentTest {
//    
//    public ShipmentTest() {
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
//    public void testShipmentConstructor() {
//        Shipment shipment = new Shipment();
//        assertNotNull(shipment);
//    }
//
//    @Test
//    public void testSetAndGetOrderId() {
//        Shipment shipment = new Shipment();
//        shipment.setOrderId(1);
//        assertEquals(1, shipment.getOrderId());
//    }
//
//    @Test
//    public void testSetAndGetCustomerId() {
//        Shipment shipment = new Shipment();
//        shipment.setCustomerId(2);
//        assertEquals(2, shipment.getCustomerId());
//    }
//
//    @Test
//    public void testSetAndGetEmployeeId() {
//        Shipment shipment = new Shipment();
//        shipment.setEmployeeId(3);
//        assertEquals(3, shipment.getEmployeeId());
//    }
//
//    @Test
//    public void testSetAndGetStatus() {
//        Shipment shipment = new Shipment();
//        shipment.setStatus(ShipmentStatus.TOPAY);
//        assertEquals(ShipmentStatus.TOPAY, shipment.getStatus());
//    }
//
//    @Test
//    public void testSetAndGetStartDate() {
//        Shipment shipment = new Shipment();
//        Timestamp startDate = Timestamp.valueOf("2023-01-01 10:00:00");
//        shipment.setStartDate(startDate);
//        assertEquals(startDate, shipment.getStartDate());
//    }
//
//    @Test
//    public void testSetAndGetEndDate() {
//        Shipment shipment = new Shipment();
//        Timestamp endDate = Timestamp.valueOf("2023-01-01 12:00:00");
//        shipment.setEndDate(endDate);
//        assertEquals(endDate, shipment.getEndDate());
//    }
//
//    @Test
//    public void testSetAndGetOrder() {
//        Shipment shipment = new Shipment();
//        Order order = new Order();
//        order.setOrderId(1);
//        shipment.setOrder(order);
//        assertEquals(order, shipment.getOrder());
//        assertEquals(1, shipment.getOrderId());
//    }
//
//    @Test
//    public void testSetAndGetCustomer() {
//        Shipment shipment = new Shipment();
//        Customer customer = new Customer();
//        customer.setCustomerId(2);
//        shipment.setCustomer(customer);
//        assertEquals(customer, shipment.getCustomer());
//        assertEquals(2, shipment.getCustomerId());
//    }
//
//    @Test
//    public void testSetAndGetEmployee() {
//        Shipment shipment = new Shipment();
//        Employee employee = new Employee();
//        employee.setEmployeeId(3);
//        shipment.setEmployee(employee);
//        assertEquals(employee, shipment.getEmployee());
//        assertEquals(3, shipment.getEmployeeId());
//    }
//
//    @Test
//    public void testSetAndGetShipCost() {
//        Shipment shipment = new Shipment();
//        shipment.setShipCost(100);
//        assertEquals(100, shipment.getShipCost());
//    }
//
//    @Test
//    public void testToString() {
//        Shipment shipment = new Shipment();
//        shipment.setOrderId(1);
//        shipment.setCustomerId(2);
//        shipment.setEmployeeId(3);
//        shipment.setStatus(ShipmentStatus.TOPAY);
//        Timestamp startDate = Timestamp.valueOf("2023-01-01 10:00:00");
//        shipment.setStartDate(startDate);
//        Timestamp endDate = Timestamp.valueOf("2023-01-01 12:00:00");
//        shipment.setEndDate(endDate);
//
//        String expected = "Shipment{" +
//                "orderId=" + shipment.getOrderId() +
//                ", customerId=" + shipment.getCustomerId() +
//                ", employeeId=" + shipment.getEmployeeId() +
//                ", status='" + shipment.getStatus() + '\'' +
//                ", startDate=" + shipment.getStartDate() +
//                ", endDate=" + shipment.getEndDate() +
//                '}';
//
//        assertEquals(expected, shipment.toString());
//    }
//}