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
public abstract class PopupController<T extends JFrame & PopupView, M extends Model> implements BasePopupController<T, M> {
    protected JFrame previousView;

    @Override
    public void add(T view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
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

    @Override
    public void edit(T view, M model, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                view.dispose();
            }
        });

        view.getBtnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    editEntity(view, model);
                    view.dispose();
                    view.showMessage("Sửa " + model.getClassName() +" thành công!");
                    sc.onSuccess();
                } catch (Exception ex) {
                    ec.onError(ex);
                }
            }
        });
        view.getBtnOK().setText("Cập nhật");
    }

    protected abstract void addEntity(T view) throws Exception;

    protected abstract void editEntity(T view, M model) throws Exception;
}
