//package models;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import utils.EmployeePermission;
//
//import java.sql.Date;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import static org.mockito.Mockito.*;
//
///**
// * Test class for Employee
// */
//public class EmployeeTest {
//    
//    public EmployeeTest() {
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
//    public void testEmployeeConstructor() {
//        Employee e = new Employee();
//        assertNotNull(e);
//    }
//
//    @Test
//    public void testSetAndGetEmployeeId() {
//        Employee e = new Employee();
//        e.setEmployeeId(1);
//        assertEquals(1, e.getEmployeeId());
//    }
//
//    @Test
//    public void testSetAndGetSalary() {
//        Employee e = new Employee();
//        e.setSalary(50000);
//        assertEquals(50000, e.getSalary());
//        e.setSalary(-100);
//        assertEquals(0, e.getSalary()); // Salary should not be negative
//    }
//
//    @Test
//    public void testSetAndGetUsername() {
//        Employee e = new Employee();
//        e.setUsername("johndoe");
//        assertEquals("johndoe", e.getUsername());
//    }
//
//    @Test
//    public void testSetAndGetPassword() {
//        Employee e = new Employee();
//        e.setPassword("password123");
//        assertEquals("password123", e.getPassword());
//    }
//
//    @Test
//    public void testSetAndGetName() {
//        Employee e = new Employee();
//        e.setName("John Doe");
//        assertEquals("John Doe", e.getName());
//    }
//
//    @Test
//    public void testSetAndGetPhoneNumber() {
//        Employee e = new Employee();
//        e.setPhoneNumber("1234567890");
//        assertEquals("1234567890", e.getPhoneNumber());
//    }
//
//    @Test
//    public void testSetAndGetPermission() {
//        Employee e = new Employee();
//        e.setPermission(EmployeePermission.STAFF);
//        assertEquals(EmployeePermission.STAFF, e.getPermission());
//    }
//
//    @Test
//    public void testSetAndGetStartDate() {
//        Employee e = new Employee();
//        Date date = Date.valueOf("2020-01-01");
//        e.setStartDate(date);
//        assertEquals(date, e.getStartDate());
//    }
//
//    @Test
//    public void testGetFromResultSet() throws SQLException {
//        ResultSet rs = mock(ResultSet.class);
//        when(rs.getInt("employeeId")).thenReturn(1);
//        when(rs.getNString("username")).thenReturn("johndoe");
//        when(rs.getNString("password")).thenReturn("password123");
//        when(rs.getNString("name")).thenReturn("John Doe");
//        when(rs.getNString("phoneNumber")).thenReturn("1234567890");
//        when(rs.getDate("startDate")).thenReturn(Date.valueOf("2020-01-01"));
//        when(rs.getNString("permission")).thenReturn("STAFF");
//        when(rs.getInt("salary")).thenReturn(50000);
//
//        Employee e = Employee.getFromResultSet(rs);
//
//        assertEquals(1, e.getEmployeeId());
//        assertEquals("johndoe", e.getUsername());
//        assertEquals("password123", e.getPassword());
//        assertEquals("John Doe", e.getName());
//        assertEquals("1234567890", e.getPhoneNumber());
//        assertEquals(Date.valueOf("2020-01-01"), e.getStartDate());
//        assertEquals(EmployeePermission.STAFF, e.getPermission());
//        assertEquals(50000, e.getSalary());
//    }
//
//    @Test
//    public void testToString() {
//        Employee e = new Employee();
//        e.setEmployeeId(1);
//        e.setUsername("johndoe");
//        e.setPassword("password123");
//        e.setName("John Doe");
//        e.setPhoneNumber("1234567890");
//        e.setPermission(EmployeePermission.STAFF);
//        e.setSalary(50000);
//        e.setStartDate(Date.valueOf("2020-01-01"));
//
//        String expected = "Employee{employeeId=1, salary=50000, username=johndoe, password=password123, name=John Doe, phoneNumber=1234567890, permission=STAFF, startDate=2020-01-01}";
//        assertEquals(expected, e.toString());
//    }
//}