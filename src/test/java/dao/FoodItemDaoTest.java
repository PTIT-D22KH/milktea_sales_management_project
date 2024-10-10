///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package dao;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import models.FoodItem;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Test class for FoodItemDao
// */
//public class FoodItemDaoTest {
//    FoodItemDao foodItemDao = new FoodItemDao();
//
//    public FoodItemDaoTest() {
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
//    }
//
//    @BeforeEach
//    public void setUp() {
//    }
//
//    @AfterEach
//    public void tearDown() {
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
//
//    @Test
//    public void testGetAll() throws SQLException {
//        ArrayList<FoodItem> foodItems = foodItemDao.getAll();
//        assertEquals(17, foodItems.size()); // Adjust the expected size as needed
//        FoodItem firstItem = foodItems.get(0);
//        for (FoodItem x : foodItems) {
//            System.out.println(x);
//        }
//        assertEquals("Bánh Flan", firstItem.getName()); // Adjust the expected name as needed
//    }
//
//    @Test
//    public void testGetById() throws SQLException {
//        FoodItem foodItem = foodItemDao.getById(1);
//        assertNotNull(foodItem);
//        assertEquals("No Topping", foodItem.getName()); // Adjust the expected name as needed
//    }
//
//    @Test
//    public void testSaveNull() throws SQLException {
//        FoodItem foodItem = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            foodItemDao.save(foodItem);
//        });
//        assertEquals("Food item rong", exception.getMessage());
//    }
//
//    @Test
//    public void testSaveNotNull() throws SQLException {
//        FoodItem foodItem = new FoodItem();
//        foodItem.setName("Burger");
//        foodItem.setDescription("Delicious beef burger");
//        foodItem.setImagePath("/images/burger.jpg");
//        foodItem.setUnitName("piece");
//        foodItem.setUnitPrice(500);
//        foodItem.setCategoryId(1);
//        foodItemDao.save(foodItem);
//        
////        FoodItem savedFoodItem = foodItemDao.getById(foodItem.getFoodItemId());
////        assertEquals("Burger", savedFoodItem.getName());
////        assertEquals("Delicious beef burger", savedFoodItem.getDescription());
//    }
//
//    @Test
//    public void testUpdateNull() throws SQLException {
//        FoodItem foodItem = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            foodItemDao.update(foodItem);
//        });
//        assertEquals("Food item rong", exception.getMessage());
//    }
//
//    @Test
//    public void testUpdateNotNull() throws SQLException {
//        FoodItem foodItem = foodItemDao.getById(19);
//        foodItem.setDescription("Updated description");
//        foodItemDao.update(foodItem);
//
//        FoodItem updatedFoodItem = foodItemDao.getById(19);
//        assertEquals("Updated description", updatedFoodItem.getDescription());
//    }
//
//    @Test
//    public void testDelete() throws SQLException {
//        // Ensure the food item with ID 1 exists
//        FoodItem foodItem = foodItemDao.getById(19);
//        if (foodItem == null) {
//            // Create and save a new food item with ID 1 if it does not exist
//            foodItem = new FoodItem();
//            foodItem.setFoodItemId(19); // Set the ID to 1
//            foodItem.setName("Test Delete");
//            foodItem.setDescription("Test description");
//            foodItem.setImagePath("/images/test.jpg");
//            foodItem.setUnitName("piece");
//            foodItem.setUnitPrice(100);
//            foodItem.setCategoryId(1);
//            foodItemDao.save(foodItem);
//        }
//
//        // Delete the food item
//        foodItemDao.delete(foodItem);
//
//        // Verify that the food item has been deleted
//        FoodItem deletedFoodItem = foodItemDao.getById(19);
//        assertNull(deletedFoodItem);
//    }
//
//    @Test
//    public void testDeleteById() throws SQLException {
//        // First, save a new food item to ensure there is a food item with a known ID to delete
//        FoodItem foodItem = new FoodItem();
//        foodItem.setName("Test Delete");
//        foodItem.setDescription("Test description");
//        foodItem.setImagePath("/images/test.jpg");
//        foodItem.setUnitName("piece");
//        foodItem.setUnitPrice(100);
//        foodItem.setCategoryId(1);
//        foodItemDao.save(foodItem);
//
//        // Retrieve the newly saved food item to get its ID
//        FoodItem savedFoodItem = foodItemDao.getById(20);
//        assertNotNull(savedFoodItem);
//        int foodItemIdToDelete = savedFoodItem.getFoodItemId();
//
//        // Delete the food item by ID
//        foodItemDao.deleteById(foodItemIdToDelete);
//
//        // Verify that the food item has been deleted
//        FoodItem deletedFoodItem = foodItemDao.getById(20);
//        assertNull(deletedFoodItem);
//    }
//}