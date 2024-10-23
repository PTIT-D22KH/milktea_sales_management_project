/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin;

import controllers.ManagerController;
import controllers.popup.EmployeePopupController;
import controllers.popup.ErrorCallback;
import controllers.popup.SuccessCallback;
import dao.EmployeeDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.Employee;
import utils.EmployeePermission;
import views.popup.EmployeePopupView;

/**
 *
 * @author P51
 */

/**
 * Controller for managing employee data.
 * Adheres to SRP by focusing only on employee management.
 */
public class EmployeeManagerController extends ManagerController{
    private final EmployeeDao employeeDao;
    private final EmployeePopupController popupController;
    
    public EmployeeManagerController() {
        employeeDao = new EmployeeDao();
        popupController = new EmployeePopupController();
    }

    public EmployeeManagerController(EmployeeDao employeeDao, EmployeePopupController popupController) {
        this.employeeDao = employeeDao;
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

        popupController.add(new EmployeePopupView(), successCallback, errorCallback);
    }

    @Override
    public void actionSearch() {
        try {
            String keyChoice = getView().getComboSearchField().getSelectedItem().toString();
            String convertKeyChoice = new String();
            switch (keyChoice) {
                case "Mã nhân viên":
                    convertKeyChoice = "EmployeeId";
                    break;
                case "SĐT":
                    convertKeyChoice = "PhoneNumber";
                    break;
                case "Tên nhân viên":
                    convertKeyChoice = "Name";
                    break;
                default:
                    throw new AssertionError();
            }
            ArrayList<Employee> employees = employeeDao.searchByKey(convertKeyChoice, String.valueOf(getView().getSearchTxt().getText()));
            getView().setTableData(employees);
        } catch (Exception e) {
            getView().showError(e);
        }
    }

    @Override
    public void actionDelete() {
        int selectedIds[] = getView().getSelectedIds();
        try {
            if (selectedIds.length > 1) {
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa nhân viên", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else if (selectedIds.length == 1) {
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa nhân viên?", "Xóa nhân viên", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xoá!");
            }
            for (int id : selectedIds) {
                employeeDao.deleteById(id);
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
                throw new Exception("Chọn nhân viên cần chỉnh sửa");
            }
            Employee e = employeeDao.getById(selectedId);
            if (e == null) {
                throw new Exception("Nhân viên bạn chọn không hợp lệ");
            }
            if (e.getPermission() == EmployeePermission.MANAGER) {
                int value = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn chỉnh sửa admin?");
                if (value != YES_OPTION) {
                    return;
                }
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
            popupController.edit(new EmployeePopupView(), e, successCallback, errorCallback);
        } catch (Exception e) {
            getView().showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Employee> employees = employeeDao.getAll();
            getView().setTableData(employees);
        } catch (Exception e) {
            getView().showError(e);
        }
    }
}
