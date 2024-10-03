/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import static utils.ShipmentStatus.values;

/**
 *
 * @author DELL
 */
public enum ShipmentStatus {
    TOPAY("topay", "Chờ xác nhận"),
    TOSHIP("toship", "Chờ lấy hàng"),
    TORECEIVE("toreceive", "Đang giao"),
    COMPLETED("completed", "Hoàn thành"),
    CANCELLED("cancelled", "Đã hủy"),;
    private String id, name;

    ShipmentStatus(String id, String name) {
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

    public static ShipmentStatus getById(String id) {
        for (ShipmentStatus e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return TOPAY;
    }

    public static ShipmentStatus getByName(String name) {
        for (ShipmentStatus e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return TOPAY;
    }
}