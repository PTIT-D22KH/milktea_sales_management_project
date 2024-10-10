//package models;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import utils.StringToSlug;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import static org.mockito.Mockito.*;
//
///**
// * Test class for FoodCategory
// */
//public class FoodCategoryTest {
//    
//    public FoodCategoryTest() {
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
//    public void testFoodCategoryConstructor() {
//        FoodCategory fc = new FoodCategory();
//        assertNotNull(fc);
//    }
//
//    @Test
//    public void testSetAndGetFoodCategoryId() {
//        FoodCategory fc = new FoodCategory();
//        fc.setFoodCategoryId(1);
//        assertEquals(1, fc.getFoodCategoryId());
//    }
//
//    @Test
//    public void testSetAndGetName() {
//        FoodCategory fc = new FoodCategory();
//        fc.setName("Desserts");
//        assertEquals("Desserts", fc.getName());
//        assertEquals(StringToSlug.convert("Desserts"), fc.getSlug());
//    }
//
//    @Test
//    public void testGetSlug() {
//        FoodCategory fc = new FoodCategory();
//        fc.setName("Main Course");
//        assertEquals(StringToSlug.convert("Main Course"), fc.getSlug());
//    }
//
//    @Test
//    public void testGetFromResultSet() throws SQLException {
//        ResultSet rs = mock(ResultSet.class);
//        when(rs.getInt("foodCategoryId")).thenReturn(1);
//        when(rs.getNString("name")).thenReturn("Appetizers");
//
//        FoodCategory fc = FoodCategory.getFromResultSet(rs);
//
//        assertEquals(1, fc.getFoodCategoryId());
//        assertEquals("Appetizers", fc.getName());
//        assertEquals(StringToSlug.convert("Appetizers"), fc.getSlug());
//    }
//
//    @Test
//    public void testToString() {
//        FoodCategory fc = new FoodCategory();
//        fc.setFoodCategoryId(1);
//        fc.setName("Beverages");
//
//        String expected = "1 Beverages " + StringToSlug.convert("Beverages");
//        assertEquals(expected, fc.toString());
//    }
//}