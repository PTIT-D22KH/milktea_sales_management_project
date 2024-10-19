/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import models.Model;
import views.popup.PopupView;

/**
 *
 * @author P51
 * @param <T>
 * @param <M>
 */
/**
 * Abstract controller for managing popups.
 * Adheres to SRP by focusing only on popup management.
 * 
 * @param <T> The type of PopupView
 * @param <M> The type of Model
 */
public abstract class PopupController<T extends JFrame & PopupView, S extends Model> implements BasePopupController<T, S> {
    private JFrame previousView;

    @Override
    public void add(T view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        addCancelListener(view);
        addOkListener(view, sc, ec);
    }

    @Override
    public void edit(T view, S model, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        addCancelListener(view);
        addEditListener(view, model, sc, ec);
        view.getBtnOK().setText("Cập nhật");
    }

    private void addCancelListener(T view) {
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void addOkListener(T view, SuccessCallback sc, ErrorCallback ec) {
        view.getBtnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    addEntity(view);
                    view.dispose();
                    view.showMessage("Thêm " + view.getClassName() + " mới thành công!");
                    sc.onSuccess();
                } catch (Exception exception) {
                    ec.onError(exception);
                }
            }
        });
    }

    private void addEditListener(T view, S model, SuccessCallback sc, ErrorCallback ec) {
        view.getBtnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    editEntity(view, model);
                    view.dispose();
                    view.showMessage("Sửa " + model.getClassName() + " thành công!");
                    sc.onSuccess();
                } catch (Exception ex) {
                    ec.onError(ex);
                }
            }
        });
    }

    protected abstract void addEntity(T view) throws Exception;

    protected abstract void editEntity(T view, S model) throws Exception;
    
    protected JFrame getPreviousView() {
        return previousView;
    }

    protected void setPreviousView(JFrame previousView) {
        this.previousView = previousView;
    }
}
