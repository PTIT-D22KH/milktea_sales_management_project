/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.math.BigInteger;

/**
 *
 * @author DELL
 */
public class OderItem extends Model{
    private int orderId,foodItemId,toppingId,quantity;
    private BigInteger foodPrice,toppingPrice;
    private String note;
    private FoodItem foodItem, toppingItem;

    public OderItem() {
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
        this.quantity = quantity;
    }

    public BigInteger getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigInteger foodPrice) {
        this.foodPrice = foodPrice;
    }

    public BigInteger getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(BigInteger toppingPrice) {
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
    }

    public FoodItem getToppingItem() {
        return toppingItem;
    }

    public void setToppingItem(FoodItem toppingItem) {
        this.toppingItem = toppingItem;
    }

    @Override
    public String toString() {
        return "OderItem{" + "orderId=" + orderId + ", foodItemId=" + foodItemId + ", toppingId=" + toppingId + ", quantity=" + quantity + ", foodPrice=" + foodPrice + ", toppingPrice=" + toppingPrice + ", note=" + note + ", foodItem=" + foodItem + ", toppingItem=" + toppingItem + '}';
    }
    
    
}
