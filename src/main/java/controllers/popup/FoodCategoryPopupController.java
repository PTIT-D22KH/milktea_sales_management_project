package controllers.popup;

import models.FoodCategory;
import dao.FoodCategoryDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.popup.FoodCategoryPopupView;
import javax.swing.JFrame;

public class FoodCategoryPopupController {
    private FoodCategoryDao foodCategoryDao;
    private JFrame previousView;
    
    public FoodCategoryPopupController(){
        this.foodCategoryDao = new FoodCategoryDao();
    }
    
    public void add(FoodCategoryPopupView view, SuccessCallback sc, ErrorCallback ec){
        if (previousView != null && previousView.isDisplayable()){
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(event -> view.dispose());
        view.getBtnOK().addActionListener(event -> {
            try {
                addFoodCategory(view);
                view.dispose();
                view.showMessage("Thêm loại món thành công");
                sc.onSuccess();
            }
            catch (Exception ex){
                ec.onError(ex);
            }
        });
    }
    
    public void edit(FoodCategoryPopupView view, FoodCategory fc, SuccessCallback sc, ErrorCallback ec){
        if (previousView != null && previousView.isDisplayable()){
            previousView.requestFocus();
            return;
        }
        
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                view.dispose();
            }
        });
        view.getLbTitle().setText("Sửa loại món - " + fc.getFoodCategoryId());
        view.getTxtName().setText(fc.getName());
        view.getBtnOK().setText("Cập nhật");
        view.getBtnOK().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    editFoodCategory(view, fc);
                    view.dispose();
                    view.showMessage("Sửa món thành công");
                    sc.onSuccess();
                } catch (Exception ex) {
                    ec.onError(ex);
                }
            }
        });
        
    }
    
    private void addFoodCategory(FoodCategoryPopupView view) throws Exception{
        String foodCategoryName = view.getTxtName().getText();
        validateFoodCategoryName(foodCategoryName);
        
        if (foodCategoryDao.findByName(foodCategoryName) != null){
            throw new Exception("Tên món đã tồn tại");
        }
        
        FoodCategory fc = new FoodCategory();
        fc.setName(foodCategoryName);
        foodCategoryDao.save(fc);
    }
    
    private void editFoodCategory(FoodCategoryPopupView view, FoodCategory fc) throws Exception {
        String foodCategoryName = view.getTxtName().getText();
        validateFoodCategoryName(foodCategoryName);

        
        FoodCategory tmp = foodCategoryDao.findByName(foodCategoryName);
        if (tmp != null && tmp.getFoodCategoryId() != fc.getFoodCategoryId()){
            throw new Exception("Tên loại món đã được sử dụng");
        }
        
        fc.setName(foodCategoryName);
        foodCategoryDao.update(fc);
    }
    private void validateFoodCategoryName(String foodCategoryName) throws Exception {
        if (foodCategoryName.isEmpty()) {
            throw new Exception("Vui lòng điền tên loại món");
        }
    }
    
}
