package com.company.Task2;

import com.company.Task2.Components.*;

public class Manager {
    public void createTopComputer(Builder builder) {
        builder.reset();
        builder.setMotherboard(new MotherBoard("MPG Z390 GAMING EDGE AC", "MSI"))
                .setProcessor(new Processor("Core™ i7-9700KF", "Intel", 3600))
                .setDisk(new Disk("BarraCude", "Seagate", Disk.Type.HDD, "2TB"))
                .setRAM(new Ram("Predator RGB", "HyperX", Ram.Type.DDR4, "16GB"))
                .setVideoCard(new VideoCard("GeForce RTX 2080 SUPER GAMING X TRIO", "MSI"))
                .setFluidCooler(new FluidCooler("WaterCooling 240 RGB", "HYPERPC"));
    }

    public void createCheapComputer(Builder builder) {
        builder.reset();
        builder.setMotherboard(new MotherBoard("B350ET2 Ver. 6.x", "Biostar"))
                .setProcessor(new Processor("Ryzen 5 1400", "AMD", 3200))
                .setDisk(new Disk("TS256GMTE110S", "Transcend", Disk.Type.SSD, "256GB"))
                .setRAM(new Ram("CT8G4DFS824A", "Crucial", Ram.Type.DDR4, "8GB"))
                .setVideoCard(new VideoCard("Radeon RX 570", "GIGABYTE"));
    }
}
