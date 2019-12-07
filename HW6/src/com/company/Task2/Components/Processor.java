package com.company.Task2.Components;

public class Processor {
    String model, manufacturer;
    double frequency;

    public Processor(String model, String manufacturer, double frequency) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Processor: " + manufacturer + " " + model + " " + frequency + " MGhz";
    }
}
