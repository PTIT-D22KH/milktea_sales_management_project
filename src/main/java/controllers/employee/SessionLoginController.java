/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.employee;

import java.awt.Color;
import java.text.SimpleDateFormat;
import models.Session;
import views.employee.SessionLoginView;

/**
 *
 * @author P51
 */
public class SessionLoginController {
    private SessionLoginView view;
    private SimpleDateFormat dateFormatter;
    public SessionLoginController() {
        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void setView(SessionLoginView view) {
        this.view = view;
    }
    public void render(Session session) {
        view.getStartTimeLabel().setText(dateFormatter.format(session.getStartTime()));
        if (session.getEndTime() == null) {
            view.getEndTimeLabel().setText("");
        } else {
            view.getEndTimeLabel().setText(dateFormatter.format(session.getEndTime()));
        }
        if (session.getMessage().equals("login")) {
            view.getStatusLabel().setText("Đang hoạt động");
            view.getStatusLabel().setForeground(Color.GREEN);
        } else {
            view.getStatusLabel().setText("Đã đăng xuất");
            view.getStatusLabel().setForeground(Color.red);
        }
    }
    
}
