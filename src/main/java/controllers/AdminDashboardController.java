/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.admin.EmployeeManagerController;
import controllers.employee.EmployeeInformationController;
import javax.swing.JPanel;
import models.Employee;
import utils.SessionManager;
import views.AdminDashboardView;
import views.DashboardView;
import views.admin.CustomerManagerView;
import views.admin.EmployeeManagerView;
import views.admin.FoodCategoryManagerView;
import views.admin.FoodItemManagerView;
import views.admin.ManagerPaneView;
import views.admin.MenuItemView;
import views.admin.ShipmentManagerView;
import views.admin.TableManagerView;
import views.employee.EmployeeInformationView;

/**
 *
 * @author P51
 */
public class AdminDashboardController extends DashboardController<AdminDashboardView>{
    private ManagerController employeeManagerController = new EmployeeManagerController();
    private ManagerPaneView employeeManagerView = new EmployeeManagerView();
    private ManagerPaneView tableManagerView = new TableManagerView(); 
    private ManagerPaneView foodCategoryManagerView= new FoodCategoryManagerView();
    private ManagerPaneView foodItemManagerView = new FoodItemManagerView();
    private EmployeeInformationController informationController = new EmployeeInformationController();
    private EmployeeInformationView informationView = new EmployeeInformationView();
    private JPanel[] cards = {
        homeView, employeeManagerView, tableManagerView, customerManagerView,
        foodCategoryManagerView, orderManagerView, foodItemManagerView, shipmentManagerView,
        aboutView, informationView
    };

    public AdminDashboardController(AdminDashboardView view) {
        this.view = view;
        sidebarController.setSidebarPanel(view.getPanelSideBar());
        view.setVisible(true);
        initMenu();
        addEvent();
        Employee session = SessionManager.getSession().getEmployee();
        System.out.println(session);
        if (session != null) {
            view.getLbName().setText(session.getName());
        }
        view.setCards(cards);
        view.setPanel(homeView);
        view.setCards(cards);
    }

    @Override
    protected void initMenu() {
        MenuItemView menuKH = new MenuItemView("QLKH", "Quản lý khách hàng");
        menuKH.setVisible(true);
        MenuItemView menuQLDDH = new MenuItemView("QLDDH","Quản lý đơn đặt hàng");
        MenuItemView menuQLGH = new MenuItemView("QLGH", "Quản lý giao hàng");
        MenuItemView menuTL = new MenuItemView("TL", "Thiết lập");
        menuTL.addSubMenu(new MenuItemView("TTCN", "Thông tin cá nhân"));
        menuTL.addSubMenu(new MenuItemView("TLGD", "Giao diện"));
        menuTL.addSubMenu(new MenuItemView("TT", "About us"));
        MenuItemView menuQLNV = new MenuItemView("QLNV", "Quản lý nhân viên");
        sidebarController.addMenu(menuQLNV, menuKH, menuQLDDH, menuQLGH, menuTL);
        sidebarController.addMenuEvent(this::onMenuChange);
    }

    @Override
    public void onMenuChange(MenuItemView item) {
        switch (item.getId()) {
            case "QLNV": // Quản lý nhân viên
                view.setPanel(employeeManagerView);
                employeeManagerController.setView(employeeManagerView);
                employeeManagerController.updateData();
                break;
            case "QLDDH"://Đơn đặt hàng
                view.setPanel(orderManagerView);
                orderManagerController.setView(orderManagerView);
                orderManagerController.updateData();
                break;
            case "QLKH"://Quản lý khách hàng
                view.setPanel(customerManagerView);
                customerManagerController.setView(customerManagerView);
                customerManagerController.updateData();
                break;
            case "QLGH"://Quản lý giao hàng
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
