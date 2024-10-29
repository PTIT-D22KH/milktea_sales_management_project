/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.popup;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import models.Model;
import utils.ErrorPopup;

/**
 *
 * @author P51
 */
public abstract class SelectEntityPopupView <T extends Model> extends javax.swing.JFrame implements BaseSelectEntityPopupView<T>{
    protected DefaultListModel<T> entityListModel;
    public SelectEntityPopupView() {
        this.entityListModel = new DefaultListModel<>();
    }
    protected abstract void mineInitComponents();

    @Override
    public abstract JButton getBtnCancel();
    @Override
    public abstract JButton getBtnOK();
    @Override
    public abstract JButton getBtnSearch();
    @Override
    public abstract JList<T> getEntityList();
    @Override
    public abstract JTextField getEntityNameTxtField();
    
    @Override
    public void renderEntity(ArrayList<T> list) {
        entityListModel.removeAllElements();
        for (T entity : list) {
            entityListModel.addElement(entity);
        }
    }

    @Override
    public void showError(String message) {
        ErrorPopup.show(new Exception(message));
    }

    @Override
    public void showError(Exception message) {
        ErrorPopup.show(message);
    }
    @Override
    public void showMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
