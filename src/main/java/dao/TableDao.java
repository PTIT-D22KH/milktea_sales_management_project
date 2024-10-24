/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Table;

/**
 *
 * @author DELL
 */
public class TableDao extends Dao<Table> {

    @Override
    public ArrayList<Table> getAll() throws SQLException {
        ArrayList<Table> tables = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `table`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Table table = Table.getFromResultSet(rs);
            tables.add(table);
        }
        return tables;
    }


    @Override
    public void save(Table t) throws SQLException {
        if (t == null) {
            throw new SQLException("Table rỗng");
        }
        String query = "INSERT INTO `table` (`name`, `status`) VALUES (?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getName());
        stmt.setNString(2, t.getStatus().getId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Table t) throws SQLException {
        if (t == null) {
            throw new SQLException("Table rỗng");
        }
        String query = "UPDATE `table` SET `name` = ? , `status` = ? WHERE `table`.`tableId` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getName());
        stmt.setNString(2, t.getStatus().getId());
        stmt.setInt(3, t.getTableId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void delete(Table t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `table` WHERE `table`.`tableId` = ?");
        stmt.setInt(1, t.getTableId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `table` WHERE `table`.`tableId` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Table findByName(String name) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `table` WHERE `name` = '" + name + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Table t = Table.getFromResultSet(rs);
            return t;
        }
        return null;
    }

    public ArrayList<Table> searchByKey(String key, String word) throws SQLException {
        ArrayList<Table> tables = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `table` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Table table = Table.getFromResultSet(rs);
            tables.add(table);
        }
        return tables;
    }

    @Override
    public Table getById(int id) throws SQLException {
       Statement statement = conn.createStatement();
        String query = "SELECT * "
                + "FROM `table` "
                + "WHERE `tableId` = " + id;
        ResultSet result = statement.executeQuery(query);
        if (result.next()) {
            Table table = Table.getFromResultSet(result);
            return table;
        }
        return null;
    }
}

