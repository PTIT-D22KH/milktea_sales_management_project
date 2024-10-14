/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin;

import controllers.ManagerController;
import controllers.popup.ErrorCallback;
import controllers.popup.ShipmentPopupControler;
import controllers.popup.SuccessCallback;
import dao.ShipmentDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.Shipment;
import views.popup.ShipmentPopupView;

/**
 *
 * @author P51
 */
public class ShipmentManagerController extends ManagerController{
    private ShipmentDao shipmentDao;
    private ShipmentPopupControler popupController;
    
    public ShipmentManagerController() {
        super();
        this.shipmentDao = new ShipmentDao();
        this.popupController = new ShipmentPopupControler();
    }

    public ShipmentManagerController(ShipmentDao shipmentDao, ShipmentPopupControler popupController) {
        super();
        this.shipmentDao = shipmentDao;
        this.popupController = popupController;
    }
    
    @Override
    public void actionAdd() {
       view.showMessage("Vui lòng thao tác ở giao diện chỉnh sửa hóa đơn!");
    }

    @Override
    public void actionSearch() {
        try {
            ArrayList<Shipment> shipments = shipmentDao.searchByKey(view.getComboSearchField().getSelectedItem().toString(), String.valueOf(view.getSearchTxt().getText()));
            view.setTableData(shipments);
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionDelete() {
        int selectedIds[] = view.getSelectedIds();
        try {
            if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedIds.length; i++) {
                shipmentDao.deleteById(selectedIds[i]);
                updateData();
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionEdit() {
        try {
            int selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw  new Exception("Chọn đơn ship cần chỉnh sửa!");
            } else {
                Shipment e = shipmentDao.getById(selectedId);
                if (e == null) {
                    throw  new Exception("Đơn ship bạn chọn không hợp lê");
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
                popupController.edit(new ShipmentPopupView(), e.getOrderId(), successCallback, errorCallback);
            }
                   
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Shipment> shipments = shipmentDao.getAll();
            view.setTableData(shipments);
            
        } catch (Exception e) {
            view.showError(e);
        }
    }
    
}
