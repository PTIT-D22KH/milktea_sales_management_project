/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin;

import controllers.popup.CustomerPopupController;
import controllers.ManagerController;
import controllers.ManagerController;
import controllers.popup.CustomerPopupController;
import controllers.popup.ErrorCallback;
import controllers.popup.SuccessCallback;
import dao.CustomerDao;
import dao.ShipmentDao;
//import dao.ShipmentDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.Customer;
import views.popup.CustomerPopupView;
//import models.Customer;

/**
 *
 * @author P51
 */
public class CustomerManagerController extends ManagerController{
    
    private CustomerDao customerDao;
    private ShipmentDao shipmentDao;
    private CustomerPopupController popupController;
    private CustomerPopupController customerPopupController;
    
    public CustomerManagerController(){ 
        
        super();
        customerDao = new CustomerDao();
        shipmentDao = new ShipmentDao();
        popupController = new CustomerPopupController();
        customerPopupController = new CustomerPopupController();
    }

    //Test only
    public CustomerManagerController(CustomerDao customerDao, ShipmentDao shipmentDao, CustomerPopupController popupController, CustomerPopupController customerPopupController) {
        this.customerDao = customerDao;
        this.shipmentDao = shipmentDao;
        this.popupController = popupController;
        this.customerPopupController = customerPopupController;
    }

    
    @Override
    public void actionAdd() {
        
        SuccessCallback successCallback = new SuccessCallback() {
            @Override
            public void onSuccess() {
                updateData();
            }
        };

        ErrorCallback errorCallback = new ErrorCallback() {
            @Override
            public void onError(Exception e) {
                view.showError(e);
            }
        };

        customerPopupController.add(new CustomerPopupView(), successCallback, errorCallback);
    }

    @Override
    public void actionSearch() {
        try {
            ArrayList<Customer> customers = customerDao.searchByKey(view.getComboSearchField().getSelectedItem().toString(), String.valueOf(view.getSearchTxt().getText()));
            view.setTableData(customers);
        } catch (Exception e) {
            view.showError(e);
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionDelete() {
        int selectedIds[] = view.getSelectedIds();
        try {
            if (selectedIds.length > 1) {
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else if (selectedIds.length == 1){
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa khách hàng?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xoá!");
            }
            for (int i = 0; i < selectedIds.length; i++) {
                int id = selectedIds[i];
                shipmentDao.deleteByIdCustomer(id);
                customerDao.deleteById(id);
                updateData();
            }
        } catch (Exception e) {
            view.showError(e);
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionEdit() {
        try {
            int selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn khách hàng cần edit");
            } else {
                Customer c = customerDao.getById(selectedId);
                if (c == null) {
                    throw new Exception("Khách hàng bạn chọn không hợp lệ");
                }
                SuccessCallback successCallback = new SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        updateData();
                    }
                };

                ErrorCallback errorCallback = new ErrorCallback() {
                    @Override
                    public void onError(Exception e) {
                        view.showError(e);
                    }
                };
                popupController.edit(new CustomerPopupView(), c, successCallback, errorCallback);
            }
        } catch (Exception e) {
            view.showError(e);
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Customer> customers = customerDao.getAll();
            view.setTableData(customers);
        } catch (Exception e){
            view.showError(e);
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
