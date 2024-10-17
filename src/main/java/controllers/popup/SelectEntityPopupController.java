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
public abstract class SelectEntityPopupController <T extends SelectEntityPopupView, D extends Dao, M extends Model>{
    protected JFrame previousView;
    protected D entityDao;
    public SelectEntityPopupController() {
        
    }
    public SelectEntityPopupController(D entityDao) {
        this.entityDao = entityDao;
    }
    public interface Callback <M>{

        /**
         *
         * @param model
         * @param m
         */
        public abstract void run(M model);
    }
    
    public void select(T view, Callback<M> callback) {
        if(previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        try {         
            view.renderEntity(entityDao.getAll());
        }
        catch (SQLException e) {
            view.showError(e);
        }
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();            }
        });
    }
}
