package com.epam.storozhuk.strategy;

import com.epam.storozhuk.entities.Hardware;

public interface ProductGenerator {
    Hardware createCPU();

    Hardware createIntel();

    Hardware createVideoCard();
}
