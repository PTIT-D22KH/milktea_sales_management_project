package models;

import utils.StringToSlug;
import java.sql.SQLException;
import java.sql.ResultSet;

public class FoodCategory extends Model {

    private int foodCategoryId;
    private String name;
    private String slug;
    
    public FoodCategory(){
        
    }

    public int getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(int foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //Set slug as well
        this.slug = StringToSlug.convert(name);
    }

    public String getSlug() {
        return StringToSlug.convert(name);
    }

//    public void setSlug(String slug) {
//        this.slug = slug;
//    }
    
    public static FoodCategory getFromResultSet(ResultSet rs)
            throws SQLException {
        FoodCategory fc = new FoodCategory();
        fc.setFoodCategoryId(rs.getInt("foodCategoryId"));
        fc.setName(rs.getNString("name"));
        
        return fc;
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
            foodCategoryId, name
        };
    }

    @Override
    public String getClassName() {
        return "loại món";
    }
}
