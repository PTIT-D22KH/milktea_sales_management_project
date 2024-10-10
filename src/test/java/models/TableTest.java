//package models;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import utils.TableStatus;
//
///**
// * Test class for Table
// */
//public class TableTest {
//    
//    public TableTest() {
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
//    public void testTableConstructor() {
//        Table table = new Table();
//        assertNotNull(table);
//    }
//
//    @Test
//    public void testSetAndGetTableId() {
//        Table table = new Table();
//        table.setTableId(1);
//        assertEquals(1, table.getTableId());
//    }
//
//    @Test
//    public void testSetAndGetName() {
//        Table table = new Table();
//        table.setName("Table 1");
//        assertEquals("Table 1", table.getName());
//    }
//
//    @Test
//    public void testSetAndGetStatus() {
//        Table table = new Table();
//        table.setStatus(TableStatus.SERVING);
//        assertEquals(TableStatus.SERVING, table.getStatus());
//    }
//
//    @Test
//    public void testToString() {
//        Table table = new Table();
//        table.setName("Table 1");
//
//        String expected = "Table 1";
//        assertEquals(expected, table.toString());
//    }
//}