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
import java.sql.SQLException;
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
    private final ShipmentDao shipmentDao;
    private final ShipmentPopupControler popupController;
    
    public ShipmentManagerController() {
//        super();
        this.shipmentDao = new ShipmentDao();
        this.popupController = new ShipmentPopupControler();
    }

    public ShipmentManagerController(ShipmentDao shipmentDao, ShipmentPopupControler popupController) {
//        super();
        this.shipmentDao = shipmentDao;
        this.popupController = popupController;
    }
    
    @Override
    public void actionAdd() {
        getView().showMessage("Vui lòng thao tác ở giao diện chỉnh sửa hóa đơn!");
    }

    @Override
    public void actionSearch() {
        try {
            String keyChoice = getView().getComboSearchField().getSelectedItem().toString();
            String convertKeyChoice = new String();
            switch (keyChoice) {
                case "Mã đơn":
                    convertKeyChoice = "OrderId";
                    break;
                default:
                    throw new AssertionError();
            }
            ArrayList<Shipment> shipments = shipmentDao.searchByKey(convertKeyChoice, String.valueOf(getView().getSearchTxt().getText()));
            getView().setTableData(shipments);
        } catch (SQLException e) {
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
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa đơn ship?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn đơn ship cần xoá!");
            }
            for (int id : selectedIds) {
                shipmentDao.deleteById(id);
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
                throw new Exception("Chọn đơn ship cần chỉnh sửa!");
            } else {
                Shipment e = shipmentDao.getById(selectedId);
                if (e == null) {
                    throw new Exception("Đơn ship bạn chọn không hợp lệ");
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
                        getView().showError(ex);
                    }
                };
                popupController.edit(new ShipmentPopupView(), e.getOrderId(), successCallback, errorCallback);
            }

        } catch (Exception e) {
            getView().showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Shipment> shipments = shipmentDao.getAll();
            getView().setTableData(shipments);

        } catch (SQLException e) {
            getView().showError(e);
        }
    }
    
}
