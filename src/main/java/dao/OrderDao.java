
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Customer;
import models.Employee;
import models.Order;
import models.Table;

/**
 *
 * @author MSI
 */
public class OrderDao extends Dao<Order> {

    private final EmployeeDao employeeDao = new EmployeeDao();
    private final TableDao tableDao = new TableDao();
    private final CustomerDao customerDao = new CustomerDao();

    
    @Override
    public ArrayList<Order> getAll() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order` ORDER BY `order`.`orderDate` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            if (order == null) {
                continue;
            }
            Employee employee = employeeDao.getById(rs.getInt("employeeId"));
            Table table = tableDao.getById(rs.getInt("tableId"));
            Customer customer = customerDao.getById(rs.getInt("customerId"));
            order.setEmployee(employee);
            order.setTable(table);
            order.setCustomer(customer);
            orders.add(order);
        }
        return orders;
    }

    /**
     * get all orders from a specific employee
     * @param EmployeeId
     * @return
     * @throws SQLException 
     */
    public ArrayList<Order> getAll(int EmployeeId) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order`  WHERE `employeeId`= '" + EmployeeId + "' ORDER BY `order`.`orderDate` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            Employee employee = employeeDao.getById(rs.getInt("employeeId"));
            Table table = tableDao.getById(rs.getInt("tableId"));
            Customer customer = customerDao.getById(rs.getInt("customerId"));
            order.setEmployee(employee);
            order.setTable(table);
            order.setCustomer(customer);
            orders.add(order);
        }
        return orders;
    }


    @Override
    public void save(Order t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order rỗng");
        }
        String query = "INSERT INTO `order` (`employeeId`, `tableId`, `customerId`, `type`, `status`, `orderDate`, `payDate`, `paidAmount`, `totalAmount`, `discount`) VALUES (?, ?, ?, ?, ?, current_timestamp(), NULL, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getEmployee().getEmployeeId());
        stmt.setInt(2, t.getTable().getTableId());
        stmt.setInt(3, t.getCustomer().getCustomerId());
        stmt.setNString(4, t.getType().getId());
        stmt.setNString(5, t.getStatus().getId());
        stmt.setInt(6, t.getPaidAmount());
        stmt.setInt(7, t.getTotalAmount());
        stmt.setInt(8, t.getDiscount());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Order t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order rỗng");
        }
        String query = "UPDATE `order` SET `employeeId` = ?, `tableId` = ?, `customerId` = ?, `type` = ?, `status` = ?, `orderDate` = ?, `payDate` = ?, `paidAmount` = ?, `totalAmount` = ?, `discount` = ? WHERE `order`.`orderId` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getEmployee().getEmployeeId());
        stmt.setInt(2, t.getTable().getTableId());
        stmt.setInt(3, t.getCustomer().getCustomerId());
        stmt.setNString(4, t.getType().getId());
        stmt.setNString(5, t.getStatus().getId());
        stmt.setTimestamp(6, t.getOrderDate());
        stmt.setTimestamp(7, t.getPayDate());
        stmt.setInt(8, t.getPaidAmount());
        stmt.setInt(9, t.getTotalAmount());
        stmt.setInt(10, t.getDiscount());
        stmt.setInt(11, t.getOrderId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void delete(Order t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order` WHERE `order`.`orderId` = ?");
        stmt.setInt(1, t.getOrderId());
        stmt.executeUpdate();
    }

    /**
     * delete order with specific id
     * @param OrderId
     * @throws SQLException 
     */
    @Override
    public void deleteById(int OrderId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order` WHERE `order`.`orderId` = ?");
        stmt.setInt(1, OrderId);
        stmt.executeUpdate();
    }

    /**
     * delete order items in a specific order
     * @param OrderId
     * @throws SQLException 
     */
    public void deleteItems(int OrderId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order_item` WHERE `orderId` = ?");
        stmt.setInt(1, OrderId);
        stmt.executeUpdate();
    }

    public ArrayList<Order> searchByKey(String key, String word) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            Employee employee = employeeDao.getById(rs.getInt("employeeId"));
            Table table = tableDao.getById(rs.getInt("tableId"));
            Customer customer = customerDao.getById(rs.getInt("customerId"));
            order.setEmployee(employee);
            order.setTable(table);
            order.setCustomer(customer);
            orders.add(order);
        }
        return orders;
    }

    /**
     * create order with specific timestamp
     * @param t
     * @throws SQLException 
     */
    public void create(Order t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order rỗng");
        }
        String query = "INSERT INTO `order` (`employeeId`, `tableId`, `customerId`, `type`, `status`, `orderDate`, `payDate`, `paidAmount`, `totalAmount`, `discount`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getEmployee().getEmployeeId());
        stmt.setInt(2, t.getTable().getTableId());
        stmt.setInt(3, t.getCustomer().getCustomerId());
        stmt.setNString(4, t.getType().getId());
        stmt.setNString(5, t.getStatus().getId());
        stmt.setTimestamp(6, t.getOrderDate());
        stmt.setTimestamp(7, t.getPayDate());
        stmt.setInt(8, t.getPaidAmount());
        stmt.setInt(9, t.getTotalAmount());
        stmt.setInt(10, t.getDiscount());
        int row = stmt.executeUpdate();
    }

    public ArrayList<Order> searchByKey(int EmployeeId, String key, String word) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM `order` WHERE " + key + " LIKE ? AND employeeId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, String.format("%s%s%s", "%", word, "%"));
        statement.setInt(2, EmployeeId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            Employee employee = employeeDao.getById(rs.getInt("employeeId"));
            Table table = tableDao.getById(rs.getInt("tableId"));
            Customer customer = customerDao.getById(rs.getInt("customerId"));
            order.setEmployee(employee);
            order.setTable(table);
            order.setCustomer(customer);
            orders.add(order);
        }
        return orders;
    }


    @Override
    public Order getById(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order` WHERE `orderId` = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            Employee employee = employeeDao.getById(rs.getInt("employeeId"));
            Table table = tableDao.getById(rs.getInt("tableId"));
            Customer customer = customerDao.getById(rs.getInt("customerId"));
            order.setEmployee(employee);
            order.setTable(table);
            order.setCustomer(customer);
            return order;
        }
        return null;
    }

}