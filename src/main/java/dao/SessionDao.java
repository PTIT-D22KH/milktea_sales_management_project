/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import models.Employee;
import models.Session;

/**
 * Data Access Object for Session
 */
public class SessionDao extends Dao<Session> {
    private final EmployeeDao employeeDao = new EmployeeDao();
    public SessionDao() {
        
    }
    public SessionDao(Connection conn) {
        this.conn = conn;
    }

    

    @Override
    public ArrayList<Session> getAll() throws SQLException {
        ArrayList<Session> sessions = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `session` ORDER BY `startTime` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Session session = Session.getFromResultSet(rs);
            Employee employee = employeeDao.getById(rs.getInt("employeeId"));
            session.setEmployee(employee);
            sessions.add(session);
        }
        return sessions;
    }

    @Override
    public Session getById(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `session` WHERE `sessionId` = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Session session = Session.getFromResultSet(rs);
            Employee employee = employeeDao.getById(rs.getInt("employeeId"));
            session.setEmployee(employee);
            return session;
        }
        return null;
    }

    public ArrayList<Session> getSession(int id) throws SQLException {
        ArrayList<Session> sessions = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `session` WHERE `employeeId` = " + id + " ORDER BY `startTime` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Session session = Session.getFromResultSet(rs);
            Employee employee = employeeDao.getById(rs.getInt("employeeId"));
            session.setEmployee(employee);
            sessions.add(session);
        }
        return sessions;
    }

    @Override
    public void save(Session t) throws SQLException {
        if (t == null) {
            throw new SQLException("Session rong");
        }
        String query = "INSERT INTO `session` (`employeeId`, `startTime`, `endTime`, `message`) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getEmployee().getEmployeeId());
        stmt.setTimestamp(2, t.getStartTime());
        stmt.setTimestamp(3, t.getEndTime());
        stmt.setNString(4, t.getMessage());
        stmt.executeUpdate();
    }

    @Override
    public void update(Session t) throws SQLException {
        if (t == null) {
            throw new SQLException("Session rong");
        }
        String query = "UPDATE `session` SET `startTime` = ?, `endTime` = ?, `message` = ? WHERE `sessionId` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setTimestamp(1, t.getStartTime());
        stmt.setTimestamp(2, t.getEndTime());
        stmt.setNString(3, t.getMessage());
        stmt.setInt(4, t.getSessionId());
        stmt.executeUpdate();
    }

    @Override
    public void delete(Session t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * get latest session
     * @param EmployeeId
     * @return
     * @throws SQLException 
     */
    public Session getLast(int EmployeeId) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `session` WHERE `employeeId` = " + EmployeeId + " ORDER BY `sessionId` DESC LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Session session = Session.getFromResultSet(rs);
            Employee employee = employeeDao.getById(rs.getInt("employeeId"));
            session.setEmployee(employee);
            return session;
        }
        return null;
    }

    public ArrayList<Session> getAll(Timestamp start, Timestamp end) throws SQLException {
        ArrayList<Session> sessions = new ArrayList<>();
        String query = "SELECT * FROM `session` WHERE `message` = ? AND DATE(startTime) >= DATE(?) AND DATE(startTime) <= DATE(?) ORDER BY `session`.`startTime` DESC";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, "logout");
        statement.setTimestamp(2, start);
        statement.setTimestamp(3, end);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Session session = Session.getFromResultSet(rs);
            Employee employee = employeeDao.getById(rs.getInt("employeeId"));
            session.setEmployee(employee);
            sessions.add(session);
        }
        return sessions;
    }
}