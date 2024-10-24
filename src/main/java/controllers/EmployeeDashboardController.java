/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import controllers.employee.EmployeeInformationController;
import javax.swing.JPanel;
import utils.SessionManager;
import models.Employee;
import views.EmployeeDashboardView;
import views.admin.MenuItemView;
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
        MenuItemView menuKH = new MenuItemView("QLKH", "Quản lý khách hàng");
        menuKH.setVisible(true);
        MenuItemView menuQLDDH = new MenuItemView("QLDDH", "Quản lý đơn đặt hàng");
        MenuItemView menuQLGH = new MenuItemView("QLGH", "Quản lý giao hàng");
        MenuItemView menuTTCN = new MenuItemView("TTCN", "Thông tin cá nhân");
        MenuItemView menuTLGD = new MenuItemView("TLGD", "Giao diện");
        MenuItemView menuTT = new MenuItemView("TT", "About us");
        sidebarController.addMenu(menuKH, menuQLDDH, menuQLGH, menuTTCN, menuTLGD, menuTT);
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
