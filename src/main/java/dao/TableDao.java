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
        String query = "SELECT * FROM `table`";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Table table = Table.getFromResultSet(rs);
                tables.add(table);
            }
        }
        return tables;
    }

    @Override
    public void save(Table t) throws SQLException {
        validate(t);
        String query = "INSERT INTO `table` (`name`, `status`) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setTableParameters(stmt, t);
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Table t) throws SQLException {
        validate(t);
        String query = "UPDATE `table` SET `name` = ?, `status` = ? WHERE `table`.`tableId` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setTableParameters(stmt, t);
            stmt.setInt(3, t.getTableId());
            int rowsAffected = stmt.executeUpdate();
//            if (rowsAffected == 0) {
//                throw new SQLException("Không tìm thấy bàn với ID " + t.getTableId());
//            }
        }
    }

    @Override
    public void delete(Table t) throws SQLException {
        if (t == null) {
            throw new SQLException("Table rỗng");
        }
        deleteById(t.getTableId());
    }

    @Override
    public void deleteById(int id) throws SQLException {
        if (id <= 0) {
            throw new SQLException("TableId phải > 0");
        }
        String query = "DELETE FROM `table` WHERE `table`.`tableId` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
//            if (rowsAffected == 0) {
//                throw new SQLException("Không tìm thấy bàn với ID " + id);
//            }
        }
    }

    public Table findByName(String name) throws SQLException {
        if (name == null || name.trim().isEmpty()) {
            throw new SQLException("Tên bàn không được null/trống");
        }
        String query = "SELECT * FROM `table` WHERE `name` = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Table.getFromResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Table> searchByKey(String key, String word) throws SQLException {
        if (key == null || word == null || key.trim().isEmpty() || word.trim().isEmpty()) {
            throw new SQLException("Từ khoá không được null/trống");
        }
        ArrayList<Table> tables = new ArrayList<>();
        String query = "SELECT * FROM `table` WHERE `" + key + "` LIKE ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, "%" + word + "%");
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Table table = Table.getFromResultSet(rs);
                    tables.add(table);
                }
            }
        }
        return tables;
    }

    @Override
    public Table getById(int id) throws SQLException {
        if (id <= 0) {
            throw new SQLException("TableId phải > 0");
        }
        String query = "SELECT * FROM `table` WHERE `tableId` = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Table.getFromResultSet(result);
                }
            }
        }
        return null;
    }

    private void setTableParameters(PreparedStatement stmt, Table t) throws SQLException {
        stmt.setString(1, t.getName());
        stmt.setString(2, t.getStatus().getId());
    }

    @Override
    protected void validate(Table t) throws SQLException {
        if (t == null) {
            throw new SQLException("Table rỗng");
        }
        if (t.getName() == null || t.getName().trim().isEmpty()) {
            throw new SQLException("Tên bàn không được null/trống");
        }
        if (t.getStatus() == null) {
            throw new SQLException("Trạng thái bàn không được null");
        }
    }
}
