/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

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
    
    interface MenuBarEvent {
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
            sidebarPanel.add(item);
            sidebarPanel.setVisible(true);
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

    public void setMenu(MenuItemView item) {//Chọn menu
        activeMenuItemView = item;
    }
    
}
