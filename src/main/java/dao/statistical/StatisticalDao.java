/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.statistical;

import dao.EmployeeDao;
import java.sql.Connection;
import utils.DatabaseConnector;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import models.Employee;
import models.statistical.EmployeeIncome;
import models.statistical.EmployeeSalary;
import models.statistical.ProductIncome;
import models.statistical.WorkingDay;
import utils.OrderStatus;

/**
 *
 * @author P51
 */
public class StatisticalDao {
    private final Connection connection;
    private final EmployeeDao employeeDao;
    
    public StatisticalDao(){
        this.connection = DatabaseConnector.getInstance().getConn();
        this.employeeDao = new EmployeeDao();
    }
    /**
     * getTotalOrder methods overloading
     * @param start
     * @param end
     * @param employeeId
     * @return
     * @throws SQLException 
     */
    public int getTotalOrder(Timestamp start, Timestamp end, int employeeId) throws SQLException {
        String query = "SELECT COUNT(*) AS totalOrder FROM `order` WHERE status = ? AND orderDate >= ? AND orderDate <= ? AND employeeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setNString(1, OrderStatus.PAID.getId());
            statement.setTimestamp(2, start);
            statement.setTimestamp(3, end);
            statement.setInt(4, employeeId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalOrder");
            }
        }
        return 0;
    }
    //overloading
    public int getTotalOrder(Timestamp start, Timestamp end) throws SQLException {
        String query = "SELECT COUNT(*) AS totalOrder FROM `order` WHERE status = ? AND orderDate >= ? AND orderDate <= ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setNString(1, OrderStatus.PAID.getId());
            statement.setTimestamp(2, start);
            statement.setTimestamp(3, end);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalOrder");
            }
        }
        return 0;
    }

    /**
     * getTotalWorkingMinutes methods overloading
     * @param start
     * @param end
     * @return
     * @throws SQLException 
     */
    public int getTotalWorkingMinutes(Timestamp start, Timestamp end) throws SQLException {
        String query = "SELECT CEIL(SUM(TIME_TO_SEC(TIMEDIFF(endTime, startTime))) / 60) AS totalWorkingMinutes FROM `session` WHERE message = 'logout' AND DATE(startTime) >= DATE(?) AND DATE(endTime) <= DATE(?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, start);
            statement.setTimestamp(2, end);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalWorkingMinutes");
            }
        }
        return 0;
    }

    //overloading
    public int getTotalWorkingMinutes(Timestamp start, Timestamp end, int employeeId) throws SQLException {
        String query = "SELECT CEIL(SUM(TIME_TO_SEC(TIMEDIFF(endTime, startTime))) / 60) AS totalWorkingMinutes FROM `session` WHERE message = 'logout' AND DATE(startTime) >= DATE(?) AND DATE(endTime) <= DATE(?) AND employeeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, start);
            statement.setTimestamp(2, end);
            statement.setInt(3, employeeId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalWorkingMinutes");
            }
        }
        return 0;
    }

    /**
     * getTotalIncome methods overloading
     * @param start
     * @param end
     * @return
     * @throws SQLException 
     */
    public int getTotalIncome(Timestamp start, Timestamp end) throws SQLException {
        String query = "SELECT SUM(paidAmount) AS totalIncome FROM `order` WHERE status = ? AND DATE(orderDate) >= DATE(?) AND DATE(orderDate) <= DATE(?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setNString(1, OrderStatus.PAID.getId());
            statement.setTimestamp(2, start);
            statement.setTimestamp(3, end);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalIncome");
            }
        }
        return 0;
    }

    //overloading
    public int getTotalIncome(Timestamp start, Timestamp end, int employeeId) throws SQLException {
        String query = "SELECT SUM(paidAmount) AS totalIncome FROM `order` WHERE status = ? AND DATE(orderDate) >= DATE(?) AND DATE(orderDate) <= DATE(?) AND employeeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setNString(1, OrderStatus.PAID.getId());
            statement.setTimestamp(2, start);
            statement.setTimestamp(3, end);
            statement.setInt(4, employeeId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalIncome");
            }
        }
        return 0;
    }


    public ArrayList<EmployeeIncome> getListTotalIncomeByEmployee(Timestamp start, Timestamp end) throws SQLException {
        ArrayList<EmployeeIncome> incomes = new ArrayList<>();
        String query = "SELECT `employeeId`, SUM(paidAmount) AS totalIncome, COUNT(orderId) AS totalOrder FROM `order` WHERE status = ? AND DATE(orderDate) >= DATE(?) AND DATE(orderDate) <= DATE(?) GROUP BY `employeeId`  ORDER BY `totalIncome` DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setNString(1, OrderStatus.PAID.getId());
            statement.setTimestamp(2, start);
            statement.setTimestamp(3, end);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Employee employee = employeeDao.getById(rs.getInt("employeeId"));
                int totalIncome = rs.getInt("totalIncome");
                int totalOrder = rs.getInt("totalOrder");
                EmployeeIncome income = new EmployeeIncome(employee, totalIncome, totalOrder);
                incomes.add(income);
            }
        }
        return incomes;
    }

    /**
     * getListTotalIncomeByDate methods overloading
     * @param start
     * @param end
     * @return
     * @throws SQLException 
     */
    public ArrayList<EmployeeIncome> getListTotalIncomeByDate(Timestamp start, Timestamp end) throws SQLException {
        ArrayList<EmployeeIncome> incomes = new ArrayList<>();
        String query = "SELECT DATE(orderDate) AS orderDate, COUNT(orderId) AS totalOrder , SUM(paidAmount) AS totalIncome FROM `order` WHERE status = ? AND DATE(orderDate) >= DATE(?) AND DATE(orderDate) <= DATE(?) GROUP BY `orderDate` ORDER BY `orderDate` ASC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setNString(1, OrderStatus.PAID.getId());
            statement.setTimestamp(2, start);
            statement.setTimestamp(3, end);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int totalIncome = rs.getInt("totalIncome");
                int totalOrder = rs.getInt("totalOrder");
                Date date = rs.getDate("orderDate");
                EmployeeIncome income = new EmployeeIncome(totalIncome, totalOrder, date);
                incomes.add(income);
            }
        }
        return incomes;
    }

    //overloading
    public ArrayList<EmployeeIncome> getListTotalIncomeByDate(Timestamp start, Timestamp end, int employeeId) throws SQLException {
        ArrayList<EmployeeIncome> incomes = new ArrayList<>();
        String query = "SELECT DATE(orderDate) AS orderDate, SUM(paidAmount) AS totalIncome FROM `order` WHERE status = ? AND DATE(orderDate) >= DATE(?) AND DATE(orderDate) <= DATE(?) AND employeeId = ? GROUP BY orderDate ORDER BY `orderDate` ASC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setNString(1, OrderStatus.PAID.getId());
            statement.setTimestamp(2, start);
            statement.setTimestamp(3, end);
            statement.setInt(4, employeeId);
            ResultSet rs = statement.executeQuery();
            Employee e = employeeDao.getById(employeeId);
            while (rs.next()) {
                int totalIncome = rs.getInt("totalIncome");
                Date date = rs.getDate("orderDate");
                EmployeeIncome income = new EmployeeIncome(e, totalIncome, date);
                incomes.add(income);
            }
        }
        return incomes;
    }

    public int getTotalEmployee() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM employee";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public int getTotalCustomer() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM customer";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public ArrayList<ProductIncome> getQuantityItemByCategory(Timestamp start, Timestamp end, int category) throws SQLException {
        ArrayList<ProductIncome> itemProducts = new ArrayList<>();
        String query = "SELECT `name`,SUM(quantity) as sum "
                + "FROM `order_item`,`food_item`,`order` "
                + "WHERE `order_item`.`foodItemId`=food_item.foodItemId "
                + "AND `foodCategoryId`= ? "
                + "AND `order_item`.`orderId`= order.orderId "
                + "AND DATE(orderDate)>= DATE(?) "
                + "AND DATE(orderDate)<= DATE(?) "
                + "GROUP BY order_item.foodItemId "
                + "ORDER by sum DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, category);
            statement.setTimestamp(2, start);
            statement.setTimestamp(3, end);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("sum");
                String name = rs.getString("name");
                ProductIncome itemProduct = new ProductIncome(quantity, name);
                itemProducts.add(itemProduct);
            }
        }
        return itemProducts;
    }

    public ArrayList<ProductIncome> getQuantityItem(Timestamp start, Timestamp end) throws SQLException {
        ArrayList<ProductIncome> itemProducts = new ArrayList<>();
        String query = "SELECT `order_item`.`foodItemId`, `name`, SUM(quantity) as sum, (foodPrice*SUM(quantity)) as amount "
                + "FROM `order_item`,`food_item`,`order` "
                + "WHERE `order_item`.`foodItemId`=food_item.foodItemId "
                + "AND `order_item`.`orderId`= order.orderId "
                + "AND DATE(orderDate)>= DATE(?) "
                + "AND DATE(orderDate)<= DATE(?) "
                + "AND order.status = ? "
                + "GROUP BY `order_item`.`foodItemId`, name, foodPrice "
                + "ORDER by sum DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, start);
            statement.setTimestamp(2, end);
            statement.setNString(3, OrderStatus.PAID.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("sum");
                int amount = rs.getInt("amount");
                int id = rs.getInt("foodItemId");
                String name = rs.getString("name");
                ProductIncome itemProduct = new ProductIncome(quantity, amount, id, name);
                itemProducts.add(itemProduct);
            }
        }
        return itemProducts;
    }

    /**
     * getWorkingDays methods overloading with Date and Timestamp
     * @param start
     * @param end
     * @param employeeId
     * @return
     * @throws SQLException 
     */
    public WorkingDay getWorkingDays(Date start, Date end, int employeeId) throws SQLException {
        WorkingDay workingDays = new WorkingDay();
        String query = "SELECT DATE(startTime) AS startTime, DATE(endTime) AS endTime FROM `session` WHERE DATE(startTime) >= DATE(?) AND DATE(endTime) <= DATE(?) AND `employeeId` = ? ORDER BY `session`.`startTime` ASC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, start);
            statement.setDate(2, end);
            statement.setInt(3, employeeId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Date startTime = rs.getDate("startTime");
                Date endTime = rs.getDate("endTime");
                Date i = startTime;
                while (!i.after(endTime)) {
                    workingDays.addWorkDay(i);
                    i = new Date(i.getTime() + 24 * 60 * 60 * 1000); // Check ngày sau
                }
            }
        }
        return workingDays;
    }

    //overloading
    public WorkingDay getWorkingDays(Timestamp start, Timestamp end, int employeeId) throws SQLException {
        WorkingDay workingDays = new WorkingDay();
        String query = "SELECT DATE(startTime) AS startTime, DATE(endTime) AS endTime FROM `session` WHERE DATE(startTime) >= DATE(?) AND DATE(endTime) <= DATE(?) AND `employeeId` = ? ORDER BY `session`.`startTime` ASC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, start);
            statement.setTimestamp(2, end);
            statement.setInt(3, employeeId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Date startTime = rs.getDate("startTime");
                Date endTime = rs.getDate("endTime");
                Date i = startTime;
                while (!i.after(endTime)) {
                    workingDays.addWorkDay(i);
                    i = new Date(i.getTime() + 24 * 60 * 60 * 1000); // Check ngày sau
                }
            }
        }
        return workingDays;
    }

    public ArrayList<EmployeeSalary> getEmployeeSalaries(Timestamp start, Timestamp end) throws SQLException {
        ArrayList<EmployeeSalary> list = new ArrayList<>();
        String query = "SELECT `employeeId`, COUNT(orderId) as quantity, (COUNT(orderId) * 2000) as bonus FROM `order` GROUP BY `employeeId`";
        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                Employee employee = employeeDao.getById(rs.getInt("employeeId"));
                int quantity = rs.getInt("quantity");
                int bonus = rs.getInt("bonus");
                EmployeeSalary salaryEmployee = new EmployeeSalary(employee, quantity, bonus);
                list.add(salaryEmployee);
            }
        }
        return list;
    }
    
    
    
}
