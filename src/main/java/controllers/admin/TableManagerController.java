/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin;

import controllers.ManagerController;
import controllers.popup.ErrorCallback;
import controllers.popup.SuccessCallback;
import controllers.popup.TablePopupController;
import dao.TableDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.Table;
import views.admin.ManagerPaneView;
import views.admin.TableManagerView;
import views.popup.TablePopupView;

/**
 *
 * @author P51
 */
public class TableManagerController extends ManagerController{

    private TableDao tableDao;
    private TablePopupController popupController;
    
    public TableManagerController() {
        super();
        tableDao = new TableDao();
        popupController = new TablePopupController();
    }

    public TableManagerController(TableDao tableDao, TablePopupController popupController) {
        this.tableDao = tableDao;
        this.popupController = popupController;
    }

    

    public void setView(TableManagerView view) {
        super.setView(view); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
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
        popupController.add(new TablePopupView(), successCallback, errorCallback);
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionSearch() {
        try {
            ArrayList<Table> tables = tableDao.searchByKey(view.getComboSearchField().getSelectedItem().toString(), view.getSearchTxt().getText());
            view.setTableData(tables);
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
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa bàn?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn cần xoá!");
            }
            for (int i = 0; i < selectedIds.length; i++) {
                tableDao.deleteById(selectedIds[i]);
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
                throw new Exception("Chọn bàn cần edit");
            } else {
                Table t = tableDao.getById(selectedId);
                if (t == null) {
                    throw new Exception("Bàn bạn chọn không hợp lệ");
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
                popupController.edit(new TablePopupView(), t, successCallback, errorCallback);
            }
        } catch (Exception e) {
            view.showError(e);
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Table> tables = tableDao.getAll();
            view.setTableData(tables);
        } catch (Exception e) {
            view.showError(e);
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
