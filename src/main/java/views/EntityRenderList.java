/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import models.Employee;
import models.Model;

/**
 *
 * @author P51
 */
public abstract class EntityRenderList <T extends Model> extends javax.swing.JPanel implements ListCellRenderer<T>{

    public abstract Component getListCellRendererComponent(JList<? extends T> list, T value, int index, boolean isSelected, boolean cellHasFocus);
    
}
