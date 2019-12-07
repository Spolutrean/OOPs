package com.company.Task2.Components;

public class VideoCard {
    String model, manufacturer;

    public VideoCard(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Video card: " + manufacturer + " " + model;
    }
}
