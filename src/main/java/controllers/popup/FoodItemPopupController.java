package controllers.popup;

import dao.FoodCategoryDao;
import dao.FoodItemDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import models.FoodCategory;
import models.FoodItem;
import utils.ImageManager;
import utils.StringToSlug;
import views.ChooseImageView;
import views.popup.FoodItemPopupView;

public class FoodItemPopupController extends PopupController<FoodItemPopupView, FoodItem>{
    private final FoodItemDao foodItemDao;
    private final FoodCategoryDao foodCategoryDao;
    private final ChooseImageView chooseImageView;
    private final ImageManager imageManager;
    private final String resourcesPath;
    
    public FoodItemPopupController() {
        foodItemDao = new FoodItemDao();
        foodCategoryDao = new FoodCategoryDao();
        chooseImageView = new ChooseImageView();
        imageManager = new ImageManager();
        resourcesPath = getClass().getResource("/images/").getPath();
        
    }
    
    public FoodItemPopupController(FoodItemDao foodItemDao, FoodCategoryDao foodCategoryDao) {
        this.foodItemDao = foodItemDao;
        this.foodCategoryDao = foodCategoryDao;
        chooseImageView = new ChooseImageView();
        imageManager = new ImageManager();
        resourcesPath = getClass().getResource("/images/").getPath();
        
    }
    
    private ActionListener eventShowChooseFileDialog(FoodItemPopupView view){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int otp = chooseImageView.showOpenDialog(view);
                switch (otp) {
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
        super.add(view, sc, ec);
        initComboBox(view);
        view.getBtnChooseImage().addActionListener(eventShowChooseFileDialog(view));
    }
    
    @Override
    protected void addEntity(FoodItemPopupView view) throws Exception {
        FoodItem foodItem = new FoodItem();
        FoodCategory selectedCategory   = (FoodCategory) view.getCmbFoodCategory().getSelectedItem();
        String foodName                 = view.getTxtFoodName().getText();
        String unit                     = view.getTxtUnit().getText();
        String unitPrice                = view.getTxtUnitPrice().getText();
        String description              = view.getTxtDescription().getText();
        String imagePath                = view.getTxtImagePath().getText();
        
        validateFoodItemData(foodName, unit, unitPrice, selectedCategory);
        
        foodItem.setName(foodName);
        foodItem.setUnitName(unit);
        foodItem.setUnitPrice(Integer.parseInt(unitPrice));
        foodItem.setImagePath(imagePath);
        foodItem.setDescription(description);
        foodItem.setFoodCategory(foodCategoryDao.getById(selectedCategory.getFoodCategoryId()));
        foodItemDao.save(foodItem);
    }
    
    @Override
    public void edit(FoodItemPopupView view, FoodItem foodItem, SuccessCallback sc, ErrorCallback ec) {
        super.edit(view, foodItem, sc, ec);
        if (foodItem == null) {
            view.showError("Món không tồn tại");
        }
        initComboBox(view);
        view.getLbTitle().setText("Sửa món ăn - " + foodItem.getFoodItemId());
        view.getTxtFoodName().setText(foodItem.getName());
        view.getTxtDescription().setText(foodItem.getDescription());
        view.getTxtImagePath().setText(foodItem.getImagePath());
        view.getTxtUnit().setText(foodItem.getUnitName());
        view.getTxtUnitPrice().setText(foodItem.getUnitPrice() + "");
        FoodCategory fc = new FoodCategory();
        fc.setFoodCategoryId(foodItem.getFoodCategory().getFoodCategoryId());
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
    
    @Override
    protected void editEntity(FoodItemPopupView view, FoodItem foodItem) throws Exception {
        FoodCategory selectedCategory = (FoodCategory) view.getCmbFoodCategory().getSelectedItem();
        String foodName                 = view.getTxtFoodName().getText();
        String unit                     = view.getTxtUnit().getText();
        String unitPrice                = view.getTxtUnitPrice().getText();
        String description              = view.getTxtDescription().getText();
        String imagePath                = view.getTxtImagePath().getText();
        
        validateFoodItemData(foodName, unit, unitPrice, selectedCategory);
        
        foodItem.setName(foodName);
        foodItem.setUnitName(unit);
        foodItem.setUnitPrice(Integer.parseInt(unitPrice));
        foodItem.setImagePath(imagePath);
        foodItem.setDescription(description);
        foodItem.setFoodCategory(foodCategoryDao.getById(selectedCategory.getFoodCategoryId()));
        foodItemDao.update(foodItem);
    }
    private void validateFoodItemData(String foodName, String unit, String unitPrice, FoodCategory selectedCategory) throws Exception {
        if (foodName.isEmpty() || unit.isEmpty() || unitPrice.isEmpty() || selectedCategory == null) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
    }
    
}
