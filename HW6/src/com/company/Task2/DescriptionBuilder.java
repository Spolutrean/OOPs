package com.company.Task2;

import com.company.Task2.Components.*;

public class DescriptionBuilder implements Builder {
    private Description description = new Description();

    @Override
    public void reset() {
        description = new Description();
    }

    @Override
    public void setMotherboard(MotherBoard motherboard) {
        description.addDescriptionPart(motherboard.toString());
    }

    @Override
    public void setProcessor(Processor processor) {
        description.addDescriptionPart(processor.toString());
    }

    @Override
    public void setDisk(Disk disk) {
        description.addDescriptionPart(disk.toString());
    }

    @Override
    public void setRAM(Ram ram) {
        description.addDescriptionPart(ram.toString());
    }

    @Override
    public void setVideoCard(VideoCard videoCard) {
        description.addDescriptionPart(videoCard.toString());
    }

    @Override
    public void setFluidCooler(FluidCooler fluidCooler) {
        description.addDescriptionPart(fluidCooler.toString());
    }

    public Description getResult() {
        return description;
    }

}
