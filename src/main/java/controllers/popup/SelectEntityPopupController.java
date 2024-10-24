/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.Dao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import models.Employee;
import models.Model;
import views.popup.SelectEntityPopupView;


/**
 *
 * @author P51
 */
/**
 * Abstract controller for managing entity selection popups.
 * Adheres to SRP by focusing only on entity selection popup management.
 * 
 * @param <T> The type of SelectEntityPopupView
 * @param <S> The type of Dao
 * @param <U> The type of Model
 */
public abstract class SelectEntityPopupController <T extends SelectEntityPopupView<U>, S extends Dao<U>, U extends Model>{
    private JFrame previousView;
    private final S entityDao;
    public SelectEntityPopupController(S entityDao) {
        this.entityDao = entityDao;
    }
    public interface Callback <U>{
        void run(U model);
    }
    
    public void select(T view, Callback<U> callback) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        try {
            view.renderEntity(entityDao.getAll());
        } catch (SQLException e) {
            view.showError(e);
        }
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }
    protected JFrame getPreviousView() {
        return previousView;
    }

    protected void setPreviousView(JFrame previousView) {
        this.previousView = previousView;
    }

    protected S getEntityDao() {
        return entityDao;
    }
}
