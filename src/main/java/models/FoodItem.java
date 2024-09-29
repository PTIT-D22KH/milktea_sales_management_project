package models;

import java.text.DecimalFormat;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodItem extends Model {
    private int id;
    private String name, description, urlImage, unitName;
    private int unitPrice, idCategory;
    DecimalFormat formatter = new DecimalFormat("###, ###,###");

    public FoodItem() {
    }
    public int getID() {
        return this.id;
    }
    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrlImage() {
        return this.urlImage;
    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUnitName() {
        return this.unitName;
    }
    public void setUnitName(String unitname) {
        this.unitName = unitname;
    }
    public Integer getUnitPrice() {
        return this.unitPrice;
    }
    public void setUnitPrice(String unitprice) {
        this.unitPrice = unitPrice;
    }
    public int getIDCategory() {
        return this.idCategory;
    }
    public void setIDCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, formatter.format(this.unitPrice));
    }

    

}
