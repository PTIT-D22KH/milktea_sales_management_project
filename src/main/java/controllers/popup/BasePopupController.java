/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controllers.popup;

import javax.swing.JFrame;
import models.Model;
/**
 *
 * Base interface for managing popups.
 * Adheres to ISP by providing only necessary methods for popup management.
 * @author P51
 * @param <T> type of the view, which extends JFrame
 * @param <S> type of the model, which extends Model
 */

public interface BasePopupController <T extends JFrame, S extends Model>{
    void add(T view, SuccessCallback sc, ErrorCallback ec);

    /**
     *
     * @param view
     * @param model
     * @param sc
     * @param ec
     */
    void edit(T view, S model, SuccessCallback sc, ErrorCallback ec);
}
