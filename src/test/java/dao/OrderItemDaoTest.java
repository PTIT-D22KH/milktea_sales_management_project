///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package dao;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import models.OrderItem;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Test class for OrderItemDao
// */
//public class OrderItemDaoTest {
//    OrderItemDao orderItemDao = new OrderItemDao();
//
//    public OrderItemDaoTest() {
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
//        ArrayList<OrderItem> orderItems = orderItemDao.getAll();
//        assertNotNull(orderItems);
//        assertFalse(orderItems.isEmpty());
//        OrderItem firstOrderItem = orderItems.get(0);
//        assertNotNull(firstOrderItem);
//    }
//
//    @Test
//    public void testSaveNull() throws SQLException {
//        OrderItem orderItem = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            orderItemDao.save(orderItem);
//        });
//        assertEquals("Order Item rỗng", exception.getMessage());
//    }
//
//    @Test
//    public void testSaveNotNull() throws SQLException {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOrderId(1);
//        orderItem.setFoodItemId(1);
//        orderItem.setToppingId(1);
//        orderItem.setQuantity(2);
//        orderItem.setFoodPrice(100);
//        orderItem.setToppingPrice(50);
//        orderItem.setNote("Extra spicy");
//
//        orderItemDao.save(orderItem);
//        // Uncomment and adjust the following lines as needed
//        // OrderItem savedOrderItem = orderItemDao.getById(orderItem.getOrderId());
//        // assertNotNull(savedOrderItem);
//        // assertEquals(2, savedOrderItem.getQuantity());
//    }
//
//    @Test
//    public void testUpdateNull() throws SQLException {
//        OrderItem orderItem = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            orderItemDao.update(orderItem);
//        });
//        assertEquals("Order Item rỗng", exception.getMessage());
//    }
//
//    @Test
//    public void testUpdateNotNull() throws SQLException {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOrderId(1);
//        orderItem.setFoodItemId(1);
//        orderItem.setToppingId(1);
//        orderItem.setQuantity(3);
//        orderItem.setFoodPrice(100);
//        orderItem.setToppingPrice(50);
//        orderItem.setNote("Less spicy");
//
//        orderItemDao.update(orderItem);
//        // Uncomment and adjust the following lines as needed
//        // OrderItem updatedOrderItem = orderItemDao.getById(orderItem.getOrderId());
//        // assertNotNull(updatedOrderItem);
//        // assertEquals(3, updatedOrderItem.getQuantity());
//    }
//
//    @Test
//    public void testDelete() throws SQLException {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOrderId(1);
//        orderItem.setFoodItemId(1);
//        orderItem.setToppingId(1);
//
//        orderItemDao.delete(orderItem);
//        // Uncomment and adjust the following lines as needed
//        // OrderItem deletedOrderItem = orderItemDao.getById(orderItem.getOrderId());
//        // assertNull(deletedOrderItem);
//    }
//
//    @Test
//    public void testGetByIdOrder() throws SQLException {
//        ArrayList<OrderItem> orderItems = orderItemDao.getByIdOrder(1);
//        assertNotNull(orderItems);
//        assertFalse(orderItems.isEmpty());
//        OrderItem firstOrderItem = orderItems.get(0);
//        assertNotNull(firstOrderItem);
//    }
//
//    @Test
//    public void testSearchByKey() throws SQLException {
//        ArrayList<OrderItem> orderItems = orderItemDao.searchByKey("note", "spicy");
//        assertNotNull(orderItems);
//        assertFalse(orderItems.isEmpty());
//        for (OrderItem orderItem : orderItems) {
//            assertTrue(orderItem.getNote().contains("spicy"));
//        }
//    }
//}