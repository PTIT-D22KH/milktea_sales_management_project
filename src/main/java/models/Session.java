package models;

import java.sql.*;

public class Session {
    private int sessionId, employeeId;
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
        if(employee != null) {
            this.employeeId = employee.getEmployeeId();
        }
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        
        this.employeeId = employeeId;
        
    }
    
    
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override 
    public String toString() {
        return "Session{" + "id=" + sessionId + ", idEmployee=" + employeeId + ", startTime=" + startTime + ", endTime=" + endTime + ", message=" + message + "}";
    }



}
