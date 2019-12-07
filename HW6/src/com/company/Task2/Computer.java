package com.company.Task2;

import com.company.Task2.Components.*;

public class Computer {
    private Disk disk = null;
    private FluidCooler fluidCooler = null;
    private MotherBoard motherBoard = null;
    private Processor processor = null;
    private Ram ram = null;
    private VideoCard videoCard = null;

    public Disk getDisk() {
        return disk;
    }

    public FluidCooler getFluidCooler() {
        return fluidCooler;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public Processor getProcessor() {
        return processor;
    }

    public Ram getRam() {
        return ram;
    }

    public VideoCard getVideoCard() {
        return videoCard;
    }

    public void setDisk(Disk disk) {    
        this.disk = disk;
    }

    public void setFluidCooler(FluidCooler fluidCooler) {
        this.fluidCooler = fluidCooler;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public void setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
    }
}
