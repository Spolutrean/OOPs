package com.company.Task2;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        ComputerBuilder computerBuilder = new ComputerBuilder();
        DescriptionBuilder descriptionBuilder = new DescriptionBuilder();
        manager.createTopComputer(computerBuilder);
        manager.createTopComputer(descriptionBuilder);
        Computer computer = computerBuilder.getResult();
        Description description = descriptionBuilder.getResult();
    }
}
