/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import utils.ErrorPopup;
import views.popup.MessageShowable;

/**
 *
 * @author P51
 */
public abstract class AuthenticationView extends JFrame implements MessageShowable{
    public abstract JTextField getUsernameTxtField();
    public abstract JPasswordField getPasswordField();
    @Override
    public void showError(String message) {
        ErrorPopup.show(new Exception(message));
    }


    @Override
    public void showError(Exception e) {
        ErrorPopup.show(e);
    }


    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
