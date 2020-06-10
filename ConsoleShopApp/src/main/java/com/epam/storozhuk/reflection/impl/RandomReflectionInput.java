package com.epam.storozhuk.reflection.impl;

import com.epam.storozhuk.annotation.UserFriendlyNaming;
import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.reflection.ReflectionInput;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class RandomReflectionInput implements ReflectionInput {
    private static final int PRODUCT_HIGHEST_PRICE = 1000;
    private static final int PRODUCT_LOWEST_PRICE = 70;
    private Random random;
    private Map<String, Supplier<Object>> createFieldOfType;

    public RandomReflectionInput() {
        random = new Random();
        createFieldOfType = initMapCreateFieldOfType();
    }

    public Hardware createCPU() {
        CPU cpu = new CPU();
        Field[] fields;
        for (Class classCPU = cpu.getClass(); !classCPU.getSimpleName().equals("Object"); ) {
            fields = classCPU.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(UserFriendlyNaming.class)) {
                    field.setAccessible(true);
                    try {
                        field.set(cpu, createFieldOfType.get(field.getType().getSimpleName()).get());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            classCPU = classCPU.getSuperclass();
        }
        return cpu;
    }

    public Hardware createIntel() {
        Intel intel = new Intel();
        Field[] fields;
        for (Class intelClass = intel.getClass(); !intelClass.getSimpleName().equals("Object"); ) {
            fields = intelClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(UserFriendlyNaming.class)) {
                    field.setAccessible(true);
                    try {
                        field.set(intel, createFieldOfType.get(field.getType().getSimpleName()).get());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            intelClass = intelClass.getSuperclass();
        }
        return intel;
    }

    public Hardware createVideoCard() {
        VideoCard videoCard = new VideoCard();
        Field[] fields;
        for (Class videoCardClass = videoCard.getClass(); !videoCardClass.getSimpleName().equals("Object"); ) {
            fields = videoCardClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(UserFriendlyNaming.class)) {
                    field.setAccessible(true);
                    try {
                        field.set(videoCard, createFieldOfType.get(field.getType().getSimpleName()).get());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            videoCardClass = videoCardClass.getSuperclass();
        }
        return videoCard;
    }

    private String createString() {
        return String.valueOf(random.nextInt(1000) + 1);
    }

    private BigDecimal createBigDecimal() {
        return BigDecimal.valueOf(Math.random() * (PRODUCT_HIGHEST_PRICE - PRODUCT_LOWEST_PRICE + 1) + PRODUCT_LOWEST_PRICE);
    }

    private Integer createInt() {
        return random.nextInt(1000) + 1;
    }

    private Map<String, Supplier<Object>> initMapCreateFieldOfType() {
        Map<String, Supplier<Object>> returnTypes = new HashMap<>();
        returnTypes.put("int", this::createInt);
        returnTypes.put("BigDecimal", this::createBigDecimal);
        returnTypes.put("String", this::createString);
        return returnTypes;
    }
}
