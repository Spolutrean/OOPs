package com.company.Task2;

import com.company.Task2.Components.*;

public class ComputerBuilder implements Builder {
    private Computer computer = new Computer();

    @Override
    public void reset() {
        computer = new Computer();
    }

    @Override
    public void setMotherboard(MotherBoard motherboard) {
        computer.setMotherBoard(motherboard);
    }

    @Override
    public void setProcessor(Processor processor) {
        computer.setProcessor(processor);
    }

    @Override
    public void setDisk(Disk disk) {
        computer.setDisk(disk);
    }

    @Override
    public void setRAM(Ram ram) {
        computer.setRam(ram);
    }

    @Override
    public void setVideoCard(VideoCard videoCard) {
        computer.setVideoCard(videoCard);
    }

    @Override
    public void setFluidCooler(FluidCooler fluidCooler) {
        computer.setFluidCooler(fluidCooler);
    }

    public Computer getResult() {
        return computer;
    }
}
