/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.ErrorPopup;
import views.admin.MenuItemView;

/**
 *
 * @author P51
 */
public abstract class DashboardView extends JFrame{
    protected JPanel[] cards;
    protected ArrayList<MenuItemView> menuItems = new ArrayList<>();
    
    public void showError(String message) {
        ErrorPopup.show(new Exception(message));
    }

    public void showError(Exception e) {
        ErrorPopup.show(e);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public abstract JButton getBtnLogout();

    public abstract JLabel getLbName();

    // Thêm dropdown menu
    public abstract void addMenu(MenuItemView... menu);

    public ArrayList<MenuItemView> getMenuItems() {
        return menuItems;
    }

    public void setCards(JPanel[] cards) {
        this.cards = cards;
        initLayout();
    }

    // Thêm các pane vào cardlayout
    public abstract void initLayout();

    public abstract JPanel getPanelLayout();

    public abstract JPanel getPanelSideBar();

    public void setPanel(JPanel panel) {
        for (JPanel card : cards) {
            card.setVisible(false);
        }
        panel.setVisible(true);
    }
    
}
