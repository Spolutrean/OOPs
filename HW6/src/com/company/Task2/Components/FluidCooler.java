package com.company.Task2.Components;

public class FluidCooler {
    String model, manufacturer;

    public FluidCooler(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Fluid cooler: " + manufacturer + " " + model;
    }
}
