package com.company.Task2.Components;

public class MotherBoard {
    String model, manufacturer;

    public MotherBoard(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "MotherBoard: " + manufacturer + " " + model;
    }
}
