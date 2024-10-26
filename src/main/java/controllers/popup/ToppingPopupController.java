/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.FoodItemDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.FoodItem;
import models.OrderItem;
import views.popup.ToppingPopupView;

/**
 *
 * @author namle
 */
public class ToppingPopupController {
    private final FoodItemDao foodItemDao;
    private final DecimalFormat formatter;
    private JFrame previousView;

    interface Event {

        public abstract void onSelect(OrderItem item);
    }

    public ToppingPopupController() {
        this.foodItemDao = new FoodItemDao();
        this.formatter = new DecimalFormat("###,###,###");
    }
    public ToppingPopupController(FoodItemDao foodItemDao) {
        this.foodItemDao = foodItemDao;
        this.formatter = new DecimalFormat("###,###,###");
    }
    public void add(ToppingPopupView view, FoodItem foodItem, Event event) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getLbFoodName().setText(foodItem.getName());
        view.getSpnFoodPrice().setValue(foodItem.getUnitPrice());
        try {
            //Hiện danh sách topping
            for (FoodItem topping : foodItemDao.getByCategoryId(4)) {
                view.getCboTopping().addItem(topping);
            }
            //Ẩn topping nếu k phải trà sữa
            if (foodItem.getFoodCategory().getFoodCategoryId()!= 2) {
                view.getLbTopping().setVisible(false);
                view.getCboTopping().setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateAmount(view);
        view.getBtnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                event.onSelect(addItem(foodItem, view));
                view.dispose();
            }
        });
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                view.dispose();
            }
        });
        view.getSpnFoodPrice().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                updateAmount(view);
            }
        });
        view.getSpnQuantity().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                updateAmount(view);
            }
        });
        view.getCboTopping().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                updateAmount(view);
            }
        });
    }

    private void updateAmount(ToppingPopupView view) {
        int amount = (int) view.getSpnFoodPrice().getValue();
        FoodItem topping = (FoodItem) view.getCboTopping().getSelectedItem();
        if (topping != null) {
            amount += topping.getUnitPrice();
        }
        int quantity = (int) view.getSpnQuantity().getValue();
        amount *= quantity;
        view.getLbAmount().setText(formatter.format(amount));
    }

    private OrderItem addItem(FoodItem foodItem, ToppingPopupView view) {
        OrderItem orderItem = new OrderItem();
        try {
            orderItem.setFoodItem(foodItem);
            orderItem.setFoodPrice((int) view.getSpnFoodPrice().getValue());
            if (foodItem.getFoodCategory().getFoodCategoryId()== 2) {
                orderItem.setToppingItem((FoodItem) view.getCboTopping().getSelectedItem());
                orderItem.setToppingPrice(orderItem.getToppingItem().getUnitPrice());
            } else {
                orderItem.setToppingItem(foodItemDao.getById(1));
                orderItem.setToppingPrice(0);
            }
            orderItem.setQuantity((int) view.getSpnQuantity().getValue());

            return orderItem;
        } catch (Exception e) {
            return orderItem;
        }
    }
    
}
