//package utils;
//
//import utils.DatabaseConnector;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import utils.ConfigLoader;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class DatabaseConnectorTest {
//
//    private ConfigLoader mockConfigLoader;
//    private MockedStatic<DriverManager> mockedDriverManager;
//    private DatabaseConnector databaseConnector;
//
//    @BeforeEach
//    public void setUp() throws ClassNotFoundException, SQLException {
//        mockConfigLoader = mock(ConfigLoader.class);
//        when(mockConfigLoader.getProperty("database.host")).thenReturn("localhost");
//        when(mockConfigLoader.getProperty("database.port")).thenReturn("3306");
//        when(mockConfigLoader.getProperty("database.username")).thenReturn("test");
//        when(mockConfigLoader.getProperty("database.password")).thenReturn("password");
//        when(mockConfigLoader.getProperty("database.name")).thenReturn("test_db");
//        when(mockConfigLoader.getProperty("database.driver_class")).thenReturn("com.mysql.cj.jdbc.Driver");
//        when(mockConfigLoader.getProperty("database.jdbc")).thenReturn("mysql");
//
//        // Mock DriverManager to return a mock Connection
//        Connection mockConnection = mock(Connection.class);
//        mockedDriverManager = mockStatic(DriverManager.class);
//        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
//
//        // Use the new constructor for testing
//        databaseConnector = new DatabaseConnector(mockConfigLoader);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // Close the static mock
//        mockedDriverManager.close();
//
//        // Reset the singleton instance for the next test
//        DatabaseConnector.resetInstance();
//    }
//
//    @Test
//    public void testSingletonInstance() {
//        DatabaseConnector.resetInstance(); // Ensure the singleton is reset before the test
//        DatabaseConnector instance1 = DatabaseConnector.getInstance();
//        DatabaseConnector instance2 = DatabaseConnector.getInstance();
//        assertSame(instance1, instance2, "Instances should be the same (singleton pattern)");
//    }
//
//    @Test
//    public void testGetConnection() {
//        Connection connection = databaseConnector.getConn();
//        assertNotNull(connection, "Connection should not be null");
//    }
//
//    @Test
//    public void testClassNotFoundException() {
//        try {
//            when(mockConfigLoader.getProperty("database.driver_class")).thenReturn("invalid.DriverClass");
//            new DatabaseConnector(mockConfigLoader);
//            fail("Expected RuntimeException due to ClassNotFoundException");
//        } catch (RuntimeException e) {
//            assertTrue(e.getCause() instanceof ClassNotFoundException, "Cause should be ClassNotFoundException");
//        }
//    }
//
//    @Test
//    public void testSQLException() throws SQLException {
//        try {
//            when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenThrow(new SQLException("Test SQLException"));
//            new DatabaseConnector(mockConfigLoader);
//            fail("Expected RuntimeException due to SQLException");
//        } catch (RuntimeException e) {
//            assertTrue(e.getCause() instanceof SQLException, "Cause should be SQLException");
//        }
//    }
//}