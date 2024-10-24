/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import models.WorkDay;
import utils.DatabaseConnector;
import utils.OrderStatus;

/**
 *
 * @author Admin
 */
public class WorkDayDao {

    private final Connection conn = DatabaseConnector.getInstance().getConn();

    public ArrayList<Integer> getDay(int id, int month, int year) throws SQLException {
        String query = "SELECT  DISTINCT DAY(startTime) as day FROM `session` WHERE `employeeId` = ? AND YEAR(startTime) = ? and MONTH(startTime) = ? ORDER BY day ASC";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        statement.setInt(2, year);
        statement.setInt(3, month);
        ResultSet rs = statement.executeQuery();
        ArrayList<Integer> list = new ArrayList();
        while (rs.next()) {
            list.add(rs.getInt("day"));
        }
        return list;
    }

    public WorkDay getSales(int employeeId, Timestamp date) throws SQLException {
        String query = "SELECT DATE(orderDate) as day, COUNT(orderId) AS amount, SUM(totalAmount) as total, (COUNT(orderId) * 2000) as bonus "
                     + "FROM `order` "
                     + "WHERE employeeId = ? AND DATE(orderDate) = DATE(?) AND status = ? "
                     + "GROUP BY DATE(orderDate)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, employeeId);
            statement.setTimestamp(2, date);
            statement.setNString(3, OrderStatus.PAID.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                WorkDay workDay = WorkDay.getFromResultSet(rs);
                return workDay;
            }
        }
        return null;
    }

    public int getBonus(int id, int month, int year) throws SQLException {
        String query = "SELECT (COUNT(orderId) * 2000) as bonus "
                + "FROM `order` "
                + "WHERE employeeId = ? "
                + "AND YEAR(orderDate) = ? "
                + "AND MONTH(orderDate) = ? "
                + "AND status = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setInt(2, year);
            statement.setInt(3, month);
            statement.setNString(4, OrderStatus.PAID.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("bonus");
            }
        }
        return 0;
    }

    public int getTotalWorkingMinutes(Timestamp date, int employeeId) throws SQLException {
        String query = "SELECT FLOOR(SUM(TIME_TO_SEC(TIMEDIFF(endTime, startTime))) / 60) AS totalWorkingMinutes FROM `session` WHERE DATE(startTime) = DATE(?) AND employeeId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setTimestamp(1, date);
        statement.setInt(2, employeeId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getInt("totalWorkingMinutes");
        }
        return 0;
    }

    public int getTotalWorkingMinutes(int month, int year, int employeeId) throws SQLException {
        String query = "SELECT FLOOR(SUM(TIME_TO_SEC(TIMEDIFF(endTime, startTime))) / 60) AS totalWorkingMinutes FROM `session` WHERE YEAR(startTime) =? AND MONTH(startTime)=? AND employeeId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, year);
        statement.setInt(2, month);
        statement.setInt(3, employeeId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getInt("totalWorkingMinutes");
        }
        return 0;
    }

    public int getTotalIncome(int year, int month, int employeeId) throws SQLException {
        String query = "SELECT SUM(paidAmount) AS totalIncome FROM `order` WHERE status = ? AND YEAR(orderDate)=? AND MONTH(orderDate) =? AND employeeId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, OrderStatus.PAID.getId());
        statement.setInt(2, year);
        statement.setInt(3, month);
        statement.setInt(4, employeeId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getInt("totalIncome");
        }
        return 0;
    }

    public int getTotalOrder(int year, int month, int employeeId) throws SQLException {
        String query = "SELECT COUNT(*) AS totalOrder FROM `order` WHERE status = ? AND YEAR(orderDate) = ? AND MONTH(orderDate) = ? AND employeeId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, OrderStatus.PAID.getId());
        statement.setInt(2, year);
        statement.setInt(3, month);
        statement.setInt(4, employeeId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getInt("totalOrder");
        }
        return 0;
    }
}
