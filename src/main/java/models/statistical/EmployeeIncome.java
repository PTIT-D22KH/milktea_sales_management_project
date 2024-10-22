/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.statistical;

import java.sql.Date;
import models.Employee;

/**
 *
 * @author P51
 */
public class EmployeeIncome {
    private Employee employee;
    private int totalIncome;
    private int totalOrder;
    private Date date;

    public EmployeeIncome(Employee employee, int totalIncome, int totalOrder) {
        this.employee = employee;
        this.totalIncome = totalIncome;
        this.totalOrder = totalOrder;
    }

    public EmployeeIncome(int totalIncome, int totalOrder, Date date) {
        this.totalIncome = totalIncome;
        this.totalOrder = totalOrder;
        this.date = date;
    }

    public EmployeeIncome(Employee employee, int totalIncome, Date date) {
        this.employee = employee;
        this.totalIncome = totalIncome;
        this.date = date;
    }

    public boolean equalDate(Date date) {
        return date.equals(this.date);
    }
    @Override
    public String toString() {
        return "EmployeeIncome{" + "employee=" + employee + ", totalIncome=" + totalIncome + ", totalOrder=" + totalOrder + ", date=" + date + '}';
    }
}
