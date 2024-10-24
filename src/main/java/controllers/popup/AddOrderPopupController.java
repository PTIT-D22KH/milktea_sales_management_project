/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;
import dao.EmployeeDao;
import dao.OrderDao;
import dao.ShipmentDao;
import dao.TableDao;
import java.sql.SQLException;
import models.Employee;
import models.Order;
import models.Table;
import utils.OrderStatus;
import utils.OrderType;
import utils.SessionManager;
import utils.TableStatus;
import views.popup.AddOrderPopupView;
/**
 *
 * @author P51
 */
public class AddOrderPopupController extends PopupController<AddOrderPopupView, Order> {
    private final OrderDao orderDao;
//    private final EmployeeDao employeeDao;
//    private final ShipmentDao shipmentDao;
    private final TableDao tableDao;
//    private final DecimalFormat formatter;

    public AddOrderPopupController() {
        this.orderDao = new OrderDao();
//        this.employeeDao = new EmployeeDao();
//        this.shipmentDao = new ShipmentDao();
        this.tableDao = new TableDao();
//        this.formatter = new DecimalFormat("###,###,###");
    }

    public AddOrderPopupController(OrderDao orderDao, EmployeeDao employeeDao, ShipmentDao shipmentDao, TableDao tableDao) {
        this.orderDao = orderDao;
//        this.employeeDao = employeeDao;
//        this.shipmentDao = shipmentDao;
        this.tableDao = tableDao;
//        this.formatter = new DecimalFormat("###,###,###");
    }

    @Override
    protected void addEntity(AddOrderPopupView view) throws Exception {
        Order order = new Order();
        Table table = (Table) view.getTbComboBoxModel().getSelectedItem();
        OrderType type = OrderType.getByName(view.getCboType().getSelectedItem().toString());
        Employee employee = SessionManager.getSession().getEmployee();
        int discount = (int) view.getSpnDiscount().getValue();
        if (table == null) {
            throw new Exception("Hết bàn");
        }
        if (discount < 0 || discount > 100) {
            throw new Exception("Discount phải nằm trong khoảng 0-100");
        }
        if (employee == null) {
            throw new Exception("Bạn chưa đăng nhập");
        }
        if (type == OrderType.LOCAL) {
            if (tableDao.getById(table.getTableId()).getStatus() != TableStatus.FREE) {
                throw new Exception("Bàn này đang phục vụ");
            }
            table.setStatus(TableStatus.SERVING);
        }

        order.setEmployee(employee);
        order.setTable(table);
        order.setStatus(OrderStatus.UNPAID);
        order.setType(type);
        order.setDiscount(discount);
        order.setCustomerId(1);
        orderDao.save(order);
        tableDao.update(table);
    }

    @Override
    public void add(AddOrderPopupView view, SuccessCallback sc, ErrorCallback ec) {
        super.add(view, sc, ec);
        try {
            for (Table table : tableDao.getAll()) {
                if (table.getStatus() == TableStatus.FREE) {
                    view.getTbComboBoxModel().addElement(table);
                }
            }
            for (OrderType ot : OrderType.values()) {
                view.getCboType().addItem(ot.getName());
            }
        } catch (SQLException exception) {
            view.dispose();
            ec.onError(exception);
        }
    }

    @Override
    protected void editEntity(AddOrderPopupView view, Order model) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
