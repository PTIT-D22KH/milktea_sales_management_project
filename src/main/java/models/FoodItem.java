package models;

import java.text.DecimalFormat;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodItem extends Model {
    private int foodItemId;
    private String name, description, imagePath, unitName;
    private int unitPrice, categoryId;
    DecimalFormat formatter = new DecimalFormat("###, ###,###");

    public FoodItem() {
    }

    public int getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    

    @Override
    public String toString() {
        return String.format("%s (%s)", name, formatter.format(unitPrice));
    }

    

}
