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
public interface BaseSelectEntityPopupView <T extends Model> extends BasePopupView{
    public JButton getBtnSearch();
    public JList<T> getEntityList();
    public void renderEntity(ArrayList<T> list);
    public JTextField getEntityNameTxtField();
}
