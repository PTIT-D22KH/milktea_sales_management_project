package controllers.admin;

import controllers.ManagerController;
import controllers.popup.ErrorCallback;
import controllers.popup.FoodItemPopupController;
import controllers.popup.SuccessCallback;
import dao.FoodItemDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.FoodItem;
import views.admin.FoodItemManagerView;
import views.popup.FoodItemPopupView;

public class FoodItemManagerController extends ManagerController {
    
    private FoodItemDao foodItemDao;
    private FoodItemPopupController popupController;
    
    public FoodItemManagerController(){
        super();
        foodItemDao = new FoodItemDao();
        popupController = new FoodItemPopupController();
    }
    
    public void setView(FoodItemManagerView view) {
        super.setView(view);
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
        
        popupController.add(new FoodItemPopupView(), successCallback, errorCallback);
    }
    
    @Override
    public void actionSearch(){
        try {
            ArrayList<FoodItem> foodItems = foodItemDao.searchByKey(view.getComboSearchField().getSelectedItem().toString(), String.valueOf(view.getSearchTxt().getText()));
            view.setTableData(foodItems);
        } catch (Exception e) {
            //view.showError(e);
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
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa món?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn món cần xoá!");
            }
            
            for (int i = 0; i < selectedIds.length; i++) {
                foodItemDao.deleteById(selectedIds[i]);
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
                throw new Exception("Chọn món cần chỉnh sửa");
            } else {
                FoodItem foodItem = foodItemDao.getById(selectedId);
                if (foodItem == null) {
                    throw new Exception("Món bạn chọn không hợp lệ");
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
                
                popupController.edit(new FoodItemPopupView(), foodItem, successCallback, errorCallback);
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void updateData(){
        try {
            ArrayList<FoodItem> foodItems = foodItemDao.getAll();
            view.setTableData(foodItems);
        } catch (Exception e) {
            view.showError(e);
        }
    }
}
