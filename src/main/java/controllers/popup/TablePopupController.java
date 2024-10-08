/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.TableDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import models.Table;
import utils.TableStatus;
import views.popup.TablePopupView;

/**
 *
 * @author namle
 */
public class TablePopupController {
    private TableDao tableDao = new TableDao();
    private JFrame previousView;

    private void addTable(TablePopupView view) throws Exception {
        String name = view.getTbNameField().getText();
        if (name.isEmpty()) {
            throw new Exception("Vui lòng điền đủ thông tin");
        }
        if (tableDao.findByName(name) != null) {
            throw new Exception("Tên bàn đã được sử dụng");
        }
        Table t = new Table();
        t.setName(name);
        t.setStatus(TableStatus.FREE);
        tableDao.save(t);
    }

    private void editTable(TablePopupView view, Table t) throws Exception {
        String tableName = view.getTbNameField().getText();
        if (tableName.isEmpty()) {
            throw new Exception("Điền tên bàn");
        }
        Table temp = tableDao.findByName(tableName);
        if (temp != null && temp.getTableId() != t.getTableId()) {
            throw new Exception("Tên bàn đã được sử dụng");
        }
        t.setName(tableName);
        tableDao.update(t);
    }

    public void add(TablePopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getBtnOK().addActionListener(evt -> {
            try {
                addTable(view);
                view.dispose();
                view.showMessage("Thêm bàn thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void edit(TablePopupView view, Table table, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getLbTitle().setText("Sửa bàn - " + table.getTableId());
        view.getTbNameField().setText(table.getName());
        view.getBtnOK().setText("Cập nhật");
        view.getBtnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    editTable(view, table);
                    view.dispose();
                    view.showMessage("Sửa bàn thành công!");
                    sc.onSuccess();
                } catch (Exception ex) {
                    ec.onError(ex);
                }
            }
        });

    }
}
