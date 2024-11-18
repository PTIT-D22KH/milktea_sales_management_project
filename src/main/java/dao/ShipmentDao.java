/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Customer;
import models.Employee;
import models.Order;
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
        String query = "SELECT * FROM `shipment`";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Shipment shipment = Shipment.getFromResultSet(rs);
                loadShipmentRelations(shipment, rs);
                shipments.add(shipment);
            }
        }
        return shipments;
    }

    @Override
    public void save(Shipment t) throws SQLException {
        validate(t);
        String query = "INSERT INTO `shipment` "
                + "(`OrderId`, `CustomerId`, `EmployeeId`, `shipCost`, `status`, `startDate`) "
                + "VALUES (?, ?, ?, ?, ?, current_timestamp())";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setShipmentParameters(stmt, t);
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Shipment t) throws SQLException {
        validate(t);
        String query = "UPDATE `shipment` "
                + "SET `CustomerId` = ?, `EmployeeId` = ?, `shipCost` = ?, `status` = ?, `startDate` = ?, `endDate` = ? "
                + "WHERE `shipment`.`OrderId` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setUpdateParameters(stmt, t);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Không tìm thấy shipment với OrderId " + t.getOrder().getOrderId());
            }
        }
    }

    @Override
    public void delete(Shipment t) throws SQLException {
        if (t == null || t.getOrder() == null) {
            throw new SQLException("Shipment hoặc Order rỗng");
        }
        deleteById(t.getOrder().getOrderId());
    }

    @Override
    public void deleteById(int id) throws SQLException {
        if (id <= 0) {
            throw new SQLException("OrderId phải > 0");
        }
        String query = "DELETE FROM `shipment` WHERE `shipment`.`OrderId` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Không tìm thấy shipment với OrderId " + id);
            }
        }
    }

    public void deleteByCustomerId(int customerId) throws SQLException {
        if (customerId <= 0) {
            throw new SQLException("CustomerId phải > 0");
        }
        String query = "DELETE FROM `shipment` WHERE `shipment`.`CustomerId` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        }
    }

    @Override
    public ArrayList<Shipment> searchByKey(String key, String word) throws SQLException {
        if (key == null || word == null || key.trim().isEmpty() || word.trim().isEmpty()) {
            throw new SQLException("Từ khoá không được null/trống");
        }
        ArrayList<Shipment> shipments = new ArrayList<>();
        String query = "SELECT * FROM `shipment` WHERE `" + key + "` LIKE ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, "%" + word + "%");
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Shipment shipment = Shipment.getFromResultSet(rs);
                    loadShipmentRelations(shipment, rs);
                    shipments.add(shipment);
                }
            }
        }
        return shipments;
    }

    @Override
    public Shipment getById(int id) throws SQLException {
        if (id <= 0) {
            throw new SQLException("OrderId phải > 0");
        }
        String query = "SELECT * FROM `shipment` WHERE `orderId` = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Shipment shipment = Shipment.getFromResultSet(result);
                    loadShipmentRelations(shipment, result);
                    return shipment;
                }
            }
        }
        return null;
    }

    private void loadShipmentRelations(Shipment shipment, ResultSet rs) throws SQLException {
        Customer customer = customerDao.getById(rs.getInt("customerId"));
        Order order = orderDao.getById(rs.getInt("orderId"));
        Employee employee = employeeDao.getById(rs.getInt("employeeId"));
        shipment.setCustomer(customer);
        shipment.setOrder(order);
        shipment.setEmployee(employee);
    }

    private void setShipmentParameters(PreparedStatement stmt, Shipment t) throws SQLException {
        stmt.setInt(1, t.getOrder().getOrderId());
        stmt.setInt(2, t.getCustomer().getCustomerId());
        stmt.setInt(3, t.getEmployee().getEmployeeId());
        stmt.setInt(4, t.getShipCost());
        stmt.setString(5, t.getStatus().getId());
    }

    private void setUpdateParameters(PreparedStatement stmt, Shipment t) throws SQLException {
        stmt.setInt(1, t.getCustomer().getCustomerId());
        stmt.setInt(2, t.getEmployee().getEmployeeId());
        stmt.setInt(3, t.getShipCost());
        stmt.setString(4, t.getStatus().getId());
        stmt.setTimestamp(5, t.getStartDate());
        stmt.setTimestamp(6, t.getEndDate());
        stmt.setInt(7, t.getOrder().getOrderId());
    }

    @Override
    protected void validate(Shipment t) throws SQLException {
        if (t == null) {
            throw new SQLException("Shipment rỗng");
        }
        if (t.getOrder() == null || t.getOrder().getOrderId() <= 0) {
            throw new SQLException("Order không hợp lệ");
        }
        if (t.getCustomer() == null || t.getCustomer().getCustomerId() <= 0) {
            throw new SQLException("Customer không hợp lệ");
        }
        if (t.getEmployee() == null || t.getEmployee().getEmployeeId() <= 0) {
            throw new SQLException("Employee không hợp lệ");
        }
        if (t.getShipCost() < 0) {
            throw new SQLException("Ship cost không được âm");
        }
        if (t.getStatus() == null) {
            throw new SQLException("Status không được null");
        }
    }
}