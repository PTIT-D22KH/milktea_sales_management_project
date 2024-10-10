///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package dao;
//
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import models.Order;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import utils.OrderStatus;
//import utils.OrderType;
//
///**
// * Test class for OrderDao
// */
//public class OrderDaoTest {
//    OrderDao orderDao = new OrderDao();
//
//    public OrderDaoTest() {
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
//    public void testGetAll() throws SQLException {
//        ArrayList<Order> orders = orderDao.getAll();
//        assertNotNull(orders);
//        assertFalse(orders.isEmpty());
//        Order lastOrder = orders.get(orders.size() - 1);
//        assertNotNull(lastOrder);
////        System.out.println(lastOrder);
//    }
//
//    @Test
//    public void testGetById() throws SQLException {
//        Order order = orderDao.getById(1);
//        assertNotNull(order);
//        assertEquals(1, order.getOrderId());
//        assertEquals(OrderStatus.UNPAID, order.getStatus());
//        assertEquals(OrderType.LOCAL, order.getType());
//    }
//
//    @Test
//    public void testSaveNull() throws SQLException {
//        Order order = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            orderDao.save(order);
//        });
//        assertEquals("Order rỗng", exception.getMessage());
//    }
//
//    @Test
//    public void testSaveNotNull() throws SQLException {
//        Order order = new Order();
//        order.setEmployeeId(1);
//        order.setTableId(1);
//        order.setCustomerId(1);
//        order.setPaidAmount(100);
//        order.setTotalAmount(200);
//        order.setDiscount(10);
//        order.setStatus(OrderStatus.UNPAID);
//        order.setType(OrderType.LOCAL);
//        order.setOrderDate(Timestamp.valueOf("2023-01-01 10:00:00"));
//        order.setPayDate(Timestamp.valueOf("2023-01-01 12:00:00"));
//
//        orderDao.save(order);
////        System.out.println(order);
//        Order savedOrder = orderDao.getById(35);
//        assertNotNull(savedOrder);
//        assertEquals(OrderStatus.UNPAID, savedOrder.getStatus());
//        assertEquals(OrderType.LOCAL, savedOrder.getType());
//    }
//
//    @Test
//    public void testUpdateNull() throws SQLException {
//        Order order = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            orderDao.update(order);
//        });
//        assertEquals("Order rỗng", exception.getMessage());
//    }
//
//    @Test
//    public void testUpdateNotNull() throws SQLException {
//        Order order = orderDao.getById(36);
//        assertNotNull(order);
//        order.setStatus(OrderStatus.PAID);
//        orderDao.update(order);
//        Order updatedOrder = orderDao.getById(36);
//        assertEquals(OrderStatus.PAID, updatedOrder.getStatus());
//    }
//
//    @Test
//    public void testDelete() throws SQLException {
//        // Ensure the order with ID 1 exists
//        Order order = orderDao.getById(37);
//        if (order == null) {
//            // Create and save a new order with ID 1 if it does not exist
//            order = new Order();
////            order.setOrderId(38);
//            order.setEmployeeId(1);
//            order.setTableId(1);
//            order.setCustomerId(1);
//            order.setPaidAmount(100);
//            order.setTotalAmount(200);
//            order.setDiscount(10);
//            order.setStatus(OrderStatus.UNPAID);
//            order.setType(OrderType.LOCAL);
//            order.setOrderDate(Timestamp.valueOf("2023-01-01 10:00:00"));
//            order.setPayDate(Timestamp.valueOf("2023-01-01 12:00:00"));
//            orderDao.save(order);
//        }
//
//        // Delete the order
//        orderDao.delete(order);
//
//        // Verify that the order has been deleted
//        Order deletedOrder = orderDao.getById(37);
//        assertNull(deletedOrder);
//    }
//
//    @Test
//    public void testDeleteById() throws SQLException {
//        // First, save a new order to ensure there is an order with a known ID to delete
//        Order order = new Order();
//        order.setEmployeeId(1);
//        order.setTableId(1);
//        order.setCustomerId(1);
//        order.setPaidAmount(100);
//        order.setTotalAmount(200);
//        order.setDiscount(10);
//        order.setStatus(OrderStatus.UNPAID);
//        order.setType(OrderType.LOCAL);
//        order.setOrderDate(Timestamp.valueOf("2023-01-01 10:00:00"));
//        order.setPayDate(Timestamp.valueOf("2023-01-01 12:00:00"));
//        orderDao.save(order);
//
//        // Retrieve the newly saved order to get its ID
//        Order savedOrder = orderDao.getById(38);
//        assertNotNull(savedOrder);
//        int orderIdToDelete = savedOrder.getOrderId();
//
//        // Delete the order by ID
//        orderDao.deleteById(orderIdToDelete);
//
//        // Verify that the order has been deleted
//        Order deletedOrder = orderDao.getById(38);
//        assertNull(deletedOrder);
//    }
//
//    @Test
//    public void testSearchByKey() throws SQLException {
//        ArrayList<Order> orders = orderDao.searchByKey("status", "paid");
//        assertNotNull(orders);
//        assertFalse(orders.isEmpty());
//        for (Order order : orders) {
//            assertEquals(OrderStatus.PAID, order.getStatus());
//        }
//    }
//
//    @Test
//    public void testGetRandom() throws SQLException {
//        Order order = orderDao.getRandom();
//        assertNotNull(order);
//        assertEquals(OrderStatus.UNPAID, order.getStatus());
//    }
//}