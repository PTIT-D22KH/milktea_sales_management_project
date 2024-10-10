//package models;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.Timestamp;
//
///**
// * Test class for Session
// */
//public class SessionTest {
//    
//    public SessionTest() {
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
//    public void testSessionConstructor() {
//        Session session = new Session();
//        assertNotNull(session);
//    }
//
//    @Test
//    public void testSetAndGetSessionId() {
//        Session session = new Session();
//        session.setSessionId(1);
//        assertEquals(1, session.getSessionId());
//    }
//
//    @Test
//    public void testSetAndGetEmployeeId() {
//        Session session = new Session();
//        session.setEmployeeId(2);
//        assertEquals(2, session.getEmployeeId());
//    }
//
//    @Test
//    public void testSetAndGetStartTime() {
//        Session session = new Session();
//        Timestamp startTime = Timestamp.valueOf("2023-01-01 10:00:00");
//        session.setStartTime(startTime);
//        assertEquals(startTime, session.getStartTime());
//    }
//
//    @Test
//    public void testSetAndGetEndTime() {
//        Session session = new Session();
//        Timestamp endTime = Timestamp.valueOf("2023-01-01 12:00:00");
//        session.setEndTime(endTime);
//        assertEquals(endTime, session.getEndTime());
//    }
//
//    @Test
//    public void testSetAndGetEmployee() {
//        Session session = new Session();
//        Employee employee = new Employee();
//        employee.setEmployeeId(2);
//        session.setEmployee(employee);
//        assertEquals(employee, session.getEmployee());
//        assertEquals(2, session.getEmployeeId());
//    }
//
//    @Test
//    public void testSetAndGetMessage() {
//        Session session = new Session();
//        session.setMessage("Session started");
//        assertEquals("Session started", session.getMessage());
//    }
//
//    @Test
//    public void testToString() {
//        Session session = new Session();
//        session.setSessionId(1);
//        session.setEmployeeId(2);
//        Timestamp startTime = Timestamp.valueOf("2023-01-01 10:00:00");
//        session.setStartTime(startTime);
//        Timestamp endTime = Timestamp.valueOf("2023-01-01 12:00:00");
//        session.setEndTime(endTime);
//        session.setMessage("Session started");
//
//        String expected = "Session{" +
//                "id=" + session.getSessionId() +
//                ", employeeId=" + session.getEmployeeId() +
//                ", startTime=" + session.getStartTime() +
//                ", endTime=" + session.getEndTime() +
//                ", message=" + session.getMessage() + 
//                '}';
//
//        assertEquals(expected, session.toString());
//    }
//}
///**
// * expected: <Session{id=1, employeeId=2, startTime=2023-01-01 10:00:00.0, endTime=2023-01-01 12:00:00.0, message='Session started'}> 
// * but was:  <Session{id=1, employeeId=2, startTime=2023-01-01 10:00:00.0, endTime=2023-01-01 12:00:00.0, message=Session started}>
//org.opentest4j.AssertionFailedError
//	at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
//	at org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
//	at org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
//	at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
//	at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
//	at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
//	at models.SessionTest.testToString(SessionTest.java:108)
//	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
//	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
//	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
//
//
// */