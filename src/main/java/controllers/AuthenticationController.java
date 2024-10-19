/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.EmployeeDao;
import javax.swing.JFrame;

/**
 *
 * @author P51
 * @param <T>
 */
public abstract class AuthenticationController<T extends JFrame> implements  AuthenticationBase{
    protected final T view;
    protected final EmployeeDao employeeDao;
    
    public AuthenticationController(T view) {
        this.view = view;
        this.employeeDao = new EmployeeDao();
        addEvent();
    }
    public AuthenticationController(T view, EmployeeDao employeeDao) {
        this.view = view;
        this.employeeDao = employeeDao;
        addEvent();
    }
    @Override
    public void showView(){
        view.setVisible(true);
    }
    protected abstract void addEvent();
}
