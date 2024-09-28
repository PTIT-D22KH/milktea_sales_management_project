/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import utils.DatabaseConnector;
import java.sql.Connection;

/**
 *
 * @author P51
 */
public class Main {
    public static void main(String[] args) {
        Connection conn = DatabaseConnector.getInstance().getConn();
        System.out.println("Ket noi csdl thanh cong!");
    }
}
