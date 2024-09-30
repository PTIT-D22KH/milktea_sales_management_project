/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.JOptionPane;

/**
 *
 * @author P51
 */
public class ErrorPopup {
    private static PrintStream ps;
    
    public static void show(Exception e) {
        try {
            if (ps == null) {
                ps = new PrintStream(new FileOutputStream("log.txt", true));
                
            }
            e.printStackTrace(ps);
            
            int choice = JOptionPane.showConfirmDialog(null, e.getLocalizedMessage() + "!\nXem chi tiết lỗi?", "Có lỗi xảy ra", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                String errorSummary = "";
                int numElement = 0;
                for (StackTraceElement element : e.getStackTrace()) {
                    if (numElement >= 20) {
                        errorSummary += "\t ... more";
                    } else {
                        errorSummary += "\t at " + element;
                        errorSummary += "\n";
                    }
                }
                JOptionPane.showMessageDialog(null, errorSummary, "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (Exception newException) {
            
        }
    }
}
