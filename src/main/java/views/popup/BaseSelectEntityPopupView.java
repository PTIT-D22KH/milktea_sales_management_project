/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package views.popup;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import models.Model;
import utils.ErrorPopup;

/**
 *
 * @author P51
 */
public interface BaseSelectEntityPopupView <M extends Model>{
    public JButton getBtnCancel();
    public JButton getBtnOK();
    public JButton getBtnSearch();
    public JList<M> getEntityList();
    public void renderEntity(ArrayList<M> list);
    public JTextField getEntityNameTxtField();
    public void showError(String message);
    public void showError(Exception message);
}
