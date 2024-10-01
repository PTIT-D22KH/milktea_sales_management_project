package models;

import utils.StringToSlug;

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
        return slug;
    }

//    public void setSlug(String slug) {
//        this.slug = slug;
//    }

    @Override
    public String toString() {
        return "";
    }
}
