/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.admin.CustomerManagerController;
import controllers.admin.OrderManagerController;
import controllers.admin.ShipmentManagerController;
import controllers.employee.EmployeeInformationController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.SessionManager;
import models.Employee;
import utils.IconManager;
import views.EmployeeDashboardView;
import views.admin.AboutView;
import views.admin.CustomerManagerView;
import views.admin.HomeView;
import views.admin.ManagerPaneView;
import views.admin.MenuItemView;
import views.admin.OrderManagerView;
import views.admin.ShipmentManagerView;
import views.employee.EmployeeInformationView;


/**
 *
 * @author P51
 */
public class EmployeeDashboardController extends DashboardController<EmployeeDashboardView>{
    private final EmployeeInformationController informationController = new EmployeeInformationController();
    private final EmployeeInformationView informationView = new EmployeeInformationView();
    private final JPanel[] cards = {homeView, orderManagerView, customerManagerView, shipmentManagerView, aboutView, informationView};

    public EmployeeDashboardController(EmployeeDashboardView view) {
        this.view = view;
        sidebarController.setSidebarPanel(view.getPanelSideBar());
        view.setVisible(true);
        initMenu();
        addEvent();
        Employee session = SessionManager.getSession().getEmployee();
        if (session != null) {
            view.getLbName().setText(session.getName());
        }
        view.setCards(cards);
        view.setPanel(homeView);
    }

    @Override
    protected void initMenu() {
        IconManager im = new IconManager();
        MenuItemView menuKH = new MenuItemView("QLKH", "Quản lý khách hàng");
        menuKH.setVisible(true);
        MenuItemView menuQLDDH = new MenuItemView("QLDDH", "Quản lý đơn đặt hàng");
        MenuItemView menuQLGH = new MenuItemView("QLGH", "Quản lý giao hàng");
        MenuItemView menuTL = new MenuItemView("TL", "Thiết lập");
        menuTL.addSubMenu(new MenuItemView("TTCN", "Thông tin cá nhân"));
        menuTL.addSubMenu(new MenuItemView("TLGD", "Giao diện"));
        menuTL.addSubMenu(new MenuItemView("TT", "About us"));
        sidebarController.addMenu(menuKH, menuQLDDH, menuQLGH, menuTL);
        sidebarController.addMenuEvent(this::onMenuChange);
    }

    @Override
    public void onMenuChange(MenuItemView item) {
        switch (item.getId()) {
            case "QLDDH": // Đơn đặt hàng
                view.setPanel(orderManagerView);
                orderManagerController.setView(orderManagerView);
                orderManagerController.updateData();
                break;
            case "QLKH": // Quản lý khách hàng
                view.setPanel(customerManagerView);
                customerManagerController.setView(customerManagerView);
                customerManagerController.updateData();
                break;
            case "QLGH": // Quản lý giao hàng
                view.setPanel(shipmentManagerView);
                shipmentManagerController.setView(shipmentManagerView);
                shipmentManagerController.updateData();
                break;
            case "TT":
                view.setPanel(aboutView);
                break;
            case "TTCN":
                view.setPanel(informationView);
                informationController.setView(informationView);
                break;
            default:
                view.setPanel(homeView);
        }
    }
}
