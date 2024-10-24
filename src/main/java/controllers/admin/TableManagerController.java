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
import views.admin.TableManagerView;
import views.popup.TablePopupView;

/**
 *
 * @author P51
 */
public class TableManagerController extends ManagerController{

    private final TableDao tableDao;
    private final TablePopupController popupController;
    
    public TableManagerController() {
//        super();
        tableDao = new TableDao();
        popupController = new TablePopupController();
    }

    public TableManagerController(TableDao tableDao, TablePopupController popupController) {
        this.tableDao = tableDao;
        this.popupController = popupController;
    }

    

    public void setView(TableManagerView view) {
        super.setView(view);
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
        popupController.add(new TablePopupView(), successCallback, errorCallback);
    }

    @Override
    public void actionSearch() {
        try {
            String keyChoice = getView().getComboSearchField().getSelectedItem().toString();
            String convertKeyChoice = new String();
            switch (keyChoice) {
                case "Mã bàn":
                    convertKeyChoice = "TableId";
                    break;
                case "Tên bàn":
                    convertKeyChoice = "Name";
                    break;
                default:
                    throw new AssertionError();
            }
            ArrayList<Table> tables = tableDao.searchByKey(convertKeyChoice, getView().getSearchTxt().getText());
            getView().setTableData(tables);
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
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa bàn?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn cần xoá!");
            }
            for (int id : selectedIds) {
                tableDao.deleteById(id);
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
                throw new Exception("Chọn bàn cần chỉnh sửa");
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
                        getView().showError(e);
                    }
                };
                popupController.edit(new TablePopupView(), t, successCallback, errorCallback);
            }
        } catch (Exception e) {
            getView().showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Table> tables = tableDao.getAll();
            getView().setTableData(tables);
        } catch (Exception e) {
            getView().showError(e);
        }
    }
    
}
