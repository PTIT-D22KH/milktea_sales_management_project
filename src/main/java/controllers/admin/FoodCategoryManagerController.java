package controllers.admin;

import controllers.ManagerController;
import controllers.popup.ErrorCallback;
import controllers.popup.FoodCategoryPopupController;
import controllers.popup.SuccessCallback;
import dao.FoodCategoryDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.FoodCategory;
import views.popup.FoodCategoryPopupView;

public class FoodCategoryManagerController extends ManagerController {
    
    private FoodCategoryDao foodCategoryDao;
    private FoodCategoryPopupController foodCategoryPopupController;
    
    public FoodCategoryManagerController() {
        super();
        this.foodCategoryDao = new FoodCategoryDao();
        this.foodCategoryPopupController = new FoodCategoryPopupController();
    }   
    
    public FoodCategoryManagerController(FoodCategoryDao foodCategoryDao, FoodCategoryPopupController foodCategoryPopupController){
        super();
        this.foodCategoryDao = foodCategoryDao;
        this.foodCategoryPopupController = foodCategoryPopupController;
    }
    
    @Override
    public void actionAdd(){
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
        foodCategoryPopupController.add(new FoodCategoryPopupView(), successCallback, errorCallback);
    }
    
    @Override
    public void actionSearch(){
        try {
            ArrayList<FoodCategory> foodCategories = foodCategoryDao.searchByKey(view.getComboSearchField().getSelectedItem().toString(), String.valueOf(view.getSearchTxt().getText()));
            view.setTableData(foodCategories);
        } catch (Exception ex) {
//            view.showError(ex);
        }
    }
    
    @Override
    public void actionDelete(){
        int selectedIds[] = view.getSelectedIds();
        try {
            if (selectedIds.length > 1) {
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else if (selectedIds.length == 1){
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa loại món?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn loại món cần xoá!");
            }
            for (int i = 0; i < selectedIds.length; i++) {
                foodCategoryDao.deleteById(selectedIds[i]);
            }
            updateData();
        } catch (Exception e) {
            view.showError(e);
        }
    }
    
    @Override
    public void actionEdit(){
        try {
            int selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn loại món cần chỉnh sửa");
            } else {
                FoodCategory foodCategory = foodCategoryDao.getById(selectedId);
                if (foodCategory == null) {
                    throw new Exception("Loại món bạn chọn không hợp lệ");
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
                
                foodCategoryPopupController.edit(new FoodCategoryPopupView(), foodCategory, successCallback, errorCallback);
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void updateData(){
        try {
            ArrayList<FoodCategory> foodCategories = foodCategoryDao.getAll();
            view.setTableData(foodCategories);
        } catch (Exception e) {
            view.showError(e);
        }
    }
}
