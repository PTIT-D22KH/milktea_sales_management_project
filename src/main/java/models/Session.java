package models;

import java.sql.*;

public class Session {
    private int sessionId, idEmployee;
    private Timestamp startTime, endTime;
    private Employee employee;
    private String message;
    
    public Session() {
    }
    public int getId() {
        return this.sessionId;
    }
    public void setID(int id) {
        this.sessionId = id;
    }
    public int getIDEmployee() {
        return this.idEmployee;
    }
    public void setIDEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
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
            this.idEmployee = employee.getEmployeeId();
        }
    }
    public int getIdEmployee() {
        return this.idEmployee;
    }
    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override 
    public String toString() {
        return "Session{" + "id=" + sessionId + ", idEmployee=" + idEmployee + ", startTime=" + startTime + ", endTime=" + endTime + ", message=" + message + "}";
    }



}
