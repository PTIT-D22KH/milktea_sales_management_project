//package models;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.DecimalFormat;
//import static org.mockito.Mockito.*;
//
///**
// * Test class for FoodItem
// */
//public class FoodItemTest {
//    
//    public FoodItemTest() {
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
//    public void testFoodItemConstructor() {
//        FoodItem fi = new FoodItem();
//        assertNotNull(fi);
//    }
//
//    @Test
//    public void testSetAndGetFoodItemId() {
//        FoodItem fi = new FoodItem();
//        fi.setFoodItemId(1);
//        assertEquals(1, fi.getFoodItemId());
//    }
//
//    @Test
//    public void testSetAndGetName() {
//        FoodItem fi = new FoodItem();
//        fi.setName("Pizza");
//        assertEquals("Pizza", fi.getName());
//    }
//
//    @Test
//    public void testSetAndGetDescription() {
//        FoodItem fi = new FoodItem();
//        fi.setDescription("Delicious cheese pizza");
//        assertEquals("Delicious cheese pizza", fi.getDescription());
//    }
//
//    @Test
//    public void testSetAndGetImagePath() {
//        FoodItem fi = new FoodItem();
//        fi.setImagePath("/images/pizza.jpg");
//        assertEquals("/images/pizza.jpg", fi.getImagePath());
//    }
//
//    @Test
//    public void testSetAndGetUnitName() {
//        FoodItem fi = new FoodItem();
//        fi.setUnitName("slice");
//        assertEquals("slice", fi.getUnitName());
//    }
//
//    @Test
//    public void testSetAndGetUnitPrice() {
//        FoodItem fi = new FoodItem();
//        fi.setUnitPrice(1500);
//        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        String formatPrice = formatter.format(fi.getUnitPrice());
//        assertEquals("1,500", formatPrice);
//        assertEquals(1500, fi.getUnitPrice());
//    }
//
//    @Test
//    public void testSetAndGetCategoryId() {
//        FoodItem fi = new FoodItem();
//        fi.setCategoryId(2);
//        assertEquals(2, fi.getCategoryId());
//    }
//
//    @Test
//    public void testToString() {
//        FoodItem fi = new FoodItem();
//        fi.setName("Pizza");
//        fi.setUnitPrice(1500);
//
//        String expected = "Pizza (1,500)";
//        assertEquals(expected, fi.toString());
//    }
//
//    @Test
//    public void testGetFromResultSet() throws SQLException {
//        ResultSet rs = mock(ResultSet.class);
//        when(rs.getInt("foodItemId")).thenReturn(1);
//        when(rs.getNString("name")).thenReturn("Pizza");
//        when(rs.getNString("description")).thenReturn("Delicious cheese pizza");
//        when(rs.getNString("imagePath")).thenReturn("/images/pizza.jpg");
//        when(rs.getNString("unitName")).thenReturn("slice");
//        when(rs.getInt("unitPrice")).thenReturn(1500);
//        when(rs.getInt("categoryId")).thenReturn(2);
//
//        FoodItem fi = new FoodItem();
//        fi.setFoodItemId(rs.getInt("foodItemId"));
//        fi.setName(rs.getNString("name"));
//        fi.setDescription(rs.getNString("description"));
//        fi.setImagePath(rs.getNString("imagePath"));
//        fi.setUnitName(rs.getNString("unitName"));
//        fi.setUnitPrice(rs.getInt("unitPrice"));
//        fi.setCategoryId(rs.getInt("categoryId"));
//
//        assertEquals(1, fi.getFoodItemId());
//        assertEquals("Pizza", fi.getName());
//        assertEquals("Delicious cheese pizza", fi.getDescription());
//        assertEquals("/images/pizza.jpg", fi.getImagePath());
//        assertEquals("slice", fi.getUnitName());
//        assertEquals(1500, fi.getUnitPrice());
//        assertEquals(2, fi.getCategoryId());
//    }
//}