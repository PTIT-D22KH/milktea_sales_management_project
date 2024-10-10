///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package dao;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import models.Table;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import utils.TableStatus;
//
///**
// * Test class for TableDao
// */
//public class TableDaoTest {
//    TableDao tableDao = new TableDao();
//
//    public TableDaoTest() {
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
//        ArrayList<Table> tables = tableDao.getAll();
//        assertNotNull(tables);
//        assertFalse(tables.isEmpty());
//        Table firstTable = tables.get(0);
//        assertNotNull(firstTable);
//    }
//
//    @Test
//    public void testSaveNull() throws SQLException {
//        Table table = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            tableDao.save(table);
//        });
//        assertEquals("Table rỗng", exception.getMessage());
//    }
//
//    @Test
//    public void testSaveNotNull() throws SQLException {
//        Table table = new Table();
//        table.setName("Test Table");
//        table.setStatus(TableStatus.FREE);
//
//        tableDao.save(table);
//        Table savedTable = tableDao.findByName("Test Table");
//        assertNotNull(savedTable);
//        assertEquals("Test Table", savedTable.getName());
//        assertEquals(TableStatus.FREE, savedTable.getStatus());
//    }
//
//    @Test
//    public void testUpdateNull() throws SQLException {
//        Table table = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            tableDao.update(table);
//        });
//        assertEquals("Table rỗng", exception.getMessage());
//    }
//
//    @Test
//    public void testUpdateNotNull() throws SQLException {
//        Table table = tableDao.findByName("Test Table");
//        assertNotNull(table);
//        table.setStatus(TableStatus.SERVING);
//        tableDao.update(table);
//        Table updatedTable = tableDao.findByName("Test Table");
//        assertEquals(TableStatus.SERVING, updatedTable.getStatus());
//    }
//
//    @Test
//    public void testDelete() throws SQLException {
//        Table table = tableDao.findByName("Test Table");
//        assertNotNull(table);
//        tableDao.delete(table);
//        Table deletedTable = tableDao.findByName("Test Table");
//        assertNull(deletedTable);
//    }
//
//    @Test
//    public void testDeleteById() throws SQLException {
//        Table table = tableDao.findByName("Test Table");
//        assertNotNull(table);
//        tableDao.deleteById(table.getTableId());
//        Table deletedTable = tableDao.findByName("Test Table");
//        assertNull(deletedTable);
//    }
//
//    @Test
//    public void testFindByName() throws SQLException {
//        testSaveNotNull();
//        Table table = tableDao.findByName("Test Table");
//        assertNotNull(table);
//        assertEquals("Test Table", table.getName());
//    }
//
//    @Test
//    public void testSearchByKey() throws SQLException {
//        testSaveNotNull();
//        ArrayList<Table> tables = tableDao.searchByKey("name", "Test");
//        assertNotNull(tables);
//        assertFalse(tables.isEmpty());
//        for (Table table : tables) {
//            assertTrue(table.getName().contains("Test"));
//        }
//    }
//
//    @Test
//    public void testGetRandom() throws SQLException {
//        Table table = tableDao.getRandom();
//        assertNotNull(table);
//    }
//
//    @Test
//    public void testGetById() throws SQLException {
//        Table table = tableDao.getById(1);
//        assertNotNull(table);
//        assertEquals(1, table.getTableId());
//    }
//}