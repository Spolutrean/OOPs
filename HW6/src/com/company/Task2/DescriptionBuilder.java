package com.company.Task2;

import com.company.Task2.Components.*;

public class DescriptionBuilder implements Builder {
    private Description description = new Description();

    @Override
    public Builder reset() {
        description = new Description();
        return this;
    }

    @Override
    public Builder setMotherboard(MotherBoard motherboard) {
        description.addDescriptionPart(motherboard.toString());
        return this;
    }

    @Override
    public Builder setProcessor(Processor processor) {
        description.addDescriptionPart(processor.toString());
        return this;
    }

    @Override
    public Builder setDisk(Disk disk) {
        description.addDescriptionPart(disk.toString());
        return this;
    }

    @Override
    public Builder setRAM(Ram ram) {
        description.addDescriptionPart(ram.toString());
        return this;
    }

    @Override
    public Builder setVideoCard(VideoCard videoCard) {
        description.addDescriptionPart(videoCard.toString());
        return this;
    }

    @Override
    public Builder setFluidCooler(FluidCooler fluidCooler) {
        description.addDescriptionPart(fluidCooler.toString());
        return this;
    }

    public Description getResult() {
        return description;
    }

}
