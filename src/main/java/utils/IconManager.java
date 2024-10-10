/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author P51
 */
public class IconManager {
    private static String resourcePath = "/";
    private static String iconPath;
    
    public IconManager() {
        iconPath = resourcePath + "icons/";
    }
    public Icon getIcon(String name) {
        return new ImageIcon(getClass().getResource(iconPath + name));
    }
}
