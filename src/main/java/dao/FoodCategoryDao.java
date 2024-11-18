package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.FoodCategory;

/**
 * Data Access Object for Food Category entities.
 * Implements CRUD operations and search functionality for food categories.
 */
public class FoodCategoryDao extends Dao<FoodCategory> {
    
    @Override
    public ArrayList<FoodCategory> getAll() throws SQLException {
        ArrayList<FoodCategory> foodCategories = new ArrayList<>();
        String query = "SELECT * FROM `food_category`";
        try (PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                foodCategories.add(FoodCategory.getFromResultSet(rs));
            }
        }
        return foodCategories;
    }
    
    @Override
    public FoodCategory getById(int id) throws SQLException {
        String query = "SELECT * FROM `food_category` WHERE `foodCategoryId` = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return FoodCategory.getFromResultSet(rs);
                }
            }
        }
        return null;
    }
    
    @Override
    public void save(FoodCategory category) throws SQLException {
        validate(category);
        String query = "INSERT INTO `food_category` (`name`, `slug`) VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getSlug());
            statement.executeUpdate();
        }
    }
    
    @Override
    public void update(FoodCategory category) throws SQLException {
        validate(category);
        String query = "UPDATE `food_category` SET `name` = ?, `slug` = ? WHERE `foodCategoryId` = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getSlug());
            statement.setInt(3, category.getFoodCategoryId());
            statement.executeUpdate();
        }
    }
    
    @Override
    public void delete(FoodCategory category) throws SQLException {
        validate(category);
        deleteById(category.getFoodCategoryId());
    }
    
    @Override
    public void deleteById(int id) throws SQLException {
        String query = "DELETE FROM `food_category` WHERE `foodCategoryId` = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    
    @Override
    public ArrayList<FoodCategory> searchByKey(String key, String value) throws SQLException {
        if (key == null || value == null || key.trim().isEmpty() || value.trim().isEmpty()) {
            throw new SQLException("Từ khoá không được null/trống");
        }
        ArrayList<FoodCategory> foodCategories = new ArrayList<>();
        String query = "SELECT * FROM `food_category` WHERE " + key + " LIKE ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, "%" + value + "%");
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    foodCategories.add(FoodCategory.getFromResultSet(rs));
                }
            }
        }
        return foodCategories;
    }
    
    /**
     * Find a food category by its exact name
     * @param name The name to search for
     * @return The matching food category or null if not found
     * @throws SQLException if database error occurs
     */
    public FoodCategory findByName(String name) throws SQLException {
        String query = "SELECT * FROM `food_category` WHERE `name` = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return FoodCategory.getFromResultSet(rs);
                }
            }
        }
        return null;
    }
    
    @Override
    protected void validate(FoodCategory category) throws SQLException {
        if (category == null) {
            throw new SQLException("Food category object cannot be null");
        }
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new SQLException("Food category name cannot be empty");
        }
        if (category.getSlug() == null || category.getSlug().trim().isEmpty()) {
            throw new SQLException("Food category slug cannot be empty");
        }
    }
}
