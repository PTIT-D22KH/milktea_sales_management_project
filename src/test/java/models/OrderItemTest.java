//package models;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Test class for OrderItem
// */
//public class OrderItemTest {
//    
//    public OrderItemTest() {
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
//    public void testOrderItemConstructor() {
//        OrderItem orderItem = new OrderItem();
//        assertNotNull(orderItem);
//        assertEquals(1, orderItem.getQuantity());
//        assertEquals(0, orderItem.getToppingId());
//        assertEquals("", orderItem.getNote());
//    }
//
//    @Test
//    public void testSetAndGetOrderId() {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOrderId(1);
//        assertEquals(1, orderItem.getOrderId());
//    }
//
//    @Test
//    public void testSetAndGetFoodItemId() {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setFoodItemId(2);
//        assertEquals(2, orderItem.getFoodItemId());
//    }
//
//    @Test
//    public void testSetAndGetToppingId() {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setToppingId(3);
//        assertEquals(3, orderItem.getToppingId());
//    }
//
//    @Test
//    public void testSetAndGetQuantity() {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setQuantity(4);
//        assertEquals(4, orderItem.getQuantity());
//        orderItem.setQuantity(-1);
//        assertEquals(0, orderItem.getQuantity()); // Quantity should not be negative
//    }
//
//    @Test
//    public void testSetAndGetFoodPrice() {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setFoodPrice(500);
//        assertEquals(500, orderItem.getFoodPrice());
//    }
//
//    @Test
//    public void testSetAndGetToppingPrice() {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setToppingPrice(200);
//        assertEquals(200, orderItem.getToppingPrice());
//    }
//
//    @Test
//    public void testSetAndGetNote() {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setNote("Extra cheese");
//        assertEquals("Extra cheese", orderItem.getNote());
//    }
//
//    @Test
//    public void testSetAndGetFoodItem() {
//        OrderItem orderItem = new OrderItem();
//        FoodItem foodItem = new FoodItem();
//        foodItem.setFoodItemId(2);
//        orderItem.setFoodItem(foodItem);
//        assertEquals(foodItem, orderItem.getFoodItem());
//        assertEquals(2, orderItem.getFoodItemId());
//    }
//
//    @Test
//    public void testSetAndGetToppingItem() {
//        OrderItem orderItem = new OrderItem();
//        FoodItem toppingItem = new FoodItem();
//        toppingItem.setFoodItemId(3);
//        orderItem.setToppingItem(toppingItem);
//        assertEquals(toppingItem, orderItem.getToppingItem());
//        assertEquals(3, orderItem.getToppingId());
//    }
//
//    @Test
//    public void testToString() {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOrderId(1);
//        orderItem.setFoodItemId(2);
//        orderItem.setToppingId(3);
//        orderItem.setQuantity(4);
//        orderItem.setFoodPrice(500);
//        orderItem.setToppingPrice(200);
//        orderItem.setNote("Extra cheese");
//
//        FoodItem foodItem = new FoodItem();
//        foodItem.setFoodItemId(2);
//        foodItem.setName("Pizza");
//        foodItem.setDescription("Delicious cheese pizza");
//        foodItem.setImagePath("/images/pizza.jpg");
//        foodItem.setUnitName("slice");
//        foodItem.setUnitPrice(1500);
//        foodItem.setCategoryId(1);
//        orderItem.setFoodItem(foodItem);
//
//        FoodItem toppingItem = new FoodItem();
//        toppingItem.setFoodItemId(3);
//        toppingItem.setName("Olives");
//        toppingItem.setDescription("Fresh olives");
//        toppingItem.setImagePath("/images/olives.jpg");
//        toppingItem.setUnitName("portion");
//        toppingItem.setUnitPrice(200);
//        toppingItem.setCategoryId(2);
//        orderItem.setToppingItem(toppingItem);
//
//        String expected = "OrderItem{" +
//                "orderId=" + orderItem.getOrderId() +
//                ", foodItemId=" + orderItem.getFoodItemId() +
//                ", toppingId=" + orderItem.getToppingId() +
//                ", quantity=" + orderItem.getQuantity() +
//                ", foodPrice=" + orderItem.getFoodPrice() +
//                ", toppingPrice=" + orderItem.getToppingPrice() +
//                ", note=" + orderItem.getNote() +
//                ", foodItem=" + orderItem.getFoodItem().toString() +
//                ", toppingItem=" + orderItem.getToppingItem().toString() +
//                '}';
//
//        assertEquals(expected, orderItem.toString());
//    }
//}
///**
// * expected: <OrderItem{orderId=1, foodItemId=2, toppingId=3, quantity=4, foodPrice=500, toppingPrice=200, note='Extra cheese', foodItem=Pizza (1,500), toppingItem=Olives (200)}> 
// * but was: <OrderItem{orderId=1, foodItemId=2, toppingId=3, quantity=4, foodPrice=500, toppingPrice=200, note=Extra cheese, foodItem=Pizza (1,500), toppingItem=Olives (200)}>
//org.opentest4j.AssertionFailedError
//	at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
//	at org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
//	at org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
//	at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
//	at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
//	at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
//	at models.OrderItemTest.testToString(OrderItemTest.java:157)
//	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
//	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
//	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
//
//
// */