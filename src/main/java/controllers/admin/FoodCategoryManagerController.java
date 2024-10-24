package controllers.admin;

import controllers.ManagerController;
import controllers.popup.ErrorCallback;
import controllers.popup.FoodCategoryPopupController;
import controllers.popup.SuccessCallback;
import dao.FoodCategoryDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.FoodCategory;
import views.popup.FoodCategoryPopupView;
/**
 * Controller for managing food category data.
 * Adheres to SRP by focusing only on food category management.
 */
public class FoodCategoryManagerController extends ManagerController {
    
    private final FoodCategoryDao foodCategoryDao;
    private final FoodCategoryPopupController foodCategoryPopupController;
    
    public FoodCategoryManagerController() {
        this.foodCategoryDao = new FoodCategoryDao();
        this.foodCategoryPopupController = new FoodCategoryPopupController();
    }   
    
    public FoodCategoryManagerController(FoodCategoryDao foodCategoryDao, FoodCategoryPopupController foodCategoryPopupController){
        this.foodCategoryDao = foodCategoryDao;
        this.foodCategoryPopupController = foodCategoryPopupController;
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
        foodCategoryPopupController.add(new FoodCategoryPopupView(), successCallback, errorCallback);
    }

    @Override
    public void actionSearch() {
        try {
            String keyChoice = getView().getComboSearchField().getSelectedItem().toString();
            String convertKeyChoice = new String();
            switch (keyChoice) {
                case "Mã loại món":
                    convertKeyChoice = "FoodCategoryId";
                    break;
                case "Tên loại món":
                    convertKeyChoice = "Name";
                    break;
                default:
                    throw new AssertionError();
            }
            ArrayList<FoodCategory> foodCategories = foodCategoryDao.searchByKey(convertKeyChoice, String.valueOf(getView().getSearchTxt().getText()));
            getView().setTableData(foodCategories);
        } catch (Exception ex) {
            getView().showError(ex);
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
                if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa loại món?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn loại món cần xoá!");
            }
            for (int id : selectedIds) {
                foodCategoryDao.deleteById(id);
            }
            updateData();
        } catch (Exception e) {
            getView().showError(e);
        }
    }

    @Override
    public void actionEdit() {
        try {
            int selectedId = getView().getSelectedId();
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
                        getView().showError(e);
                    }
                };

                foodCategoryPopupController.edit(new FoodCategoryPopupView(), foodCategory, successCallback, errorCallback);
            }
        } catch (Exception e) {
            getView().showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<FoodCategory> foodCategories = foodCategoryDao.getAll();
            getView().setTableData(foodCategories);
        } catch (Exception e) {
            getView().showError(e);
        }
    }
}
