/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author P51
 */
public class EmployeeDaoTest {
    EmployeeDao employeeDao = new EmployeeDao();
    public EmployeeDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testGetAll() throws SQLException{ 
        ArrayList<Employee> a = employeeDao.getAll();
        for (Employee x : a) {
            System.out.println(x);
        }
        assertEquals(6, a.size());
        Employee last = a.get(a.size() - 1);
        assertEquals(27, last.getEmployeeId());
        assertEquals("karma", last.getUsername());
    }
    
    @Test
    public void testGetById() throws SQLException {
        Employee a = employeeDao.getById(7);
//        System.out.println(a);
        assertEquals("son", a.getUsername());
        assertEquals("manager", a.getPermission().getId());
        
    }
}
