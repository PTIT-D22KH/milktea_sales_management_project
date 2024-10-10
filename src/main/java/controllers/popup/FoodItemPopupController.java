package controllers.popup;

import dao.FoodCategoryDao;
import dao.FoodItemDao;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import models.FoodCategory;
import models.FoodItem;
import utils.ImageManager;
import utils.StringToSlug;
import views.ChooseImageView;
import views.popup.FoodItemPopupView;

public class FoodItemPopupController {
    private FoodItemDao foodItemDao = new FoodItemDao();
    private FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
    private ChooseImageView chooseImageView = new ChooseImageView();
    private ImageManager imageManager = new ImageManager();
    private String resourcesPath = getClass().getResource("/images/").getPath();
    private JFrame previousView;
    
    private ActionListener eventShowChooseFileDialog(FoodItemPopupView view){
        return event -> {
            int otp = chooseImageView.showOpenDialog(view);
            switch (otp){
                case JFileChooser.APPROVE_OPTION:
                    File file = chooseImageView.getSelectedFile();
                    BufferedImage bi;
                    
                    try {
                        bi = ImageIO.read(file);
                        String name = StringToSlug.convert(view.getTxtFoodName().getText());
                        String pth = imageManager.saveImage(bi, name);
                        view.getTxtImagePath().setText(pth);
                        renderPreviewImage(view);
                    } catch (Exception e) {
                        view.showError(e);
                    }
                    break;
                case JFileChooser.CANCEL_OPTION:
                    view.getTxtImagePath().setText("");
                    break;

            }
        };
    }
    
    private void renderPreviewImage(FoodItemPopupView view) throws Exception {
        String imagePath = view.getTxtImagePath().getText();
        ImageIcon icon = imagePath.isEmpty() ? null : imageManager.getImage(imagePath);
        view.getLbPreviewImage().setIcon(icon);
        view.pack();
    }
    
    private void initComboBox(FoodItemPopupView view) { //Khởi tạo danh mục loai mon
        try {
            for (FoodCategory fc : foodCategoryDao.getAll()) {
                view.getFoodCategoryComboBoxModel().addElement(fc);
            }
        } 
        catch (Exception e) {
            
        }
    }
    
    public void add(FoodItemPopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        initComboBox(view);
        view.getBtnChooseImage().addActionListener(eventShowChooseFileDialog(view));
        view.getBtnOK().addActionListener(evt -> {
            try {
                addFoodItem(view);
                view.dispose();
                view.showMessage("Thêm món ăn thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }
    
    private void addFoodItem(FoodItemPopupView view) throws Exception {
        FoodItem foodItem = new FoodItem();
        FoodCategory selectedCategory   = (FoodCategory) view.getCmbFoodCategory().getSelectedItem();
        String foodName                 = view.getTxtFoodName().getText();
        String unit                     = view.getTxtUnit().getText();
        String unitPrice                = view.getTxtUnitPrice().getText();
        String description              = view.getTxtDescription().getText();
        String imagePath                = view.getTxtImagePath().getText();
        
        if (foodName.isEmpty() || unit.isEmpty() || unitPrice.isEmpty() || selectedCategory == null){
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        
        foodItem.setName(foodName);
        foodItem.setUnitName(unit);
        foodItem.setUnitPrice(Integer.parseInt(unitPrice));
        foodItem.setImagePath(imagePath);
        foodItem.setDescription(description);
        foodItem.setCategoryId(selectedCategory.getFoodCategoryId());
        foodItemDao.save(foodItem);
    }
    
    public void edit(FoodItemPopupView view, FoodItem foodItem, SuccessCallback sc, ErrorCallback ec) {
        
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        
        if (foodItem == null) {
            view.showError("Món không tồn tại");
        }
        
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        
        initComboBox(view);
        view.getLbTitle().setText("Sửa món ăn - " + foodItem.getFoodItemId());
        view.getBtnOK().setText("Cập nhật");
        view.getTxtFoodName().setText(foodItem.getName());
        view.getTxtDescription().setText(foodItem.getDescription());
        view.getTxtImagePath().setText(foodItem.getImagePath());
        view.getTxtUnit().setText(foodItem.getUnitName());
        view.getTxtUnitPrice().setText(foodItem.getUnitPrice() + "");

        FoodCategory fc = new FoodCategory();
        fc.setFoodCategoryId(foodItem.getCategoryId());
        view.getCmbFoodCategory().setSelectedItem(fc);
        if (isExistsImage(foodItem.getImagePath())) {
            view.getTxtImagePath().setText(foodItem.getImagePath());
        } else {
            view.getTxtImagePath().setText("");
        }
        try {
            renderPreviewImage(view);
        } catch (Exception e) {
            ec.onError(e);
        }
        view.getBtnChooseImage().addActionListener(eventShowChooseFileDialog(view));
        view.getBtnOK().addActionListener(evt -> {
            try {
                editFoodItem(view, foodItem);
                view.dispose();
                view.showMessage("Sửa món ăn thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }
    
    private boolean isExistsImage(String imagePath) {
        try {
            if (imagePath.isEmpty()) {
                return false;
            }
            File f = new File(resourcesPath + imagePath);
            return f.exists() && !f.isDirectory();
        } 
        catch (Exception e) {
            return false;
        }
    }
    
    private void editFoodItem(FoodItemPopupView view, FoodItem foodItem) throws Exception {
        FoodCategory selectedCategory = (FoodCategory) view.getCmbFoodCategory().getSelectedItem();
        String foodName                 = view.getTxtFoodName().getText();
        String unit                     = view.getTxtUnit().getText();
        String unitPrice                = view.getTxtUnitPrice().getText();
        String description              = view.getTxtDescription().getText();
        String imagePath                = view.getTxtImagePath().getText();
        
        if (foodName.isEmpty() || unit.isEmpty() || unitPrice.isEmpty() || selectedCategory == null){
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        
        foodItem.setName(foodName);
        foodItem.setUnitName(unit);
        foodItem.setUnitPrice(Integer.parseInt(unitPrice));
        foodItem.setImagePath(imagePath);
        foodItem.setDescription(description);
        foodItem.setCategoryId(selectedCategory.getFoodCategoryId());
        foodItemDao.update(foodItem);
    }
    
}
