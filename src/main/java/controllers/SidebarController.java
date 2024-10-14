/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.MenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import views.admin.MenuItemView;

/**
 *
 * @author P51
 */
public class SidebarController {
    private JPanel sidebarPanel;
    private ArrayList<MenuItemView> menuItems = new ArrayList<>();
    private MenuItemView activeMenuItemView = null; //item vừa chọn
    
    private interface MenuBarEvent {
        public abstract void onSelectMenuItem(MenuItemView item);
    }
    public SidebarController() {
        
    }
    
    public SidebarController(JPanel sidebarPanel) {
        this.sidebarPanel = sidebarPanel;
    }

    public void setSidebarPanel(JPanel sidebarPanel) {
        this.sidebarPanel = sidebarPanel;
    }

    public JPanel getSidebarPanel() {
        return sidebarPanel;
    }

    public void setMenuItems(ArrayList<MenuItemView> menuItems) {
        this.menuItems = menuItems;
    }

    public ArrayList<MenuItemView> getMenuItems() {
        return menuItems;
    }
    
    //add dropdown menu
    public void addMenu(MenuItemView... menu) {
        for (int i = 0; i < menu.length; i++) {
            MenuItemView item = menu[i];
            menuItems.add(item);
            item.setActive(false);
            sidebarPanel.add(item);
            ArrayList<MenuItemView> subMenus = item.getSubMenu();
            for (MenuItemView subMenu : subMenus) {
                addMenu(subMenu);
                subMenu.setVisible(false);
            }
        }
    }
    
    public void addMenuEvent(MenuBarEvent mbe) { // Thêm event mỗi khi click vào 1 item 
        for (MenuItemView menuItem : menuItems) {
            menuItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (!menuItem.equals(activeMenuItemView)) { // Nếu click lại thì bỏ qua
                        mbe.onSelectMenuItem(menuItem);
                    }
                    setMenu(menuItem);
                }
            });
        }
    }

    public void renderMenu() {
        for (MenuItemView menuItem : menuItems) {
            MenuItemView parrent = menuItem.getParentMenu();
            if (parrent == null) { // Nếu là menu cha trên cùng
                menuItem.setVisible(true);
            } else if (parrent.isActive()) {//Nếu cha được chọn thì show con ra
                menuItem.setVisible(true);
            } else if (menuItem.isActive()) {//Nếu đang chọn thì show ra
                menuItem.setVisible(true);
            } else {
                menuItem.setVisible(false);
            }
        }
    }

    private void closePreviosMenu(MenuItemView previousItem) {//Đóng menu cũ
        MenuItemView parent = previousItem.getParentMenu();
        previousItem.setActive(false);
        while (parent != null) {
            parent.setActive(false);
            parent = parent.getParentMenu();
        }
    }

    public void setMenu(MenuItemView item) {//Chọn menu
        boolean isActive = item.isActive();
        if (activeMenuItemView != null) {
            closePreviosMenu(activeMenuItemView);
        }
        MenuItemView parent = item.getParentMenu();
        while (parent != null) {
            parent.setActive(true);
            parent = parent.getParentMenu();
        }
        item.setActive(!isActive);
        activeMenuItemView = item;
        renderMenu();
    }
    
}
