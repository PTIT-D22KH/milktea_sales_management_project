/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.statistical;

import models.FoodItem;

/**
 *
 * @author P51
 */
public class ProductIncome {
    private FoodItem item;
    private int quantity;
    private int amount;
    private int id;
    private String name;

    public ProductIncome(FoodItem item, int quantity, int amount, int id, String name) {
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
        this.id = id;
        this.name = name;
    }

    public ProductIncome(int quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }

    public ProductIncome(int quantity, int amount, int id, String name) {
        this.quantity = quantity;
        this.amount = amount;
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductIncome{" + "item=" + item + ", quantity=" + quantity + ", amount=" + amount + ", id=" + id + ", name=" + name + '}';
    }
    
    
    
    
    
}
