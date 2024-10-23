/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin;

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
    
    private final CustomerDao customerDao;
    private final ShipmentDao shipmentDao;
    private final CustomerPopupController popupController;
//    private final CustomerPopupController customerPopupController;
    
    public CustomerManagerController() {
        this.customerDao = new CustomerDao();
        this.shipmentDao = new ShipmentDao();
        this.popupController = new CustomerPopupController();
    }
    public CustomerManagerController(CustomerDao customerDao, ShipmentDao shipmentDao, CustomerPopupController popupController) {
        this.customerDao = customerDao;
        this.shipmentDao = shipmentDao;
        this.popupController = popupController;
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
                getView().showError(e);
            }
        };

        popupController.add(new CustomerPopupView(), successCallback, errorCallback);
    }

    @Override
    public void actionSearch() {
        try {
            String keyChoice = getView().getComboSearchField().getSelectedItem().toString();
            String convertKeyChoice = new String();
            switch (keyChoice) {
                case "Mã khách hàng":
                    convertKeyChoice = "CustomerId";
                    break;
                case "SĐT":
                    convertKeyChoice = "PhoneNumber";
                    break;
                case "Tên khách hàng":
                    convertKeyChoice = "Name";
                    break;
                case "Địa chỉ":
                    convertKeyChoice = "Address";
                    break;
                default:
                    throw new AssertionError();
            }
            ArrayList<Customer> customers = customerDao.searchByKey(convertKeyChoice, String.valueOf(getView().getSearchTxt().getText()));
            getView().setTableData(customers);
        } catch (Exception e) {
            getView().showError(e);
        }
    }

    @Override
    public void actionDelete() {
        int selectedIds[] = getView().getSelectedIds();
        try {
            if (selectedIds.length > 1) {
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else if (selectedIds.length == 1) {
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa khách hàng?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xoá!");
            }
            for (int id : selectedIds) {
                shipmentDao.deleteByCustomerId(id);
                customerDao.deleteById(id);
                updateData();
            }
        } catch (Exception e) {
            getView().showError(e);
        }
    }

    @Override
    public void actionEdit() {
        try {
            int selectedId = getView().getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn khách hàng cần chỉnh sửa!");
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
                        getView().showError(e);
                    }
                };
                popupController.edit(new CustomerPopupView(), c, successCallback, errorCallback);
            }
        } catch (Exception e) {
            getView().showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Customer> customers = customerDao.getAll();
            getView().setTableData(customers);
        } catch (Exception e) {
            getView().showError(e);
        }
    }
    
}
