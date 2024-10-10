///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package dao;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import models.Employee;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import utils.EmployeePermission;
//
///**
// *
// * @author P51
// */
//public class EmployeeDaoTest {
//    EmployeeDao employeeDao = new EmployeeDao();
//    public EmployeeDaoTest() {
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
//        
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
//    @Test
//    public void testGetAll() throws SQLException{ 
//        ArrayList<Employee> a = employeeDao.getAll();
////        for (Employee x : a) {
////            System.out.println(x);
////        }
//        assertEquals(6, a.size());
//        Employee last = a.get(a.size() - 1);
////        assertEquals(27, last.getEmployeeId());
//        assertEquals("karma", last.getUsername());
//    }
//    
//    @Test
//    public void testGetById() throws SQLException {
//        Employee a = employeeDao.getById(7);
////        System.out.println(a);
//        assertEquals("son", a.getUsername());
//        assertEquals("manager", a.getPermission().getId());
//        assertEquals("2020-12-16", a.getStartDate().toString());
//    }
//    
//    
//    /**
//     * Should throw SQL Exception
//     * @throws SQLException 
//     */
//    @Test
//    public void testSaveNull() throws SQLException {
//        Employee n = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            employeeDao.save(n);
//        });
//        assertEquals("Cannot insert into table null object(employee)", exception.getMessage());
//    }
//    
//    @Test
//    public void testSaveNotNull() throws SQLException {
//        Employee e = employeeDao.getById(27);
//        e.setUsername("test_new_username");
//        e.setPermission(EmployeePermission.INACTIVE);
//        employeeDao.save(e);
//        Employee new_e = employeeDao.getById(28);
//        assertEquals("test_new_username", new_e.getUsername());
//        assertEquals("inactive", new_e.getPermission().getId());
//    }
//    
//    @Test
//    public void testUpdateNull() throws SQLException {
//        Employee n = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            employeeDao.update(n);
//        });
//        assertEquals("Cannot update employee table when employee instance is null", exception.getMessage());
//    }
//    
//    
//    @Test
//    public void testUpdateNotNull() throws SQLException {
//        Employee e = employeeDao.getById(28);
//        e.setPermission(EmployeePermission.STAFF);
//        employeeDao.update(e);
//        Employee new_e = employeeDao.getById(28);
//        assertEquals("staff", new_e.getPermission().getId());
//    }
//    
//    
//    @Test
//    public void testDelete() throws SQLException {
//        // Ensure the employee with ID 28 exists
//        Employee x = employeeDao.getById(28);
//        if (x == null) {
//            // Create and save a new employee with ID 28 if it does not exist
//            x = new Employee();
//            x.setEmployeeId(28); // Set the ID to 28
//            x.setUsername("test_delete_username");
//            x.setPassword("test_password");
//            x.setName("Test Delete");
//            x.setPhoneNumber("1234567890");
//            x.setPermission(EmployeePermission.STAFF);
//            x.setSalary(50000);
//            employeeDao.save(x);
//        }
//
//        // Delete the employee
//        employeeDao.delete(x);
//
//        // Verify that the employee has been deleted
//        Employee deletedEmployee = employeeDao.getById(28);
//        assertNull(deletedEmployee);
//    }
//    
//    @Test
//    public void testDeleteById() throws SQLException {
//        // First, save a new employee to ensure there is an employee with a known ID to delete
//        Employee e = new Employee();
//        e.setUsername("test_delete_username");
//        e.setPassword("test_password");
//        e.setName("Test Delete");
//        e.setPhoneNumber("1234567890");
//        e.setPermission(EmployeePermission.STAFF);
//        e.setSalary(50000);
//        employeeDao.save(e);
//
//        // Retrieve the newly saved employee to get its ID
//        Employee savedEmployee = employeeDao.findByUsername("test_delete_username");
//        assertNotNull(savedEmployee);
//        int employeeIdToDelete = savedEmployee.getEmployeeId();
//
//        // Delete the employee by ID
//        employeeDao.deleteById(employeeIdToDelete);
//
//        // Verify that the employee has been deleted
//        Employee deletedEmployee = employeeDao.getById(employeeIdToDelete);
//        assertNull(deletedEmployee);
//    }
//}
