/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class OrderItem extends Model{
    private int orderId, foodItemId, toppingId, quantity, foodPrice,toppingPrice;
    private String note;
    private FoodItem foodItem, toppingItem;

    public OrderItem() {
        quantity = 1;
        toppingId = 0;
        note = "";
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            this.quantity = 0;
        }
//        this.quantity = quantity;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(int toppingPrice) {
        this.toppingPrice = toppingPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
        this.foodItemId = foodItem.getFoodItemId();
    }

    public FoodItem getToppingItem() {
        return toppingItem;
    }

    public void setToppingItem(FoodItem toppingItem) {
        this.toppingItem = toppingItem;
        this.toppingId = toppingItem.getFoodItemId();
    }
    
    public int getAmount() {
        return quantity * (foodPrice + toppingPrice);
    }
  
    @Override
    public String toString() {
        return "OrderItem{" + "orderId=" + orderId + ", foodItemId=" + foodItemId + ", toppingId=" + toppingId + ", quantity=" + quantity + ", foodPrice=" + foodPrice + ", toppingPrice=" + toppingPrice + ", note=" + note + ", foodItem=" + foodItem + ", toppingItem=" + toppingItem + '}';
    }
    public static OrderItem getFromResultSet(ResultSet rs) throws SQLException {
        OrderItem oi = new OrderItem();
        oi.setFoodItemId(rs.getInt("foodItemId"));
        oi.setOrderId(rs.getInt("orderId"));
        oi.setToppingId(rs.getInt("toppingId"));
        oi.setQuantity(rs.getInt("quantity"));
        oi.setFoodPrice(rs.getInt("foodPrice"));
        oi.setToppingPrice(rs.getInt("toppingPrice"));
        oi.setNote(rs.getNString("note"));
        return oi;
    }

    @Override
    public Object[] toRowTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
