/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin;

import controllers.popup.CustomerPopupController;
import controllers.ManagerController;
import controllers.ManagerController;
import controllers.popup.CustomerPopupController;
import dao.CustomerDao;
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
    
    CustomerDao customerDao = new CustomerDao();
//    ShipmentDao shipmentDao = new ShipmentDao();
    CustomerPopupController popupController = new CustomerPopupController();
    CustomerPopupController customerPopupController = new CustomerPopupController();
    
    public CustomerManagerController(){ 
        super();
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setPopupController(CustomerPopupController popupController) {
        this.popupController = popupController;
    }

    public void setCustomerPopupController(CustomerPopupController customerPopupController) {
        this.customerPopupController = customerPopupController;
    }
    
    
    
    @Override
    public void actionAdd() {
        customerPopupController.add(new CustomerPopupView(), this::updateData, view::showError);
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa khách hàng", ERROR_MESSAGE) != YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedIds.length; i++) {
                int id = selectedIds[i];
//                shipmentDao.deleteByIdCustomer(id);
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
//                popupController.edit(this, new CustomerPopupView(), c);
                popupController.edit(new CustomerPopupView(), c, this::updateData, view::showError);
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
