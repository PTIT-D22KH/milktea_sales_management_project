/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;

/**
 *
 * @author P51
 */
/**
 * Utility class for managing sidebar colors.
 * Provides colors for different states of sidebar menu items.
 */
public class SidebarColor {
    private static final Color ACTIVE_COLOR_LEVEL_0 = Color.decode("#F5CBA7");
    private static final Color ACTIVE_COLOR_LEVEL_1 = Color.decode("#EAFAF1");
    private static final Color INACTIVE_COLOR_LEVEL_0 = Color.decode("#D5DBDB");
    private static final Color INACTIVE_COLOR_LEVEL_1 = Color.decode("#D5DBDB");
    private static final Color HOVER_COLOR_LEVEL_0 = Color.decode("#AED6F1");
    private static final Color HOVER_COLOR_LEVEL_1 = Color.decode("#AED6F1");
    /**
     * Returns the active color based on the level.
     * 
     * @param level the level of the menu item
     * @return the active color
     */
    public static Color getActiveColor(int level) {
        switch (level) {
            case 0:
                return ACTIVE_COLOR_LEVEL_0;
            case 1:
                return ACTIVE_COLOR_LEVEL_1;
            default:
                return ACTIVE_COLOR_LEVEL_0;
        }
    }

    /**
     * Returns the inactive color based on the level.
     * 
     * @param level the level of the menu item
     * @return the inactive color
     */
    public static Color getInactiveColor(int level) {
        switch (level) {
            case 0:
                return INACTIVE_COLOR_LEVEL_0;
            case 1:
                return INACTIVE_COLOR_LEVEL_1;
            default:
                return INACTIVE_COLOR_LEVEL_0;
        }
    }

    /**
     * Returns the hover color based on the level.
     * 
     * @param level the level of the menu item
     * @return the hover color
     */
    public static Color getHoverColor(int level) {
        switch (level) {
            case 0:
                return HOVER_COLOR_LEVEL_0;
            case 1:
                return HOVER_COLOR_LEVEL_1;
            default:
                return HOVER_COLOR_LEVEL_0;
        }
    }
}
