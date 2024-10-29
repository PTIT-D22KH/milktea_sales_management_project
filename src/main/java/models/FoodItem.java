package models;

import java.text.DecimalFormat;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodItem extends Model {
    private int foodItemId, unitPrice;
    private String name, description, imagePath, unitName;
    private final DecimalFormat formatter = new DecimalFormat("###,###,###");
    private FoodCategory foodCategory;
    public FoodItem() {
        foodCategory = null;
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

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }
    

    @Override
    public String toString() {
        return String.format("%s (%s)", name, formatter.format(unitPrice));
    }
    public static FoodItem getFromResultSet(ResultSet rs) throws SQLException {
        FoodItem f = new FoodItem();
        f.setFoodItemId(rs.getInt("foodItemId"));
        f.setName(rs.getNString("name"));
        f.setDescription(rs.getNString("description"));
        f.setImagePath(rs.getNString("ImagePath"));
        f.setUnitName(rs.getNString("unitName"));
        f.setUnitPrice(rs.getInt("unitPrice"));
//        f.setCategoryId(rs.getInt("foodCategoryId"));
        return f;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
            foodItemId, name, description, imagePath, unitName,
            unitPrice, foodCategory.getFoodCategoryId()
        };
    }

    @Override
    public String getClassName() {
        return "món ăn";
    }
    

}
