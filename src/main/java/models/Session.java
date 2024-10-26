package models;

import java.sql.*;

public class Session {
    private int sessionId;
    private Timestamp startTime, endTime;
    private Employee employee;
    private String message;
    
    public Session() {
    }
    
    
    public Timestamp getStartTime() {
        return this.startTime;
    }

    public Timestamp getEndTime() {
        return this.endTime;
    }
    public Employee getEmployee() {
        return this.employee;
    }
    public void setStartTime(Timestamp starttime) {
        this.startTime = starttime;
    }
    public void setEndTime(Timestamp endtime) {
        this.endTime = endtime;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    
    
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    public static Session getFromResultSet(ResultSet rs) throws SQLException {
        Session s = new Session();
        s.setSessionId(rs.getInt("sessionId"));
//        s.setEmployeeId(rs.getInt("EmployeeId"));
        s.setMessage(rs.getNString("message"));
        s.setStartTime(rs.getTimestamp("startTime"));
        s.setEndTime(rs.getTimestamp("endTime"));
        return s;
    }
    @Override 
    public String toString() {
        return "Session{" + "sessionId=" + sessionId + ", employee=" + employee + ", startTime=" + startTime + ", endTime=" + endTime + ", message=" + message + "}";
    }
    



}
