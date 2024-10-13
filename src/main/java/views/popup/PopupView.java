/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.popup;

import javax.swing.JButton;
import javax.swing.JLabel;
/**
 *
 * @author P51
 */
public interface PopupView {
    public abstract JButton getBtnOK();

    public abstract JButton getBtnCancel();

    public abstract JLabel getLbTitle();

    public abstract void dispose();

    public abstract void setVisible(boolean b);

    public abstract void showError(String message);

    public abstract void showError(Exception e);

    public abstract void showMessage(String message);
    
    public abstract String getClassName();
}
