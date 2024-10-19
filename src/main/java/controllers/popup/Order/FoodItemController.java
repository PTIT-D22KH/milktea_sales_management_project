/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup.order;

import dao.FoodCategoryDao;
import dao.FoodItemDao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import models.FoodCategory;
import models.FoodItem;
import views.popup.order.FoodCategoryPane;
import views.popup.order.FoodItemPane;
/**
 *
 * @author P51
 */
public class FoodItemController {
    private final FoodCategoryDao foodCategoryDao;
    private final FoodItemDao foodItemDao;
    private JPanel foodCategoryPanel, foodItemPanel;
    
    public interface Event {
        public abstract void onChange(FoodItem panel);
    }
    public FoodItemController() {
        this.foodCategoryDao = new FoodCategoryDao();
        this.foodItemDao = new FoodItemDao();
        
    }

    public JPanel getFoodCategoryPanel() {
        return foodCategoryPanel;
    }

    public void setFoodCategoryPanel(JPanel foodCategoryPanel) {
        this.foodCategoryPanel = foodCategoryPanel;
    }

    public JPanel getFoodItemPanel() {
        return foodItemPanel;
    }

    public void setFoodItemPanel(JPanel foodItemPanel) {
        this.foodItemPanel = foodItemPanel;
    }
    
    public void renderCategory(Event event) throws Exception {
        foodCategoryPanel.removeAll();
        renderFoodItem(2, event); // tra sua
        for (FoodCategory foodCategory : foodCategoryDao.getAll()) {
            FoodCategoryPane panel = new FoodCategoryPane(foodCategory);
            foodCategoryPanel.add(panel);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    FoodCategory foodCategory = panel.getFoodCategory();
                    try {
                        renderFoodItem(foodCategory.getFoodCategoryId(), event);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }
    }
    public void renderFoodItem(int categoryId, Event event) throws Exception {
        foodItemPanel.removeAll();
        for (FoodItem foodItem : foodItemDao.getByCategoryId(categoryId)) {
            FoodItemPane panel = new FoodItemPane(foodItem);
            foodItemPanel.add(panel);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    event.onChange(panel.getFoodItem());
                }
            });
        }
        foodItemPanel.updateUI();
    }
}
