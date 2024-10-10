///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package dao;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import models.FoodCategory;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Test class for FoodCategoryDao
// */
//public class FoodCategoryDaoTest {
//    FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
//
//    public FoodCategoryDaoTest() {
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
//    @Test
//    public void testGetAll() throws SQLException {
//        ArrayList<FoodCategory> foodCategories = foodCategoryDao.getAll();
//        assertNotNull(foodCategories);
//        assertFalse(foodCategories.isEmpty());
//        FoodCategory firstFoodCategory = foodCategories.get(0);
//        assertNotNull(firstFoodCategory);
//    }
//
//    @Test
//    public void testGetById() throws SQLException {
//        FoodCategory foodCategory = foodCategoryDao.getById(1);
//        assertNotNull(foodCategory);
//        assertEquals(1, foodCategory.getFoodCategoryId());
//    }
//
//    @Test
//    public void testSaveNull() throws SQLException {
//        FoodCategory foodCategory = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            foodCategoryDao.save(foodCategory);
//        });
//        assertEquals("Cannot insert into table null object(food_category)", exception.getMessage());
//    }
//
//    @Test
//    public void testSaveNotNull() throws SQLException {
//        FoodCategory foodCategory = new FoodCategory();
//        foodCategory.setName("Test Category");
////        foodCategory.setSlug("test-category");
//
//        foodCategoryDao.save(foodCategory);
//        FoodCategory savedFoodCategory = foodCategoryDao.findByName("Test Category");
//        assertNotNull(savedFoodCategory);
//        assertEquals("Test Category", savedFoodCategory.getName());
//        assertEquals("test-category", savedFoodCategory.getSlug());
//    }
//
//    @Test
//    public void testUpdateNull() throws SQLException {
//        FoodCategory foodCategory = null;
//        SQLException exception = assertThrows(SQLException.class, () -> {
//            foodCategoryDao.update(foodCategory);
//        });
//        assertEquals("Cannot update food_category table when food_category instance is null", exception.getMessage());
//    }
//
//    @Test
//    public void testUpdateNotNull() throws SQLException {
////        testSaveNotNull();
//        FoodCategory foodCategory = foodCategoryDao.findByName("Test Category");
//        assertNotNull(foodCategory);
//        foodCategory.setName("Updated Category NEW TEST");
//        foodCategoryDao.update(foodCategory);
//        FoodCategory updatedFoodCategory = foodCategoryDao.findByName("Updated Category NEW TEST");
//        assertEquals("updated-category-new-test", updatedFoodCategory.getSlug());
//    }
//
//    @Test
//    public void testDelete() throws SQLException {
//        testSaveNotNull();
//        FoodCategory foodCategory = foodCategoryDao.findByName("Test Category");
//        assertNotNull(foodCategory);
//        foodCategoryDao.delete(foodCategory);
//        FoodCategory deletedFoodCategory = foodCategoryDao.findByName("Test Category");
//        assertNull(deletedFoodCategory);
//    }
//
//    @Test
//    public void testDeleteById() throws SQLException {
//        FoodCategory foodCategory = foodCategoryDao.findByName("Updated Category");
//        assertNotNull(foodCategory);
//        foodCategoryDao.deleteById(5);
////        FoodCategory deletedFoodCategory = foodCategoryDao.findByName("Test Category");
////        assertNull(deletedFoodCategory);
//    }
//
//    @Test
//    public void testFindByName() throws SQLException {
//        testSaveNotNull();
//        FoodCategory foodCategory = foodCategoryDao.findByName("Test Category");
//        assertNotNull(foodCategory);
//        assertEquals("Test Category", foodCategory.getName());
//    }
//
//    @Test
//    public void testSearchByKey() throws SQLException {
////        testSaveNotNull();
//        ArrayList<FoodCategory> foodCategories = foodCategoryDao.searchByKey("name", "Test");
//        assertNotNull(foodCategories);
//        assertFalse(foodCategories.isEmpty());
//        for (FoodCategory foodCategory : foodCategories) {
//            assertTrue(foodCategory.getName().contains("Test"));
//        }
//    }
//}