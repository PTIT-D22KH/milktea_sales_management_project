/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Shipment;

/**
 *
 * @author DELL
 */
public class ShipmentDao extends Dao<Shipment> {

    private final CustomerDao customerDao = new CustomerDao();
    private final OrderDao orderDao = new OrderDao();
    private final EmployeeDao employeeDao = new EmployeeDao();

    @Override
    public ArrayList<Shipment> getAll() throws SQLException {
        ArrayList<Shipment> shipments = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `shipment`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Shipment shipment = Shipment.getFromResultSet(rs);
            shipment.setCustomer(customerDao.getById(shipment.getCustomerId()));
            shipment.setOrder(orderDao.getById(shipment.getOrderId()));
            shipment.setEmployee(employeeDao.getById(shipment.getEmployeeId()));
            shipments.add(shipment);
        }
        return shipments;
    }


    @Override
    public void save(Shipment t) throws SQLException {
        if (t == null) {
            throw new SQLException("Shipment rỗng");
        }
        String query = "INSERT INTO `shipment` (`OrderId`, `CustomerId`, `EmployeeId`, `shipCost`, `status`, `startDate`) VALUES ( ?, ?, ?, ?, ?, current_timestamp())";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getOrderId());
        stmt.setInt(2, t.getCustomerId());
        stmt.setInt(3, t.getEmployeeId());
        stmt.setInt(4, t.getShipCost());
        stmt.setNString(5, t.getStatus().getId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Shipment t) throws SQLException {
        if (t == null) {
            throw new SQLException("Shipment rỗng");
        }
        String query = "UPDATE `shipment` SET `CustomerId` = ?, `EmployeeId` = ?, `shipCost` = ?, `status` = ?,`startDate` = ?, `endDate` = ? WHERE `shipment`.`OrderId` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getCustomerId());
        stmt.setInt(2, t.getEmployeeId());
        stmt.setInt(3, t.getShipCost());
        stmt.setNString(4, t.getStatus().getId());
        stmt.setTimestamp(5, t.getStartDate());
        stmt.setTimestamp(6, t.getEndDate());
        stmt.setInt(7, t.getOrderId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void delete(Shipment t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `shipment` WHERE `shipment`.`OrderId` = ?");
        stmt.setInt(1, t.getOrderId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `shipment` WHERE `shipment`.`OrderId` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    /**
     * delete ship from a specific customer
     * @param customerId
     * @throws SQLException 
     */
    public void deleteByCustomerId(int customerId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `shipment` WHERE `shipment`.`CustomerId` = ?");
        stmt.setInt(1, customerId);
        stmt.executeUpdate();
    }

    public ArrayList<Shipment> searchByKey(String key, String word) throws SQLException {
        ArrayList<Shipment> shipments = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `shipment` WHERE " + key + " LIKE '%" + word + "%'";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Shipment shipment = Shipment.getFromResultSet(rs);
            shipment.setCustomer(customerDao.getById(shipment.getCustomerId()));
            shipment.setEmployee(employeeDao.getById(shipment.getEmployeeId()));
            shipments.add(shipment);
        }
        return shipments;
    }

    @Override
    public Shipment getById(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * "
                + "FROM `shipment` "
                + "WHERE `orderId` = " + id;
        ResultSet result = statement.executeQuery(query);
        if (result.next()) {
            Shipment shipment = Shipment.getFromResultSet(result);
            return shipment;
        }
        return null;
    }

}