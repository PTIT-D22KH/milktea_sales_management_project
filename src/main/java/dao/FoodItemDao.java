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
import models.FoodCategory;
import models.FoodItem;

/**
 * Data Access Object for FoodItem
 */
public class FoodItemDao extends Dao<FoodItem> {
    private final FoodCategoryDao foodCategoryDao = new FoodCategoryDao();

    @Override
    public ArrayList<FoodItem> getAll() throws SQLException {
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        String query = "SELECT * FROM `food_item` ORDER BY `foodCategoryId` ASC, `name` ASC";
        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                FoodItem foodItem = FoodItem.getFromResultSet(rs);
                FoodCategory foodCategory = foodCategoryDao.getById(rs.getInt("foodCategoryId"));
                foodItem.setFoodCategory(foodCategory);
                foodItems.add(foodItem);
            }
        }
        return foodItems;
    }

    public ArrayList<FoodItem> getByCategoryId(int id) throws SQLException {
        if (id <= 0) {
            throw new SQLException("Category ID must > 0");
        }
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        String query = "SELECT * FROM `food_item` WHERE `foodCategoryId` = ?";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setInt(1, id);
            try (ResultSet rs = prStatement.executeQuery()) {
                while (rs.next()) {
                    FoodItem foodItem = FoodItem.getFromResultSet(rs);
                    FoodCategory foodCategory = foodCategoryDao.getById(rs.getInt("foodCategoryId"));
                    foodItem.setFoodCategory(foodCategory);
                    foodItems.add(foodItem);
                }
            }
        }
        return foodItems;
    }

    @Override
    public FoodItem getById(int foodItemId) throws SQLException {
        if (foodItemId <= 0) {
            throw new SQLException("Food item ID must > 0");
        }
        String query = "SELECT * FROM `food_item` WHERE `foodItemId` = ?";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setInt(1, foodItemId);
            try (ResultSet rs = prStatement.executeQuery()) {
                if (rs.next()) {
                    FoodItem foodItem = FoodItem.getFromResultSet(rs);
                    FoodCategory foodCategory = foodCategoryDao.getById(rs.getInt("foodCategoryId"));
                    foodItem.setFoodCategory(foodCategory);
                    return foodItem;
                }
            }
        }
        return null;
    }

    @Override
    public void save(FoodItem t) throws SQLException {
        validate(t);
        String query = "INSERT INTO `food_item` "
                + "(`name`, `description`, `imagePath`, `unitName`, `unitPrice`, `foodCategoryId`) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setFoodItemParameters(stmt, t);
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(FoodItem t) throws SQLException {
        validate(t);
        String query = "UPDATE `food_item` "
                + "SET `name` = ?, `description` = ?, `imagePath` = ?, `unitName` = ?, `unitPrice` = ?, `foodCategoryId` = ? "
                + "WHERE `foodItemId` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setFoodItemParameters(stmt, t);
            stmt.setInt(7, t.getFoodItemId());
            stmt.executeUpdate();
        }
    }

    private void setFoodItemParameters(PreparedStatement stmt, FoodItem t) throws SQLException {
        stmt.setString(1, t.getName());
        stmt.setString(2, t.getDescription());
        stmt.setString(3, t.getImagePath());
        stmt.setString(4, t.getUnitName());
        stmt.setInt(5, t.getUnitPrice());
        stmt.setInt(6, t.getFoodCategory().getFoodCategoryId());
    }

    @Override
    public void delete(FoodItem t) throws SQLException {
        validate(t);
        deleteById(t.getFoodItemId());
    }

    @Override
    public void deleteById(int foodItemId) throws SQLException {
        if (foodItemId <= 0) {
            throw new SQLException("Food item ID must > 0");
        }
        String query = "DELETE FROM `food_item` WHERE `foodItemId` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, foodItemId);
            stmt.executeUpdate();
        }
    }

    @Override
    public ArrayList<FoodItem> searchByKey(String key, String word) throws SQLException {
        if (key == null || word == null || key.trim().isEmpty() || word.trim().isEmpty()) {
            throw new SQLException("Từ khoá không được null/trống");
        }
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        String query = "SELECT * FROM `food_item` WHERE `" + key + "` LIKE ?";
        try (PreparedStatement prStatement = conn.prepareStatement(query)) {
            prStatement.setString(1, "%" + word + "%");
            try (ResultSet rs = prStatement.executeQuery()) {
                while (rs.next()) {
                    FoodItem foodItem = FoodItem.getFromResultSet(rs);
                    FoodCategory foodCategory = foodCategoryDao.getById(rs.getInt("foodCategoryId"));
                    foodItem.setFoodCategory(foodCategory);
                    foodItems.add(foodItem);
                }
            }
        }
        return foodItems;
    }

    @Override
    protected void validate(FoodItem t) throws SQLException {
        if (t == null) {
            throw new SQLException("Food Item object cannot be null");
        }
    }
}