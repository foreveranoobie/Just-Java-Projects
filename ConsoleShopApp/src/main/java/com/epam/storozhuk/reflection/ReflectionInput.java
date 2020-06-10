package com.epam.storozhuk.reflection;

import com.epam.storozhuk.entities.Hardware;

import java.math.BigDecimal;

public interface ReflectionInput {
    Hardware createCPU();

    Hardware createIntel();

    Hardware createVideoCard();
}
