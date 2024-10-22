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
import views.admin.EmployeeManagerView;
import views.admin.FoodCategoryManagerView;
import views.admin.FoodItemManagerView;
import views.admin.ManagerPaneView;
import views.admin.MenuItemView;
import views.admin.TableManagerView;
import views.employee.EmployeeInformationView;


/**
 *
 * @author P51
 */
public class AdminDashboardController extends DashboardController<AdminDashboardView>{
    
    private final ManagerController employeeManagerController;
    private final ManagerController tableManagerController;
    private final ManagerController foodCategoryManagerController;
    private final ManagerController foodItemManagerController;
    private final EmployeeInformationController informationController;

    private final ManagerPaneView employeeManagerView;
    private final ManagerPaneView tableManagerView;
    private final ManagerPaneView foodCategoryManagerView;
    private final ManagerPaneView foodItemManagerView;
    private final EmployeeInformationView informationView;
    private final JPanel[] cards;

    public AdminDashboardController(AdminDashboardView view) {
        this.view = view;
        this.employeeManagerController = new EmployeeManagerController();
        this.tableManagerController = new TableManagerController();
        this.foodCategoryManagerController = new FoodCategoryManagerController();
        this.foodItemManagerController = new FoodItemManagerController();
        this.informationController = new EmployeeInformationController();

        this.employeeManagerView = new EmployeeManagerView();
        this.tableManagerView = new TableManagerView();
        this.foodCategoryManagerView = new FoodCategoryManagerView();
        this.foodItemManagerView = new FoodItemManagerView();
        this.informationView = new EmployeeInformationView();

        this.cards = new JPanel[]{
            homeView, employeeManagerView, tableManagerView, customerManagerView,
            foodCategoryManagerView, orderManagerView, foodItemManagerView, shipmentManagerView,
            aboutView, informationView
        };

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
        MenuItemView menuQLKH = new MenuItemView("QLKH", "Quản lý khách hàng");
        MenuItemView menuQLNV = new MenuItemView("QLNV", "Quản lý nhân viên");
        MenuItemView menuQLLM = new MenuItemView("QLLM","Quản lý loại món");
        MenuItemView menuQLMA = new MenuItemView("QLMA", "Quản lý món ăn");
        MenuItemView menuQLB = new MenuItemView("QLB", "Quản lý bàn");
        MenuItemView menuQLDDH = new MenuItemView("QLDDH", "Quản lý đơn đặt hàng");
        MenuItemView menuQLGH = new MenuItemView("QLGH", "Quản lý giao hàng");
        MenuItemView menuTTCN = new MenuItemView("TTCN", "Thông tin cá nhân");
        MenuItemView menuTLGD = new MenuItemView("TLGD", "Giao diện");
        MenuItemView menuTT = new MenuItemView("TT", "About us");
        sidebarController.addMenu(menuQLNV,  menuQLKH, menuQLLM, menuQLMA, menuQLB, menuQLDDH, menuQLGH,menuTTCN, menuTLGD,menuTT );
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
            case "QLDDH": // Đơn đặt hàng
                view.setPanel(orderManagerView);
                orderManagerController.setView(orderManagerView);
                orderManagerController.updateData();
                break;
            case "QLB": // Quản lý bàn
                view.setPanel(tableManagerView);
                tableManagerController.setView(tableManagerView);
                tableManagerController.updateData();
                break;
            case "QLLM": // Quản lý loại món
                view.setPanel(foodCategoryManagerView);
                foodCategoryManagerController.setView(foodCategoryManagerView);
                foodCategoryManagerController.updateData();
                break;
            case "QLMA": // Quản lý món ăn
                view.setPanel(foodItemManagerView);
                foodItemManagerController.setView(foodItemManagerView);
                foodItemManagerController.updateData();
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
