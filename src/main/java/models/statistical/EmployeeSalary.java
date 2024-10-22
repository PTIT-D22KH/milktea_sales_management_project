/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.statistical;

import models.Employee;

/**
 *
 * @author P51
 */
public class EmployeeSalary {
    private Employee employee;
    private int quantity;
    private int bonus;
    private int total;

    public EmployeeSalary(Employee employee, int quantity, int bonus) {
        this.employee = employee;
        this.quantity = quantity;
        this.bonus = bonus;
        this.total = calTotal();
    }
    
    private int calTotal() {
        return employee.getSalary() + bonus;
    }

    @Override
    public String toString() {
        return "EmployeeSalary{" + "employee=" + employee + ", quantity=" + quantity + ", bonus=" + bonus + ", total=" + total + '}';
    }
    
    
}
