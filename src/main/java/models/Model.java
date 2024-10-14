/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author P51
 */
public abstract class Model {
    
    public abstract String toString();
    
    public abstract Object[] toRowTable();
    
    public String getClassName() {
        return "";
    }
}
