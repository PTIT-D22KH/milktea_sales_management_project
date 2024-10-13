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
 */
public abstract class AuthenticationController<T extends JFrame> {
    protected T view;
    protected EmployeeDao employeeDao;
    
    public AuthenticationController(T view, EmployeeDao employeeDao) {
        this.view = view;
        this.employeeDao = employeeDao;
        addEvent();
    }
//    public void setView(T view) {
//        this.view = view;
//    }
//    public T getView() {
//        return view;
//    }
    public void showView(){
        view.setVisible(true);
    }
    protected abstract void addEvent();
}
