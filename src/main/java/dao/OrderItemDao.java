package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.FoodItem;
import models.Order;
import models.OrderItem;

/**
 *
 * @author MSI
 */
public class OrderItemDao extends Dao<OrderItem> {

    private final FoodItemDao foodItemDao = new FoodItemDao();
    private final OrderDao orderDao = new OrderDao();

    @Override
    public ArrayList<OrderItem> getAll() throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT * FROM `order_item` ORDER BY `order_item`.`orderId` DESC, `order_item`.`quantity` DESC";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                OrderItem orderItem = OrderItem.getFromResultSet(rs);
                FoodItem foodItem = foodItemDao.getById(rs.getInt("foodItemId"));
                FoodItem toppingItem = foodItemDao.getById(rs.getInt("toppingId"));
                Order order = orderDao.getById(rs.getInt("orderId"));
                orderItem.setFoodItem(foodItem);
                orderItem.setToppingItem(toppingItem);
                orderItem.setOrder(order);
                orderItems.add(orderItem);
            }
        }
        return orderItems;
    }
    @Override
    public void save(OrderItem t) throws SQLException {
        validate(t);
        String query = "INSERT INTO `order_item` (`orderId`, `foodItemId`, `toppingId`, `quantity`, `foodPrice`, `toppingPrice`, `note`)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE  `quantity` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, t.getOrder().getOrderId());
            stmt.setInt(2, t.getFoodItem().getFoodItemId());
            stmt.setInt(3, t.getToppingItem().getFoodItemId());
            stmt.setInt(4, t.getQuantity());
            stmt.setInt(5, t.getFoodItem().getUnitPrice());
            stmt.setInt(6, t.getToppingItem().getUnitPrice());
            stmt.setString(7, t.getNote());
            stmt.setInt(8, t.getQuantity());
            stmt.executeUpdate();
    }

    @Override
    public void update(OrderItem t) throws SQLException {
        validate(t);
        String query = "UPDATE `order_item` SET `quantity` = ?, `foodPrice` = ?, `toppingPrice` = ?, `note` = ? " +
                      "WHERE `orderId` = ? AND `foodItemId` = ? AND `toppingId` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, t.getQuantity());
            stmt.setInt(2, t.getFoodItem().getUnitPrice());
            stmt.setInt(3, t.getToppingItem().getUnitPrice());
            stmt.setString(4, t.getNote());
            stmt.setInt(5, t.getOrder().getOrderId());
            stmt.setInt(6, t.getFoodItem().getFoodItemId());
            stmt.setInt(7, t.getToppingItem().getFoodItemId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(OrderItem t) throws SQLException {
        validate(t);
        String query = "DELETE FROM `order_item` WHERE `orderId` = ? AND `foodItemId` = ? AND `toppingId` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, t.getOrder().getOrderId());
            stmt.setInt(2, t.getFoodItem().getFoodItemId());
            stmt.setInt(3, t.getToppingItem().getFoodItemId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    public ArrayList<OrderItem> getByOrderId(int orderId) throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT * FROM `order_item` WHERE `orderId` = ? ORDER BY `order_item`.`quantity` DESC";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, orderId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    OrderItem orderItem = OrderItem.getFromResultSet(rs);
                    FoodItem foodItem = foodItemDao.getById(rs.getInt("foodItemId"));
                    FoodItem toppingItem = foodItemDao.getById(rs.getInt("toppingId"));
                    Order order = orderDao.getById(rs.getInt("orderId"));
                    orderItem.setFoodItem(foodItem);
                    orderItem.setToppingItem(toppingItem);
                    orderItem.setOrder(order);
                    orderItems.add(orderItem);
                }
            }
        }
        return orderItems;
    }

    @Override
    public ArrayList<OrderItem> searchByKey(String key, String word) throws SQLException {
        if (key == null || word == null || key.trim().isEmpty() || word.trim().isEmpty()) {
            throw new SQLException("Từ khoá không được null/trống");
        }
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT * FROM `order_item` WHERE " + key + " LIKE ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, "%" + word + "%");
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    OrderItem orderItem = OrderItem.getFromResultSet(rs);
                    FoodItem foodItem = foodItemDao.getById(rs.getInt("foodItemId"));
                    FoodItem toppingItem = foodItemDao.getById(rs.getInt("toppingId"));
                    Order order = orderDao.getById(rs.getInt("orderId"));
                    orderItem.setFoodItem(foodItem);
                    orderItem.setToppingItem(toppingItem);
                    orderItem.setOrder(order);
                    orderItems.add(orderItem);
                }
            }
        }
        return orderItems;
    }

    public void addItem(OrderItem t) throws SQLException {
        validate(t);
        String query = "CALL `addOrderItem`(?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, t.getOrder().getOrderId());
            stmt.setInt(2, t.getFoodItem().getFoodItemId());
            stmt.setInt(3, t.getToppingItem().getFoodItemId());
            stmt.setInt(4, t.getQuantity());
            stmt.setString(5, t.getNote());
            stmt.executeUpdate();
        }
    }

    @Override
    public OrderItem getById(int id) throws SQLException {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void validate(OrderItem t) throws SQLException {
        if(t==null) {
            throw new SQLException("Order item object cannot be null");
        }
    }

}