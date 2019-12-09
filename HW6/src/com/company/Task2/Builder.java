package com.company.Task2;

import com.company.Task2.Components.*;

public interface Builder {
    Builder reset();
    Builder setMotherboard(MotherBoard motherboard);
    Builder setProcessor(Processor processor);
    Builder setDisk(Disk disk);
    Builder setRAM(Ram ram);
    Builder setVideoCard(VideoCard videoCard);
    Builder setFluidCooler(FluidCooler fluidCooler);
}
