/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import controllers.TimeCounterController;

/**
 *
 * @author P51
 */
public class ShutdownHook extends Thread{
    @Override
    
    public void run() {
        System.out.println("Chuong trinh dung dot ngot!");
        try {
            TimeCounterController.stop();
            if (SessionManager.getSession() != null) {
                System.out.println("Ket thuc phien lam viec cua " + SessionManager.getSession().getEmployee().getName());
                SessionManager.update();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
