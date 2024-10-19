/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.employee;

import dao.SessionDao;
import java.util.ArrayList;
import javax.swing.JFrame;
import models.Session;
import utils.SessionManager;
import views.employee.LoginHistoryView;
import views.employee.SessionLoginView;

/**
 *
 * @author P51
 */
public class LoginHistoryController {
    private JFrame previousView;
    private LoginHistoryView view;
    private final SessionDao sessionDao = new SessionDao();
    private int employeeId;
    public LoginHistoryController() {
        employeeId = SessionManager.getSession().getEmployeeId();
    }
    public void show(LoginHistoryView view) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            render();
            return;
        }
        previousView = view;
        this.view = view;
        view.setVisible(true);
        render();
    }
    private void render() {
        view.getContentPanel().removeAll();
        try {
            ArrayList<Session> sessions = sessionDao.getSession(employeeId);
            for (Session session : sessions) {
                SessionLoginController controller = new SessionLoginController();
                SessionLoginView view = new SessionLoginView();
                controller.setView(view);
                controller.render(session);
                this.view.getContentPanel().add(view);
                this.view.getContentPanel().updateUI();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
