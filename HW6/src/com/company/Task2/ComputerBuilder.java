package com.company.Task2;

import com.company.Task2.Components.*;

public class ComputerBuilder implements Builder {
    private Computer computer = new Computer();

    @Override
    public Builder reset() {
        computer = new Computer();
        return this;
    }

    @Override
    public Builder setMotherboard(MotherBoard motherboard) {
        computer.setMotherBoard(motherboard);
        return this;
    }

    @Override
    public Builder setProcessor(Processor processor) {
        computer.setProcessor(processor);
        return this;
    }

    @Override
    public Builder setDisk(Disk disk) {
        computer.setDisk(disk);
        return this;
    }

    @Override
    public Builder setRAM(Ram ram) {
        computer.setRam(ram);
        return this;
    }

    @Override
    public Builder setVideoCard(VideoCard videoCard) {
        computer.setVideoCard(videoCard);
        return this;
    }

    @Override
    public Builder setFluidCooler(FluidCooler fluidCooler) {
        computer.setFluidCooler(fluidCooler);
        return this;
    }

    public Computer getResult() {
        if(computer.getDisk() != null && computer.getMotherBoard() != null && computer.getProcessor() != null && computer.getRam() != null) {
            return computer;
        } else {
            return null;
        }
    }
}
