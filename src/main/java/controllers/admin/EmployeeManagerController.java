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
import models.Employee;
import utils.EmployeePermission;
import views.popup.EmployeePopupView;

/**
 *
 * @author P51
 */
public class EmployeeManagerController extends ManagerController{
    private EmployeeDao employeeDao;
    private EmployeePopupController popupController;
    
    public EmployeeManagerController() {
        super();
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
                view.showError(e);
            }
        };

        popupController.add(new EmployeePopupView(), successCallback, errorCallback);
    }

    @Override
    public void actionSearch() {
        try {
            ArrayList<Employee> employees = employeeDao.searchByKey(view.getComboSearchField().getSelectedItem().toString(), String.valueOf(view.getSearchTxt().getText()));
            view.setTableData(employees);
        } catch (Exception e) {
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionDelete() {
        int selectedIds[] = view.getSelectedIds();
        try {
            if (JOptionPane.showConfirmDialog(null, "Xác nhận xoá hàng loạt?", "Xoá nhân viên", JOptionPane.ERROR_MESSAGE) != JOptionPane.YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedIds.length; i++) {
                employeeDao.deleteById(selectedIds[i]);
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
                throw new Exception("Chọn nhân viên cần chỉnh sửa");
            }
            Employee e = employeeDao.getById(selectedId);
            if (e == null) {
                throw  new Exception("Nhân viên bạn chọn không hợp lệ");
            }
            if (e.getPermission() == EmployeePermission.MANAGER) {
                int value = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn chỉnh sửa admin?");
                if (value != JOptionPane.YES_OPTION) {
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
                    view.showError(e);
                }
            };
            popupController.edit(new EmployeePopupView(), e, successCallback, errorCallback);
        } catch (Exception e) {
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Employee> employees = employeeDao.getAll();
            view.setTableData(employees);
        } catch (Exception e) {
            view.showError(e);
        }
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
