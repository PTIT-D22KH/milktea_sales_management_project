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
import java.util.ArrayList;
import models.FoodItem;

/**
 * Data Access Object for FoodItem
 */
public abstract class FoodItemDao extends Dao<FoodItem> {
    public FoodItemDao() {
    }

    public FoodItemDao(Connection mockConnection) {
        conn = mockConnection;
    }

    @Override
    public ArrayList<FoodItem> getAll() throws SQLException {
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_item` ORDER BY `foodCategoryId` ASC, `name` ASC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            FoodItem foodItem = FoodItem.getFromResultSet(rs);
            foodItems.add(foodItem);
        }
        return foodItems;
    }

    public ArrayList<FoodItem> getByCategoryId(int id) throws SQLException {
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_item` WHERE `categoryId` = " + id;
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            FoodItem foodItem = FoodItem.getFromResultSet(rs);
            foodItems.add(foodItem);
        }
        return foodItems;
    }

    @Override
    public FoodItem getById(int foodItemId) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_item` WHERE `foodItemId` = " + foodItemId;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            FoodItem foodItem = FoodItem.getFromResultSet(rs);
            return foodItem;
        }
        return null;
    }

    @Override
    public void save(FoodItem t) throws SQLException {
        if (t == null) {
            throw new SQLException("Food item rong");
        }
        String query = "INSERT INTO `food_item` (`name`, `description`, `imagePath`, `unitName`, `unitPrice`, `foodCategoryId`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getName());
        stmt.setNString(2, t.getDescription());
        stmt.setNString(3, t.getImagePath());
        stmt.setNString(4, t.getUnitName());
        stmt.setInt(5, t.getUnitPrice());
        stmt.setInt(6, t.getCategoryId());
        stmt.executeUpdate();
    }

    @Override
    public void update(FoodItem t) throws SQLException {
        if (t == null) {
            throw new SQLException("Food item rong");
        }
        String query = "UPDATE `food_item` SET `name` = ?, `description` = ?, `imagePath` = ?, `unitName` = ?, `unitPrice` = ?, `foodCategoryId` = ? WHERE `foodItemId` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getName());
        stmt.setNString(2, t.getDescription());
        stmt.setNString(3, t.getImagePath());
        stmt.setNString(4, t.getUnitName());
        stmt.setInt(5, t.getUnitPrice());
        stmt.setInt(6, t.getCategoryId());
        stmt.setInt(7, t.getFoodItemId());
        stmt.executeUpdate();
    }

    @Override
    public void delete(FoodItem t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `food_item` WHERE `foodItemId` = ?");
        stmt.setInt(1, t.getFoodItemId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(int foodItemId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `food_item` WHERE `foodItemId` = ?");
        stmt.setInt(1, foodItemId);
        stmt.executeUpdate();
    }

    public ArrayList<FoodItem> searchByKey(String key, String word) throws SQLException {
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_item` WHERE `" + key + "` LIKE '%" + word + "%'";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            FoodItem foodItem = FoodItem.getFromResultSet(rs);
            foodItems.add(foodItem);
        }
        return foodItems;
    }

    public FoodItem getRandom() throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_item` WHERE `categoryId` != 4 ORDER BY RAND() LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            FoodItem foodItem = FoodItem.getFromResultSet(rs);
            return foodItem;
        }
        return null;
    }

    public FoodItem getRandom(int foodItemId) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_item` WHERE `categoryId` = " + foodItemId + " ORDER BY RAND() LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            FoodItem foodItem = FoodItem.getFromResultSet(rs);
            return foodItem;
        }
        return null;
    }
}