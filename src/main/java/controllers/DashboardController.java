/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.admin.CustomerManagerController;
import controllers.admin.OrderManagerController;
import controllers.admin.ShipmentManagerController;
import controllers.employee.EmployeeInformationController;
import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import utils.SessionManager;
import views.DashboardView;
import views.ForgotPasswordView;
import views.LoginView;
import views.RegisterView;
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
public abstract class DashboardController<T extends DashboardView> {
    protected T view;
    protected final ManagerController orderManagerController = new OrderManagerController();
    protected final ManagerController shipmentManagerController = new ShipmentManagerController();
    protected final ManagerController customerManagerController = new CustomerManagerController();
    protected final ManagerPaneView orderManagerView = new OrderManagerView();
    protected final ManagerPaneView shipmentManagerView = new ShipmentManagerView();
    protected final ManagerPaneView customerManagerView = new CustomerManagerView();
    protected final HomeView homeView = new HomeView();
    protected final AboutView aboutView = new AboutView();
    protected final SidebarController sidebarController = new SidebarController();
    protected final EmployeeInformationController informationController = new EmployeeInformationController();
    protected final EmployeeInformationView informationView = new EmployeeInformationView();

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
        sidebarController.setSidebarPanel(view.getPanelSideBar());
    }

    protected abstract void initMenu();

    protected void addEvent() {
        view.getBtnLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int confirm = JOptionPane.showConfirmDialog(view, "Bạn thực sự muốn đăng xuất?");
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
                try {
                    SessionManager.update(); // Đăng xuất
                } catch (SQLException ex) {
                    view.showError(ex);
                }
                view.dispose();
                EmployeeDao employeeDao = new EmployeeDao();
                LoginController controller = new LoginController(new LoginView(), employeeDao, new ForgotPasswordView(), new RegisterView());
                controller.showView();
            }
        });
    }

    public abstract void onMenuChange(MenuItemView item);
}