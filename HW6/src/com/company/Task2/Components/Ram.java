package com.company.Task2.Components;

public class Ram {
    String model, manufacturer;
    Type type;
    String capacity;

    public Ram(String model, String manufacturer, Type type, String capacity) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return type + " RAM " + capacity + " " + manufacturer + " " + model;
    }

    public enum Type {
        DDR,
        DDR2,
        DDR3,
        DDR4
    }
}
