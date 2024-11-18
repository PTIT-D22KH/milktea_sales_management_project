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

    @Override
    public ArrayList<Session> getAll() throws SQLException {
        ArrayList<Session> sessions = new ArrayList<>();
        String query = "SELECT * FROM `session` ORDER BY `startTime` DESC";
        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                Session session = Session.getFromResultSet(rs);
                Employee employee = employeeDao.getById(rs.getInt("employeeId"));
                session.setEmployee(employee);
                sessions.add(session);
            }
        }
        return sessions;
    }

    @Override
    public Session getById(int id) throws SQLException {
        if (id <= 0) {
            throw new SQLException("Session ID must > 0");
        }
        String query = "SELECT * FROM `session` WHERE `sessionId` = ?";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setInt(1, id);
            try (ResultSet rs = prStatement.executeQuery()) {
                if (rs.next()) {
                    Session session = Session.getFromResultSet(rs);
                    Employee employee = employeeDao.getById(rs.getInt("employeeId"));
                    session.setEmployee(employee);
                    return session;
                }
            }
        }
        return null;
    }

    public ArrayList<Session> getSession(int id) throws SQLException {
        if (id <= 0) {
            throw new SQLException("Employee ID must > 0");
        }
        ArrayList<Session> sessions = new ArrayList<>();
        String query = "SELECT * FROM `session` WHERE `employeeId` = ? ORDER BY `startTime` DESC";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setInt(1, id);
            try (ResultSet rs = prStatement.executeQuery()) {
                while (rs.next()) {
                    Session session = Session.getFromResultSet(rs);
                    Employee employee = employeeDao.getById(rs.getInt("employeeId"));
                    session.setEmployee(employee);
                    sessions.add(session);
                }
            }
        }
        return sessions;
    }

    @Override
    public void save(Session t) throws SQLException {
        validate(t);
        String query = "INSERT INTO `session` "
                + "(`employeeId`, `startTime`, `endTime`, `message`) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setSessionParameters(stmt, t);
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Session t) throws SQLException {
        validate(t);
        String query = "UPDATE `session` "
                + "SET `startTime` = ?, `endTime` = ?, `message` = ? "
                + "WHERE `sessionId` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setTimestamp(1, t.getStartTime());
            stmt.setTimestamp(2, t.getEndTime());
            stmt.setString(3, t.getMessage());
            stmt.setInt(4, t.getSessionId());
            stmt.executeUpdate();
        }
    }

    private void setSessionParameters(PreparedStatement stmt, Session t) throws SQLException {
        stmt.setInt(1, t.getEmployee().getEmployeeId());
        stmt.setTimestamp(2, t.getStartTime());
        stmt.setTimestamp(3, t.getEndTime());
        stmt.setString(4, t.getMessage());
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
    public Session getLast(int employeeId) throws SQLException {
        if (employeeId <= 0) {
            throw new SQLException("Employee ID must > 0");
        }
        String query = "SELECT * FROM `session` WHERE `employeeId` = ? ORDER BY `sessionId` DESC LIMIT 1";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setInt(1, employeeId);
            try (ResultSet rs = prStatement.executeQuery()) {
                if (rs.next()) {
                    Session session = Session.getFromResultSet(rs);
                    Employee employee = employeeDao.getById(rs.getInt("employeeId"));
                    session.setEmployee(employee);
                    return session;
                }
            }
        }
        return null;
    }

    public ArrayList<Session> getAll(Timestamp start, Timestamp end) throws SQLException {
        if (start == null || end == null) {
            throw new SQLException("Start and end timestamps cannot be null");
        }
        ArrayList<Session> sessions = new ArrayList<>();
        String query = "SELECT * FROM `session` WHERE `message` = ? AND DATE(startTime) >= DATE(?) AND DATE(startTime) <= DATE(?) ORDER BY `session`.`startTime` DESC";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, "logout");
            statement.setTimestamp(2, start);
            statement.setTimestamp(3, end);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Session session = Session.getFromResultSet(rs);
                    Employee employee = employeeDao.getById(rs.getInt("employeeId"));
                    session.setEmployee(employee);
                    sessions.add(session);
                }
            }
        }
        return sessions;
    }

    @Override
    public ArrayList<Session> searchByKey(String key, String value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void validate(Session t) throws SQLException {
        if (t == null) {
            throw new SQLException("Session object cannot be null");
        }
    }
}