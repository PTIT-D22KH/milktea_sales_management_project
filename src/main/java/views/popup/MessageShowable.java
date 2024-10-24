/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package views.popup;

/**
 *
 * @author P51
 */
public interface MessageShowable {
    public abstract void showError(String message);

    public abstract void showError(Exception e);

    public abstract void showMessage(String message);
}
