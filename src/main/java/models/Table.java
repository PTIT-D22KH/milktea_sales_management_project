/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import utils.TableStatus;

/**
 *
 * @author DELL
 */
public class Table extends Model{
    private int tableId;
    private String name;
    private TableStatus status;

  
    public Table() {
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }
    
    public static Table getFromResultSet(ResultSet rs) throws SQLException {
        Table t = new Table();
        t.setTableId(rs.getInt("tableId"));
        t.setName(rs.getNString("name"));
        t.setStatus(TableStatus.getById(rs.getNString("status")));
        return t;
    }

    @Override
    public String toString() {
      return name;
    }


    @Override
    public Object[] toRowTable() {
        return new Object[] {
            tableId, name, status.getName()
        };
        
    }

    @Override
    public String getClassName() {
        return "bàn";
    }
}
