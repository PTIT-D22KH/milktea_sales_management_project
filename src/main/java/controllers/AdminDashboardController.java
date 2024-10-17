/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.admin.EmployeeManagerController;
import controllers.admin.FoodCategoryManagerController;
import controllers.admin.FoodItemManagerController;
import controllers.admin.TableManagerController;
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
    private ManagerController tableManagerController = new TableManagerController();
    private ManagerController foodCategoryManagerController = new FoodCategoryManagerController();
    private ManagerController foodItemManagerController = new FoodItemManagerController();
    private EmployeeInformationController informationController = new EmployeeInformationController();

    private ManagerPaneView employeeManagerView = new EmployeeManagerView();
    private ManagerPaneView tableManagerView = new TableManagerView(); 
    private ManagerPaneView foodCategoryManagerView= new FoodCategoryManagerView();
    private ManagerPaneView foodItemManagerView = new FoodItemManagerView();
    
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
        
//        menuQLKH.setVisible(true);
        MenuItemView menuQLKH = new MenuItemView("QLKH", "Quản lý khách hàng");
        MenuItemView menuQLNV = new MenuItemView("QLNV", "Quản lý nhân viên");
        MenuItemView menuQLHH = new MenuItemView("QLHH", "Quản lý hàng hóa");
        MenuItemView menuQLDH = new MenuItemView("QLDH", "Quản lý đặt hàng");
        menuQLHH.addSubMenu(new MenuItemView("QLLM", null, "Quản lý loại món"));
        menuQLHH.addSubMenu(new MenuItemView("QLMA", "Quản lý món ăn"));
        menuQLDH.addSubMenu(new MenuItemView("QLB", "Quản lý bàn"));
        menuQLDH.addSubMenu(new MenuItemView("QLKH", "Quản lý khách hàng"));
        menuQLDH.addSubMenu(new MenuItemView("QLDDH", "Quản lý đơn đặt hàng"));
        menuQLDH.addSubMenu(new MenuItemView("QLGH", "Quản lý giao hàng"));
//        MenuItemView menuQLDDH = new MenuItemView("QLDDH","Quản lý đơn đặt hàng");
//        MenuItemView menuQLGH = new MenuItemView("QLGH", "Quản lý giao hàng");
        MenuItemView menuTL = new MenuItemView("TL", "Thiết lập");
        menuTL.addSubMenu(new MenuItemView("TTCN", "Thông tin cá nhân"));
        menuTL.addSubMenu(new MenuItemView("TLGD", "Giao diện"));
        menuTL.addSubMenu(new MenuItemView("TT", "About us"));
        
        sidebarController.addMenu(menuQLNV, menuQLHH, menuQLDH, menuTL);
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
            case "QLB"://Quản lý bàn
                view.setPanel(tableManagerView);
                tableManagerController.setView(tableManagerView);
                tableManagerController.updateData();
                break;
            case "QLLM"://Quản lý loại món
                view.setPanel(foodCategoryManagerView);
                foodCategoryManagerController.setView(foodCategoryManagerView);
                foodCategoryManagerController.updateData();
                break;
            case "QLMA"://Quản lý món ăn
                view.setPanel(foodItemManagerView);
                foodItemManagerController.setView(foodItemManagerView);
                foodItemManagerController.updateData();
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
