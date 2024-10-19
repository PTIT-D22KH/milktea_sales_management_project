/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.TableDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Table;
import utils.TableStatus;
import views.popup.TablePopupView;

/**
 *
 * @author namle
 */
public class TablePopupController extends PopupController<TablePopupView, Table>{
    private final TableDao tableDao;
    
    public TablePopupController() {
        this.tableDao = new TableDao();
    }

    public TablePopupController(TableDao tableDao) {
        this.tableDao = tableDao;
    }
    
    
    @Override
    protected void addEntity(TablePopupView view) throws Exception {
        String name = view.getTbNameField().getText();
        validateTableName(name);
        if (tableDao.findByName(name) != null) {
            throw new Exception("Tên bàn đã được sử dụng");
        }
        Table t = new Table();
        t.setName(name);
        t.setStatus(TableStatus.FREE);
        tableDao.save(t);
    }
    @Override
    protected void editEntity(TablePopupView view, Table t) throws Exception {
        String tableName = view.getTbNameField().getText();
        validateTableName(tableName);
        Table temp = tableDao.findByName(tableName);
        if (temp != null && temp.getTableId() != t.getTableId()) {
            throw new Exception("Tên bàn đã được sử dụng");
        }
        t.setName(tableName);
        tableDao.update(t);
    }
    @Override
    public void edit(TablePopupView view, Table table, SuccessCallback sc, ErrorCallback ec) {
        super.edit(view, table, sc, ec);
        view.getLbTitle().setText("Sửa bàn - " + table.getTableId());
        view.getTbNameField().setText(table.getName());
    }
    private void validateTableName(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("Vui lòng điền đủ thông tin");
        }
    }
}
