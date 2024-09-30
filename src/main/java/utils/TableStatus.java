/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import static utils.TableStatus.values;

/**
 *
 * @author DELL
 */
public enum TableStatus {
    FREE("free", "Trống"),
    SERVING("serving", "Đang phục vụ"),
    RESERVING("reserving", "Đặt trước");
    private String id, name;
    

    TableStatus(String id, String name) {
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

    public static TableStatus getById(String id) {
        for (TableStatus e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return FREE;
    }

    public static TableStatus getByName(String name) {
        for (TableStatus e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return FREE;
    }

}
