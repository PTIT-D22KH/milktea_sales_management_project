/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;
import controllers.OrderPrintController;
import controllers.popup.Order.OrderItemController;
import controllers.popup.order.FoodItemController;
import dao.EmployeeDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.ShipmentDao;
import dao.TableDao;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import models.Order;
import models.OrderItem;
import utils.OrderStatus;
import utils.OrderType;
import utils.TableStatus;
import views.popup.EditOrderPopupView;
/**
 *
 * @author P51
 */
public class EditOrderPopupController extends PopupController<EditOrderPopupView, Order> {
    private OrderDao orderDao;
    private EmployeeDao employeeDao;
    private ShipmentDao shipmentDao;
    private TableDao tableDao;
    private OrderItemDao orderItemDao;
    private FoodItemController foodItemController;
    private OrderItemController orderItemController;    
    private ToppingPopupController toppingPopupController;
    private ShipmentPopupControler shipmentPopupControler;
    private OrderPrintController orderPrintController;
    private DecimalFormat formatter;

    public EditOrderPopupController() {
        this.orderDao = new OrderDao();
        this.employeeDao = new EmployeeDao();
        this.shipmentDao = new ShipmentDao();
        this.tableDao = new TableDao();
        this.orderItemDao = new OrderItemDao();
        this.foodItemController = new FoodItemController();
        this.orderItemController = new OrderItemController();
        this.toppingPopupController = new ToppingPopupController();
        this.shipmentPopupControler = new ShipmentPopupControler();
        this.orderPrintController = new OrderPrintController();
        this.formatter = new DecimalFormat("###,###,###");
    }

    public EditOrderPopupController(OrderDao orderDao, EmployeeDao employeeDao, ShipmentDao shipmentDao, TableDao tableDao, OrderItemDao orderItemDao, FoodItemController foodItemController, OrderItemController orderItemController, ToppingPopupController toppingPopupController, ShipmentPopupControler shipmentPopupControler, OrderPrintController orderPrintController) {
        this.orderDao = orderDao;
        this.employeeDao = employeeDao;
        this.shipmentDao = shipmentDao;
        this.tableDao = tableDao;
        this.orderItemDao = orderItemDao;
        this.foodItemController = foodItemController;
        this.orderItemController = orderItemController;
        this.toppingPopupController = toppingPopupController;
        this.shipmentPopupControler = shipmentPopupControler;
        this.orderPrintController = orderPrintController;
        this.formatter = new DecimalFormat("###,###,###");
    }

    public void updateAmount(EditOrderPopupView view, Order order) {
        order.setTotalAmount(orderItemController.getTotalAmount());
        view.getLbStatus().setText(order.getStatus().getName());
        view.getLbDiscount().setText(order.getDiscount() + "");
        view.getLbPaidAmount().setText(formatter.format(order.getPaidAmount()));
        view.getLbFinalAmount().setText(formatter.format(order.getFinalAmount()));
        view.getLbTotalAmount().setText(formatter.format(order.getTotalAmount()));
        view.getRebateAmountLabel().setText(formatter.format(order.getFinalAmount() - order.getPaidAmount()));
    }

    @Override
    protected void addEntity(EditOrderPopupView view) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void editEntity(EditOrderPopupView view, Order order) throws Exception {
        if (order.getTable() == null) {
            throw new Exception("Hết bàn");
        }
        if (order.getDiscount() < 0 || order.getDiscount() > 100) {
            throw new Exception("Discount phải nằm trong khoảng 0-100");
        }
        if (order.getEmployee() == null) {
            throw new Exception("Chọn nhân viên");
        }
        if (order.getType() == OrderType.LOCAL) {
            order.getTable().setStatus(TableStatus.SERVING);
        } else {
            order.getTable().setStatus(TableStatus.FREE);
        }
        if (order.getPaidAmount() == order.getFinalAmount()) {
            order.setStatus(OrderStatus.PAID);
        }
        for (OrderItem orderItem : orderItemController.getOrderItems()) {
            if (orderItem.getQuantity() <= 0) {
                orderItemDao.delete(orderItem);
            } else {
                orderItemDao.save(orderItem);
            }
        }
        if (order.getFinalAmount() <= 0 || order.getFinalAmount() > order.getPaidAmount()) { // Chưa thanh toán 
            order.setStatus(OrderStatus.UNPAID);
            order.setPayDate(null);
        } else if (order.getStatus() == OrderStatus.UNPAID || order.getPayDate() == null) {
            // Thanh toán
            order.setStatus(OrderStatus.PAID);
            order.setPayDate(new Timestamp(System.currentTimeMillis()));
            order.getTable().setStatus(TableStatus.FREE); // Trả bàn
        }
        order.setTotalAmount(orderItemController.getTotalAmount());
        orderDao.update(order);
        tableDao.update(order.getTable());
        if (order.getType() != OrderType.ONLINE) {
            shipmentDao.deleteById(order.getOrderId()); // Xóa đơn ship
        }
    }
}
