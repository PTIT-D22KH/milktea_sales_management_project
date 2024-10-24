/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package views.popup;

import javax.swing.JButton;

/**
 *
 * @author P51
 */
public interface BasePopupView extends MessageShowable{
    public abstract JButton getBtnOK();
    public abstract JButton getBtnCancel();
};
