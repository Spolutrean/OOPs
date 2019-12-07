package com.company.Task2;

import com.company.Task2.Components.*;

public interface Builder {
    void reset();
    void setMotherboard(MotherBoard motherboard);
    void setProcessor(Processor processor);
    void setDisk(Disk disk);
    void setRAM(Ram ram);
    void setVideoCard(VideoCard videoCard);
    void setFluidCooler(FluidCooler fluidCooler);
}
