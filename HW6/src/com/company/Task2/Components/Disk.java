package com.company.Task2.Components;

public class Disk {
    String model, manufacturer;
    Type type;
    String capacity;

    public Disk(String model, String manufacturer, Type type, String capacity) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return type + " disk " + capacity + " " + manufacturer + " " + model;
    }

    public enum Type {
        SSD,
        HDD
    }
}
