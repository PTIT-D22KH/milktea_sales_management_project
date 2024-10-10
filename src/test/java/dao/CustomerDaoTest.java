///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package dao;
//
//import models.Customer;
//import org.junit.jupiter.api.*;
//import org.mockito.*;
//import java.sql.*;
//import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
///**
// *
// * @author P51
// */
//public class CustomerDaoTest {
//    @Mock
//    private Connection mockConnection;
//
//    @Mock
//    private PreparedStatement mockPreparedStatement;
//
//    @Mock
//    private ResultSet mockResultSet;
//
//    @Mock
//    private Statement mockStatement;
//
//    @InjectMocks
//    private CustomerDao customerDao;
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        MockitoAnnotations.openMocks(this);
//        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
//        when(mockConnection.createStatement()).thenReturn(mockStatement);
//        customerDao = new CustomerDao(mockConnection);
//    }
//
//    @Test
//    public void testGetAll() throws SQLException {
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
//        when(mockResultSet.getInt("customerId")).thenReturn(1);
//        when(mockResultSet.getNString("phoneNumber")).thenReturn("1234567890");
//        when(mockResultSet.getNString("name")).thenReturn("John Doe");
//        when(mockResultSet.getNString("address")).thenReturn("123 Main St");
//
//        ArrayList<Customer> customers = customerDao.getAll();
//        assertNotNull(customers);
//        assertEquals(1, customers.size());
//        Customer customer = customers.get(0);
//        assertEquals(1, customer.getCustomerId());
//        assertEquals("1234567890", customer.getPhoneNumber());
//        assertEquals("John Doe", customer.getName());
//        assertEquals("123 Main St", customer.getAddress());
//    }
//
//    @Test
//    public void testGetById() throws SQLException {
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true);
//        when(mockResultSet.getInt("customerId")).thenReturn(1);
//        when(mockResultSet.getNString("phoneNumber")).thenReturn("1234567890");
//        when(mockResultSet.getNString("name")).thenReturn("John Doe");
//        when(mockResultSet.getNString("address")).thenReturn("123 Main St");
//
//        Customer customer = customerDao.getById(1);
//        assertNotNull(customer);
//        assertEquals(1, customer.getCustomerId());
//        assertEquals("1234567890", customer.getPhoneNumber());
//        assertEquals("John Doe", customer.getName());
//        assertEquals("123 Main St", customer.getAddress());
//    }
//
//    @Test
//    public void testSave() throws SQLException {
//        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//
//        Customer customer = new Customer();
//        customer.setPhoneNumber("1234567890");
//        customer.setName("John Doe");
//        customer.setAddress("123 Main St");
//        customerDao.save(customer);
//
//        verify(mockPreparedStatement, times(1)).executeUpdate();
//    }
//
//    @Test
//    public void testUpdate() throws SQLException {
//        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//
//        Customer customer = new Customer();
//        customer.setCustomerId(1);
//        customer.setPhoneNumber("1234567890");
//        customer.setName("John Doe");
//        customer.setAddress("123 Main St");
//        customerDao.update(customer);
//
//        verify(mockPreparedStatement, times(1)).executeUpdate();
//    }
//
//    @Test
//    public void testDelete() throws SQLException {
//        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//
//        Customer customer = new Customer();
//        customer.setCustomerId(1);
//        customerDao.delete(customer);
//
//        verify(mockPreparedStatement, times(1)).executeUpdate();
//    }
//
////    @Test
////    public void testDeleteById() throws SQLException {
////        
////
////        customerDao.deleteById(3);
////        
////    }
//    @Test
//    public void testDeleteById() throws SQLException {
//        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//
//        customerDao.deleteById(1);
//
//        verify(mockPreparedStatement, times(1)).executeUpdate();
//    }
//}