/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;
import controllers.OrderPrintController;
import controllers.popup.order.OrderItemController;
import controllers.popup.order.FoodItemController;
import dao.EmployeeDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.ShipmentDao;
import dao.TableDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import models.Customer;
import models.Employee;
import models.Order;
import models.OrderItem;
import models.Shipment;
import models.Table;
import utils.OrderStatus;
import utils.OrderType;
import utils.SessionManager;
import utils.ShipmentStatus;
import utils.TableStatus;
import views.popup.EditOrderPopupView;
import views.popup.SelectCustomerPopupView;
import views.popup.ShipmentPopupView;
import views.popup.ToppingPopupView;
/**
 *
 * @author P51
 */
public class EditOrderPopupController extends PopupController<EditOrderPopupView, Order> {
    private final OrderDao orderDao;
    private final ShipmentDao shipmentDao;
    private final TableDao tableDao;
    private final OrderItemDao orderItemDao;
    private final FoodItemController foodItemController;
    private final OrderItemController orderItemController;    
    private final ToppingPopupController toppingPopupController;
    private final ShipmentPopupControler shipmentPopupControler;
    private final OrderPrintController orderPrintController;
    private final DecimalFormat formatter;

    public EditOrderPopupController() {
        this.orderDao = new OrderDao();
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
        int rebate = order.getPaidAmount()- order.getFinalAmount();
        if (rebate < 0) {
            rebate = 0;
        }
        view.getRebateAmountLabel().setText(formatter.format(rebate));
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
            System.out.println("Table " + order.getTable() + " of order" + order.getOrderId() + ", status: " + order.getTable().getStatus());
            order.getTable().setStatus(TableStatus.FREE); // Trả bàn
            System.out.println("Table " + order.getTable() + " of order" + order.getOrderId() + ", status: " + order.getTable().getStatus());
        }
        order.setTotalAmount(orderItemController.getTotalAmount());
        orderDao.update(order);
        tableDao.update(order.getTable());
        if (order.getType() != OrderType.ONLINE) {
            shipmentDao.deleteById(order.getOrderId()); // Xóa đơn ship
        }
    }
    
    @Override
    public void edit(EditOrderPopupView view, Order order, SuccessCallback sc, ErrorCallback ec) {
        if (getPreviousView() != null && getPreviousView().isDisplayable()) {
            getPreviousView().requestFocus();
            return;
        }
        setPreviousView(view);
        Employee currentLogin = SessionManager.getSession().getEmployee();
        if (order.getEmployee() == null) {
            order.setEmployee(currentLogin);
        }
        
        if (!order.getEmployee().equals(currentLogin) && order.getEmployee().getPermission().compare(currentLogin.getPermission()) > 0) {
            ec.onError(new Exception("Bạn không có quyền sửa hóa đơn này"));
            view.dispose();
            return;
        }
        view.setVisible(true);
        
        if (order.getCustomer() != null) {
            view.getCustomerNameLabel().setText(order.getCustomer().getName());
        }
        
        
        view.getBtnSelectCustomer().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectCustomerPopupController selectCustomerPopupController = new SelectCustomerPopupController();
                selectCustomerPopupController.select(new SelectCustomerPopupView(), new SelectCustomerPopupController.Callback<Customer>(){
                    @Override
                    public void run(Customer customer) {
                        order.setCustomer(customer);
                        view.getCustomerNameLabel().setText(customer.getName());
                    }
                    
                });
                
            }
            
        });
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        orderItemController.setOrderItemPanel(view.getOrderItemPanel());
        orderItemController.setOrderId(order.getOrderId());
        orderItemController.setOnQuantityChange(() -> {
            updateAmount(view, order);
        });
        foodItemController.setFoodCategoryPanel(view.getFoodCategoryPanel());
        foodItemController.setFoodItemPanel(view.getFoodItemPanel());

        if (order.getEmployee() != null) {
            view.getEmployeeNameLabel().setText(order.getEmployee().getName());
        }
        view.getOrderIdLabel().setText(order.getOrderId()+ "");
        try {
            for (Table table : tableDao.getAll()) { // Hiển thị danh sách bàn
                if (table.getStatus() == TableStatus.FREE || table.getTableId()== order.getTable().getTableId()) {
                    view.getTbComboBoxModel().addElement(table);
                }
            }
            for (OrderType ot : OrderType.values()) { // Hiển thị loại hóa đơn
                view.getTypeCboBox().addItem(ot.getName());
            }
            orderItemController.setOrderItems(orderItemDao.getByOrderId(order.getOrderId()));
            foodItemController.renderCategory(foodItem -> {//Hiển thị danh sách món ăn
                toppingPopupController.add(new ToppingPopupView(), foodItem, orderItem -> {
                    try {
                        orderItemController.addOrderItem(orderItem);// Thêm vào danh sách order
                    } catch (SQLException ex) {
                        ec.onError(ex);
                    }
                    updateAmount(view, order);
                });
            });
            view.getTbComboBoxModel().setSelectedItem(order.getTable());
            view.getDiscountSpinner().setValue(order.getDiscount());
            view.getTypeCboBox().setSelectedItem(order.getType().getName());
            view.getLbDiscount().setText(order.getDiscount() + "");

        } catch (Exception e) {
            view.dispose();
            ec.onError(e);
            return;
        }
        updateAmount(view, order);
        view.getBtnOK().setText("Cập nhật");
        view.getTypeCboBox().addActionListener(evt -> {
            order.setType(OrderType.getByName(view.getTypeCboBox().getSelectedItem().toString()));
        });
        view.getDiscountSpinner().addChangeListener(evt -> { // Thay giá trị
            order.setDiscount((int) view.getDiscountSpinner().getValue());
            updateAmount(view, order);
        });
        view.getTableComboBox().addActionListener(evt -> { // Thay bàn
            try {
                Table nTable = (Table) view.getTbComboBoxModel().getSelectedItem(),
                        cTable = order.getTable();
                if (nTable == null || (cTable != null && nTable.getTableId()== cTable.getTableId())) {
                    return;
                }
                cTable.setStatus(TableStatus.FREE);
                nTable.setStatus(TableStatus.SERVING);
                order.setTable(nTable);
                tableDao.update(cTable);
                tableDao.update(nTable);
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
        view.getBtnOK().addActionListener(evt -> {
            try {
                editEntity(view, order);
//                view.dispose();
                view.showMessage("Sửa hóa đơn thành công!");
                updateAmount(view, order);
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

        view.getPaidButton().addActionListener(evt -> {
            try {
                String rawInput = JOptionPane.showInputDialog(null, "Nhập số tiền thanh toán!", order.getPaidAmount());
                if (rawInput == null) {
                    return;
                }
                int paidAmount = Integer.parseInt(rawInput);
                if (order.getFinalAmount() > paidAmount) {
                    JOptionPane.showMessageDialog(null, "Bạn còn phải thanh toán " + formatter.format(order.getFinalAmount() - paidAmount) + " VND");
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn đã thanh toán xong");
                }
                order.setPaidAmount(paidAmount);
                updateAmount(view, order);
            } catch (Exception e) {
                ec.onError(e);
            }
        });
        view.getShipManagerButton().addActionListener(evt -> {
            if (order.getType() != OrderType.ONLINE) {
                view.showError("Bạn chỉ có thể ship đơn online");
                return;
            }
            shipmentPopupControler.add(new ShipmentPopupView(), order, () -> view.showMessage("Tạo / sửa đơn ship thành công!"), view::showError);
        });
        view.getPrintOrderButton().addActionListener(evt -> {
            try {
                orderPrintController.print(order.getOrderId());
            } catch (Exception e) {
                view.showError("Không thể in hóa đơn");
            }
        });
        view.getCancelOrderButton().addActionListener(evt -> {
            try {
                int value = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn hủy hóa đơn?");
                if (value != JOptionPane.YES_OPTION) {
                    return;
                }
                order.setStatus(OrderStatus.CANCEL);
                Shipment shipment = shipmentDao.getById(order.getOrderId());
                if (shipment != null) {
                    shipment.setStatus(ShipmentStatus.CANCELLED);
                    shipmentDao.update(shipment); // Hủy đơn ship
                }
                order.getTable().setStatus(TableStatus.FREE);
                orderDao.update(order);
                tableDao.update(order.getTable());
                view.dispose();
                sc.onSuccess();
            } catch (Exception e) {
                view.showError(e);
            }
        });
    }
}
