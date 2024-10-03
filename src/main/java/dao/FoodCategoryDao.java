package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.FoodCategory;

public class FoodCategoryDao extends Dao<FoodCategory> {
    
    public FoodCategoryDao(){
        
    }
    
    @Override
    public ArrayList<FoodCategory> getAll() throws SQLException {
        ArrayList<FoodCategory> foodCategories = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_category`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            FoodCategory fc = FoodCategory.getFromResultSet(rs);
            foodCategories.add(fc);
        }
        return foodCategories;
    }
    
    @Override
    public FoodCategory getById(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM "
                + "`food_category` "
                + "WHERE `foodCategoryId` = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()){
            FoodCategory fc = FoodCategory.getFromResultSet(rs);
            return fc;
        }
        return null;
    }
    
    @Override
    public void save(FoodCategory t) throws SQLException {
        if (t == null){
            throw new SQLException("Cannot insert into table null object(food_category)");
        }
        String query = "INSERT INTO `food_category` "
                + "(`name`, `slug`) VALUES (?, ?)";
        
        PreparedStatement prStatement = conn.prepareStatement(query);
        prStatement.setNString(1, t.getName());
        prStatement.setNString(2, t.getSlug());
        int row = prStatement.executeUpdate();
    }
    
    @Override
    public void update(FoodCategory t) throws SQLException{
        if (t == null){
            throw new SQLException("Cannot update food_category table when food_category instance is null");
        }
        String query = "UPDATE `food_category` "
                + "SET `name` = ?, `slug` = ? WHERE `foodCategoryId` = ?";
        
        PreparedStatement prStatement = conn.prepareStatement(query);
        prStatement.setNString(1, t.getName());
        prStatement.setNString(2, t.getSlug());
        prStatement.setInt(3, t.getFoodCategoryId());
        int row = prStatement.executeUpdate();
    }
    
    @Override
    public void delete(FoodCategory t) throws SQLException {
        String query = "DELETE FROM `food_category` WHERE `foodCategoryId` = ?";
        PreparedStatement prStatement = conn.prepareStatement(query);
        prStatement.setInt(1, t.getFoodCategoryId());
        int row = prStatement.executeUpdate();
    }
    
    @Override
    public void deleteById(int id) throws SQLException {
        String query = "DELETE FROM `food_category` WHERE `foodCategoryId` = ?";
        PreparedStatement prStatement = conn.prepareStatement(query);
        prStatement.setInt(1, id);
        int row = prStatement.executeUpdate();
    }
    
    public FoodCategory findByName(String name) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_category` "
                + "WHERE `name` = '" + name + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()){
            FoodCategory fc = FoodCategory.getFromResultSet(rs);
            return fc;
        }
        return null;
    }
    
    public ArrayList<FoodCategory> searchByKey(String key, String word) throws SQLException {
        ArrayList<FoodCategory> foodCategories = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_category` "
                + "WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            FoodCategory foodCategory = FoodCategory.getFromResultSet(rs);
            foodCategories.add(foodCategory);
        }
        return foodCategories;
    }
}
