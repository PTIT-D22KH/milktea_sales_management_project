///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package dao;
//
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import models.Shipment;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import utils.ShipmentStatus;
//
///**
// * Test class for ShipmentDao
// */
//public class ShipmentDaoTest {
//    ShipmentDao shipmentDao = new ShipmentDao();
//
//    public ShipmentDaoTest() {
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
//        ArrayList<Shipment> shipments = shipmentDao.getAll();
//        assertNotNull(shipments);
//        assertFalse(shipments.isEmpty());
//        Shipment firstShipment = shipments.get(0);
//        System.out.println(firstShipment);
//        assertNotNull(firstShipment);
//    }
//
//    @Test
//    public void testSaveNull() throws SQLException {
//        Shipment shipment = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            shipmentDao.save(shipment);
//        });
//        assertEquals("Shipment rỗng", exception.getMessage());
//    }
//
//    @Test
//    public void testSaveNotNull() throws SQLException {
//        Shipment shipment = new Shipment();
//        shipment.setOrderId(1);
//        shipment.setCustomerId(1);
//        shipment.setEmployeeId(1);
//        shipment.setShipCost(100);
//        shipment.setStatus(ShipmentStatus.TOPAY);
//        shipment.setStartDate(Timestamp.valueOf("2023-01-01 10:00:00"));
//
//        shipmentDao.save(shipment);
//        Shipment savedShipment = shipmentDao.getById(shipment.getOrderId());
//        assertNotNull(savedShipment);
//        assertEquals(ShipmentStatus.TOPAY, savedShipment.getStatus());
//    }
//
//    @Test
//    public void testUpdateNull() throws SQLException {
//        Shipment shipment = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            shipmentDao.update(shipment);
//        });
//        assertEquals("Shipment rỗng", exception.getMessage());
//    }
//
//    @Test
//    public void testUpdateNotNull() throws SQLException {
//        Shipment shipment = shipmentDao.getById(1);
//        assertNotNull(shipment);
//        shipment.setStatus(ShipmentStatus.COMPLETED);
//        shipmentDao.update(shipment);
//        Shipment updatedShipment = shipmentDao.getById(1);
//        assertEquals(ShipmentStatus.COMPLETED, updatedShipment.getStatus());
//    }
//
//    @Test
//    public void testDelete() throws SQLException {
//        Shipment shipment = new Shipment();
//        shipment.setOrderId(1);
//
//        shipmentDao.delete(shipment);
//        Shipment deletedShipment = shipmentDao.getById(1);
//        assertNull(deletedShipment);
//    }
//
//    @Test
//    public void testDeleteById() throws SQLException {
//        shipmentDao.deleteById(1);
//        Shipment deletedShipment = shipmentDao.getById(1);
//        assertNull(deletedShipment);
//    }
//
//    @Test
//    public void testDeleteByIdCustomer() throws SQLException {
//        shipmentDao.deleteByIdCustomer(1);
//        ArrayList<Shipment> shipments = shipmentDao.getAll();
//        for (Shipment shipment : shipments) {
//            assertNotEquals(1, shipment.getCustomerId());
//        }
//    }
//
//    @Test
//    public void testSearchByKey() throws SQLException {
//        ArrayList<Shipment> shipments = shipmentDao.searchByKey("status", "toreceive");
//        assertNotNull(shipments);
//        assertFalse(shipments.isEmpty());
//        for (Shipment shipment : shipments) {
//            System.out.println(shipment);
//            assertEquals(ShipmentStatus.TORECEIVE, shipment.getStatus());
//        }
//    }
//
//    @Test
//    public void testGetById() throws SQLException {
//        Shipment shipment = shipmentDao.getById(2);
//        assertNotNull(shipment);
//        assertEquals(2, shipment.getOrderId());
//    }
//}