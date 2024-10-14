/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controllers.popup;

import javax.swing.JFrame;
import models.Model;
/**
 *
 * @author P51
 * @param <T> type of the view, which extends JFrame
 * @param <M> type of the model, which extends Model
 */

public interface BasePopupController <T extends JFrame, M extends Model>{
    void add(T view, SuccessCallback sc, ErrorCallback ec);
    void edit(T view, M model, SuccessCallback sc, ErrorCallback ec);
}
