package com.epam.storozhuk.reflection.impl;

import com.epam.storozhuk.annotation.UserFriendlyNaming;
import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.reflection.ReflectionInput;
import com.epam.storozhuk.services.Reader;
import com.epam.storozhuk.services.impl.KeyboardReader;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public class UserReflectionInput implements ReflectionInput {
    private Reader reader;
    private ResourceBundle vocabulary;
    private Map<String, Supplier<Object>> createFieldOfType;

    public UserReflectionInput() {
        reader = new KeyboardReader();
        createFieldOfType = initMapCreateFieldOfType();
    }

    public UserReflectionInput(ResourceBundle vocabulary) {
        reader = new KeyboardReader();
        createFieldOfType = initMapCreateFieldOfType();
        this.vocabulary = vocabulary;
    }

    public Hardware createCPU() {
        CPU cpu = new CPU();
        Field[] fields;
        String enterText = vocabulary.getString("Enter");
        for (Class cpuClass = cpu.getClass(); !cpuClass.getSimpleName().equals("Object"); ) {
            fields = cpuClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(UserFriendlyNaming.class)) {
                    field.setAccessible(true);
                    try {
                        System.out.println(enterText + " " + vocabulary.getString(field.getAnnotation(UserFriendlyNaming.class).userFriendlyName()) + ": ");
                        field.set(cpu, createFieldOfType.get(field.getType().getSimpleName()).get());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            cpuClass = cpuClass.getSuperclass();
        }
        return cpu;
    }

    public Hardware createIntel() {
        Intel intel = new Intel();
        Field[] fields;
        String enterText = vocabulary.getString("Enter");
        for (Class intelClass = intel.getClass(); !intelClass.getSimpleName().equals("Object"); ) {
            fields = intelClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(UserFriendlyNaming.class)) {
                    field.setAccessible(true);
                    try {
                        System.out.println(enterText + " " + vocabulary.getString(field.getAnnotation(UserFriendlyNaming.class).userFriendlyName()) + ": ");
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
        String enterText = vocabulary.getString("Enter");
        for (Class videoCardClass = videoCard.getClass(); !videoCardClass.getSimpleName().equals("Object"); ) {
            fields = videoCardClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(UserFriendlyNaming.class)) {
                    field.setAccessible(true);
                    try {
                        System.out.println(enterText + " " + vocabulary.getString(field.getAnnotation(UserFriendlyNaming.class).userFriendlyName()) + ": ");
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
        return reader.nextLine();
    }

    private BigDecimal createBigDecimal() {
        return new BigDecimal(reader.nextLine());
    }

    private int createInt() {
        return Integer.parseInt(reader.nextLine());
    }

    private Map<String, Supplier<Object>> initMapCreateFieldOfType() {
        Map<String, Supplier<Object>> returnTypes = new HashMap<>();
        returnTypes.put("int", this::createInt);
        returnTypes.put("BigDecimal", this::createBigDecimal);
        returnTypes.put("String", this::createString);
        return returnTypes;
    }

    public void setVocabulary(ResourceBundle vocabulary) {
        this.vocabulary = vocabulary;
    }
}
