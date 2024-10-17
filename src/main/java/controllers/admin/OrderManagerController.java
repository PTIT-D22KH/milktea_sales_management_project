/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin;

import controllers.ManagerController;
import controllers.popup.AddOrderPopupController;
import controllers.popup.EditOrderPopupController;
import controllers.popup.ErrorCallback;
import controllers.popup.OrderPopupController;
import controllers.popup.SuccessCallback;
import dao.OrderDao;
import dao.ShipmentDao;
import dao.TableDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.Employee;
import models.Order;
import models.Table;
import utils.EmployeePermission;
import utils.SessionManager;
import utils.TableStatus;
import views.admin.EmployeeManagerView;
import views.popup.AddOrderPopupView;
import views.popup.EditOrderPopupView;

/**
 *
 * @author P51
 */
public class OrderManagerController extends ManagerController{
    private OrderDao orderDao;
    private TableDao tableDao;
    private ShipmentDao shipmentDao;
    private AddOrderPopupController addOrderPopupController;
    private EditOrderPopupController editOrderPopupController;
    private Employee sessionEmployee;
    public OrderManagerController() {
        super();
        orderDao = new OrderDao();
        tableDao = new TableDao();
        shipmentDao = new ShipmentDao();
        addOrderPopupController = new AddOrderPopupController();
        editOrderPopupController = new EditOrderPopupController();
        sessionEmployee = SessionManager.getSession().getEmployee();
                
    }

    public OrderManagerController(OrderDao orderDao, TableDao tableDao, ShipmentDao shipmentDao, AddOrderPopupController addOrderPopupController, EditOrderPopupController editOrderPopupController) {
        super();
        this.orderDao = orderDao;
        this.tableDao = tableDao;
        this.shipmentDao = shipmentDao;
        this.addOrderPopupController = addOrderPopupController;
        this.editOrderPopupController = editOrderPopupController;
        sessionEmployee = SessionManager.getSession().getEmployee();

    }
    
    
    public void setView(EmployeeManagerView view) {
        super.setView(view);
    }
    
    @Override
    public void actionAdd() {
        // Declare the success callback
        SuccessCallback successCallback = new SuccessCallback() {
            @Override
            public void onSuccess() {
                updateData();
            }
        };

        // Declare the error callback
        ErrorCallback errorCallback = new ErrorCallback() {
            @Override
            public void onError(Exception ex) {
                view.showError(ex);
            }
        };
        addOrderPopupController.add(new AddOrderPopupView(), successCallback, errorCallback);
    }

    @Override
    public void actionSearch() {
        try {
            Employee employee = SessionManager.getSession().getEmployee();
            ArrayList<Order> orders;
            String searchField = view.getComboSearchField().getSelectedItem().toString(), txtSearch = view.getSearchTxt().getText();
            // quản lý có thể search được toàn bộ order, nhân viên chỉ search được order của mình
            if (employee.getPermission() == EmployeePermission.MANAGER) {
                orders = orderDao.searchByKey(searchField, txtSearch);
            } else {
                orders = orderDao.searchByKey(employee.getEmployeeId(), searchField, txtSearch);
            }
            view.setTableData(orders);
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionDelete() {
        int selectedIds[] = view.getSelectedIds();
        try {
            if (JOptionPane.showConfirmDialog(null, "Không thể khổi phục\nXác nhận xóa hàng loạt?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedIds.length; i++) {
                int id = selectedIds[i];
                Order o = orderDao.getById(id);
                Table t = o.getTable();
                t.setStatus(TableStatus.FREE);
                shipmentDao.deleteById(id);
                orderDao.deleteItems(id); // Xóa item trong order 
                tableDao.update(t); // Trả bàn
                orderDao.deleteById(id); // Xóa order
            }
            updateData();
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionEdit() {
        try {
            int selectedID = view.getSelectedId();
            if (selectedID < 0) {
                throw new Exception("Chọn hoá đơn cần chỉnh sửa!");
            }
            Order order = orderDao.getById(selectedID);
            if (order == null) {
                throw new Exception("Hoá đơn bạn chọn không hợp lệ");
            }
            // Declare the success callback
            SuccessCallback successCallback = new SuccessCallback() {
                @Override
                public void onSuccess() {
                    updateData();
                }
            };

            // Declare the error callback
            ErrorCallback errorCallback = new ErrorCallback() {
                @Override
                public void onError(Exception ex) {
                    view.showError(ex);
                }
            };
            editOrderPopupController.edit(new EditOrderPopupView(), order, successCallback, errorCallback);
            
        } catch (Exception e) {
            view.showError(e); 
        }
    }

    @Override
    public void updateData() {
        try {
            Employee employee = SessionManager.getSession().getEmployee();
            ArrayList<Order> orders;
            // quản lý có thể search được toàn bộ order, nhân viên chỉ search được order của mình
            if (employee.getPermission() == EmployeePermission.MANAGER) {
                orders = orderDao.getAll();
            } else {
                orders = orderDao.getAll(employee.getEmployeeId());
            }
            view.setTableData(orders);
        } catch (Exception e) {
            view.showError(e);
        }
    }
    
    
}
