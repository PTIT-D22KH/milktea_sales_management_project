package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.OrderItem;

/**
 *
 * @author MSI
 */
public class OrderItemDao extends Dao<OrderItem> {

    FoodItemDao foodItemDao = new FoodItemDao() {};

    @Override
    public ArrayList<OrderItem> getAll() throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order_item` ORDER BY `order_item`.`orderId` DESC, `order_item`.`quantity` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            OrderItem orderItem = OrderItem.getFromResultSet(rs);
            orderItem.setFoodItem(foodItemDao.getById(orderItem.getFoodItemId()));
            orderItem.setToppingItem(foodItemDao.getById(orderItem.getToppingId()));
            orderItems.add(orderItem);
        }
        return orderItems;
    }
    @Override
    public void save(OrderItem t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order Item rỗng");
        }
        String query = "INSERT INTO `order_item` (`orderId`, `foodItemId`, `toppingId`, `quantity`, `foodPrice`, `toppingPrice`, `note`) VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE  `quantity` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getOrderId());
        stmt.setInt(2, t.getFoodItemId());
        stmt.setInt(3, t.getToppingId());
        stmt.setInt(4, t.getQuantity());
        stmt.setInt(5, t.getFoodPrice());
        stmt.setInt(6, t.getToppingPrice());
        stmt.setNString(7, t.getNote());
        stmt.setInt(8, t.getQuantity());
        stmt.executeUpdate();
    }

    @Override
    public void update(OrderItem t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order Item rỗng");
        }
        String query = "UPDATE `order_item` SET  `quantity` = ?, `foodPrice` = ?, `toppingPrice` = ?, `note` = ? WHERE `orderId` = ? AND `foodItemId` = ? AND `toppingId` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getQuantity());
        stmt.setInt(2, t.getFoodPrice());
        stmt.setInt(3, t.getToppingPrice());
        stmt.setNString(4, t.getNote());
        stmt.setInt(5, t.getOrderId());
        stmt.setInt(6, t.getFoodItemId());
        stmt.setInt(7, t.getToppingId());
        stmt.executeUpdate();
    }

    @Override
    public void delete(OrderItem t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order Item rỗng");
        }
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order_item` WHERE `orderId` = ? AND `foodItemId` = ? AND `toppingId` = ?");
        stmt.setInt(1, t.getOrderId());
        stmt.setInt(2, t.getFoodItemId());
        stmt.setInt(3, t.getToppingId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addItem(OrderItem t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order Item rỗng");
        }
        String query = "CALL `addOrderItem`(?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getOrderId());
        stmt.setInt(2, t.getFoodItemId());
        stmt.setInt(3, t.getToppingId());
        stmt.setInt(4, t.getQuantity());
        stmt.setNString(5, t.getNote());
    }

    public ArrayList<OrderItem> getByOrderId(int orderId) throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order_item`WHERE `orderId` = " + orderId + "  ORDER BY `order_item`.`quantity` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            OrderItem orderItem = OrderItem.getFromResultSet(rs);
            orderItem.setFoodItem(foodItemDao.getById(orderItem.getFoodItemId()));
            orderItem.setToppingItem(foodItemDao.getById(orderItem.getToppingId()));
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    public ArrayList<OrderItem> searchByKey(String key, String word) throws SQLException {
        ArrayList<OrderItem> orderitems = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order_item` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            OrderItem orderitem = OrderItem.getFromResultSet(rs);
            orderitems.add(orderitem);
        }
        return orderitems;
    }

    @Override
    public OrderItem getById(int id) throws SQLException {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}