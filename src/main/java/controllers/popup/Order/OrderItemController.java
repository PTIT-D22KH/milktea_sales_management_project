/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup.order;

import dao.OrderDao;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.OrderItem;
import views.popup.order.OrderItemPane;

/**
 *
 * @author DELL
 */
public class OrderItemController {
    private ArrayList<OrderItem> orderItems;
    private JPanel orderItemPanel;
    private int orderId;

    public interface Event {

        public abstract void onChange();
    }
    private Event onQuantityChange;

    public OrderItemController() {
    }

    public JPanel getOrderItemPanel() {
        return orderItemPanel;
    }

    public void setOrderItemPanel(JPanel orderItemPanel) {
        this.orderItemPanel = orderItemPanel;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    

    public void renderOrderItem() {
        orderItemPanel.removeAll();
        for (OrderItem orderItem : orderItems) {
            OrderItemPane pane = new OrderItemPane(orderItem);
            pane.getSpnQuantity().addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    orderItem.setQuantity((int) pane.getSpnQuantity().getValue());
                    if (onQuantityChange != null) {
                        onQuantityChange.onChange(); // Gọi hàm update amount
                    }
                }
            }
            );
            orderItemPanel.add(pane);
        }
        orderItemPanel.updateUI();
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
        renderOrderItem();
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (OrderItem orderItem : orderItems) {
            totalAmount += orderItem.getAmount();
        }
        return totalAmount;
    }

    public void addOrderItem(OrderItem item) throws SQLException{
        if (item == null) {
            return;
        }
        OrderDao orderDao = new OrderDao();
        item.setOrder(orderDao.getById(orderId));
        for (OrderItem orderItem : orderItems) {
            if (item.equals(orderItem)) {
                orderItem.setQuantity(orderItem.getQuantity() + item.getQuantity());
//                orderItem.setFoodPrice(item.getFoodPrice());
                renderOrderItem();
                return;
            }
        }
        orderItems.add(item);
        renderOrderItem();
    }

    public void setOnQuantityChange(Event onQuantityChange) {
        this.onQuantityChange = onQuantityChange;
    }

}
