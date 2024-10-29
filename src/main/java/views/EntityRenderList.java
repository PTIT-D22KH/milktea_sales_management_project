/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import models.Model;

/**
 *
 * @author P51
 */
public abstract class EntityRenderList <E extends Model> extends javax.swing.JPanel implements ListCellRenderer<E>{

    @Override
    public abstract Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected, boolean cellHasFocus);
    
}
