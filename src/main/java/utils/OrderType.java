/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import static utils.OrderType.values;

/**
 *
 * @author DELL
 */
public enum OrderType {
    LOCAL("local", "Tại quán"),
    ONLINE("online", "Đặt hàng");
    private String id, name;

    OrderType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static OrderType getById(String id) {
        for (OrderType e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return LOCAL;
    }

    public static OrderType getByName(String name) {
        for (OrderType e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return LOCAL;
    }

}

