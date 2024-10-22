package controllers.popup;

import models.FoodCategory;
import dao.FoodCategoryDao;
import views.popup.FoodCategoryPopupView;

public class FoodCategoryPopupController extends PopupController<FoodCategoryPopupView, FoodCategory>{
    private final FoodCategoryDao foodCategoryDao;
    
    public FoodCategoryPopupController(){
        this.foodCategoryDao = new FoodCategoryDao();
    }

    public FoodCategoryPopupController(FoodCategoryDao foodCategoryDao) {
        this.foodCategoryDao = foodCategoryDao;
    }
    
    @Override
    public void edit(FoodCategoryPopupView view, FoodCategory fc, SuccessCallback sc, ErrorCallback ec){
        super.edit(view, fc, sc, ec);
        view.getLbTitle().setText("Sửa loại món - " + fc.getFoodCategoryId());
        view.getTxtName().setText(fc.getName());
    }
    @Override
    protected void addEntity(FoodCategoryPopupView view) throws Exception{
        String foodCategoryName = view.getTxtName().getText();
        validateFoodCategoryName(foodCategoryName);
        
        if (foodCategoryDao.findByName(foodCategoryName) != null){
            throw new Exception("Tên món đã tồn tại");
        }
        
        FoodCategory fc = new FoodCategory();
        fc.setName(foodCategoryName);
        foodCategoryDao.save(fc);
    }
    @Override
    protected void editEntity(FoodCategoryPopupView view, FoodCategory fc) throws Exception {
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
