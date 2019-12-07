package com.company.Task1;

public class Product {
    public int cost;
    public Size size;
    public Type type;

    public Product(int cost, Size size, Type type) {
        this.cost = cost;
        this.size = size;
        this.type = type;
    }

    public enum Type {
        jeans,
        shirt,
        tie
    }

    public enum Size {
        S,
        M,
        L
    }
}
