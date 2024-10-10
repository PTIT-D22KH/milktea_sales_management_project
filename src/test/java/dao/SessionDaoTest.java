///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package dao;
//
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import models.Session;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Test class for SessionDao
// */
//public class SessionDaoTest {
//    SessionDao sessionDao = new SessionDao();
//
//    public SessionDaoTest() {
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
//        ArrayList<Session> sessions = sessionDao.getAll();
//        assertNotNull(sessions);
//        assertFalse(sessions.isEmpty());
//        Session firstSession = sessions.get(0);
//        for (Session session : sessions) {
//            System.out.println(session);
//        }
//        assertNotNull(firstSession);
//    }
//
//    @Test
//    public void testGetById() throws SQLException {
//        Session session = sessionDao.getById(20);
//        assertNotNull(session);
//        assertEquals(20, session.getSessionId());
//    }
//
//    @Test
//    public void testSaveNull() throws SQLException {
//        Session session = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            sessionDao.save(session);
//        });
//        assertEquals("Session rong", exception.getMessage());
//    }
//
//    @Test
//    public void testSaveNotNull() throws SQLException {
//        Session session = new Session();
//        session.setEmployeeId(1);
//        session.setStartTime(new Timestamp(System.currentTimeMillis()));
//        session.setEndTime(new Timestamp(System.currentTimeMillis()));
//        session.setMessage("Test message");
//        sessionDao.save(session);
//
//        // Uncomment and adjust the following lines as needed
//        // Session savedSession = sessionDao.getById(session.getSessionId());
//        // assertEquals("Test message", savedSession.getMessage());
//    }
//
//    @Test
//    public void testUpdateNull() throws SQLException {
//        Session session = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            sessionDao.update(session);
//        });
//        assertEquals("Session rong", exception.getMessage());
//    }
//
//    @Test
//    public void testUpdateNotNull() throws SQLException {
//        Session session = sessionDao.getById(58);
//        session.setMessage("logout");
//        sessionDao.update(session);
//
//        Session updatedSession = sessionDao.getById(58);
//        assertEquals("logout", updatedSession.getMessage());
//    }
//
//    @Test
//    public void testGetLast() throws SQLException {
//        Session session = sessionDao.getLast(1);
//        assertNotNull(session);
//        assertEquals(1, session.getEmployeeId());
//    }
//
//    @Test
//    public void testGetAllWithTimestamps() throws SQLException {
//        Timestamp start = new Timestamp(System.currentTimeMillis() - 1000000000);
//        Timestamp end = new Timestamp(System.currentTimeMillis());
//        ArrayList<Session> sessions = sessionDao.getAll(start, end);
//        assertNotNull(sessions);
//        assertFalse(sessions.isEmpty());
//        Session firstSession = sessions.get(0);
//        assertNotNull(firstSession);
//    }
//}