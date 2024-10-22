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
public class WorkingMinus {
    private Employee employee;
    private Date date;
    private int minus;

    public WorkingMinus(Employee employee, Date date, int minus) {
        this.employee = employee;
        this.date = date;
        this.minus = minus;
    }

    @Override
    public String toString() {
        return "WorkingMinus{" + "employee=" + employee + ", date=" + date + ", minus=" + minus + '}';
    }
    
    
}
