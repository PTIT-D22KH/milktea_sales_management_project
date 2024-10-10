///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package utils;
//
//import java.io.IOException;
//import java.util.Properties;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
///**
// *
// * @author P51
// */
//public class ConfigLoaderTest {
//    private ConfigLoader cfgLoader;
//    public ConfigLoaderTest() {
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
//        cfgLoader = ConfigLoader.getInstance();
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
//    /**
//     * Test Singleton pattern from Properties class in java
//     */
//    @Test
//    public void testSingleTonInstance() {
//        ConfigLoader a = ConfigLoader.getInstance();
//        ConfigLoader b = ConfigLoader.getInstance();
//        assertSame(a, b, "Instances should be the same (singleton pattern)");
//    }
//    
//    /**
//     * Test get property from PropertyLoader attribute
//     */
//    @Test
//    public void testGetProperty() {
//        String expectedDataDriverClass = "com.mysql.cj.jdbc.Driver";
//        String expectedJdbc = "mysql";
//        String expectedHost = "localhost";
//        String expectedPort = "3306";
//        String expectedUserName = "test";
//        String expectedPassword = "test_password";
//        String expectedName = "test_db";
//        String expectedMysqlDump = "test_mysql_dump_path";
//        String expectedMysql = "test_mysql_path";
//        String expectedBackupPath = "test_backup_path";
//        
//        assertEquals(null, cfgLoader.getProperty("database.driver_classs"), "Property value should be null");
//        assertEquals(expectedDataDriverClass, cfgLoader.getProperty("database.driver_class"), "Property value should match the expected data driver class");
//        assertEquals(expectedJdbc, cfgLoader.getProperty("database.jdbc"), "Property value should match the expected JDBC");
//        assertEquals(expectedHost, cfgLoader.getProperty("database.host"), "Property value should match the expected host");
//        assertEquals(expectedPort, cfgLoader.getProperty("database.port"), "Property value should match the expected port");
//        assertEquals(expectedUserName, cfgLoader.getProperty("database.username"), "Property value should match the expected username");
//        assertEquals(expectedPassword, cfgLoader.getProperty("database.password"), "Property value should match the expected password");
//        assertEquals(expectedName, cfgLoader.getProperty("database.name"), "Property value should match the expected database name");
//        assertEquals(expectedMysqlDump, cfgLoader.getProperty("path.mysqldump"), "Property value should match the expected MySQL dump path");
//        assertEquals(expectedMysql, cfgLoader.getProperty("path.mysql"), "Property value should match the expected MySQL path");
//        assertEquals(expectedBackupPath, cfgLoader.getProperty("path.backup"), "Property value should match the expected backup path");
//
//    }
//    
//    /**
//     * Test getter of propertyLoader
//     */
//    @Test
//    public void testGetPropertyLoader() {
//        Properties properties = cfgLoader.getPropertyLoader();
//        assertNotNull(properties, "Properties should not be null");
//        assertFalse(properties.isEmpty(), "Properties should not be empty");
//    }
//    
////    @Test
////    public void testReadConfigFile() throws IOException {
////        ConfigLoader mockCfgLoader = spy(new ConfigLoader());
////        doNothing().when(mockCfgLoader).readConfigFile();
////        mockCfgLoader.readConfigFile();
////        verify(mockCfgLoader, times(1)).readConfigFile();
////    }
//    
//
//    
//}
