package com.epam.storozhuk.strategy.impl;

import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.services.Reader;
import com.epam.storozhuk.services.impl.KeyboardReader;
import com.epam.storozhuk.strategy.ProductGenerator;

import java.math.BigDecimal;

public class UserInputProductGenerator implements ProductGenerator {
    private final static String INCORRECT_INPUT_MESSAGE = "Incorrect informational input. Try again";
    private static final int HARDWARE_FIELDS_COUNT = 5;
    private static final int INTEL_FIELDS_COUNT = 7;
    private Reader reader;

    public UserInputProductGenerator() {
        reader = new KeyboardReader();
    }

    @Override
    public Hardware createCPU() {
        String answer[];
        CPU cpu;
        int valuesIndexesCounter = 0;
        System.out.println("Enter the cores count, frequency, price, product article and country of production " +
                "(as in: 0, 0, 1, etc)");
        answer = reader.nextLine().split(", ");
        if (answer.length != HARDWARE_FIELDS_COUNT) {
            System.err.println(INCORRECT_INPUT_MESSAGE);
            return createCPU();
        }
        cpu = new CPU(Integer.parseInt(answer[valuesIndexesCounter++]), Integer.parseInt(answer[valuesIndexesCounter++]),
                new BigDecimal(answer[valuesIndexesCounter++]), answer[valuesIndexesCounter++], answer[valuesIndexesCounter++]);
        return cpu;
    }

    @Override
    public Hardware createIntel() {
        String answer[];
        Intel intel;
        int valuesIndexesCounter = 0;
        System.out.println("Enter the cores count, frequency, price, product article, country of production, " +
                "generation and model (as in: 0, 0, 1, etc)");
        answer = reader.nextLine().split(", ");
        if (answer.length != INTEL_FIELDS_COUNT) {
            System.err.println(INCORRECT_INPUT_MESSAGE);
            return createIntel();
        }
        intel = new Intel(Integer.parseInt(answer[valuesIndexesCounter++]), Integer.parseInt(answer[valuesIndexesCounter++]),
                new BigDecimal(answer[valuesIndexesCounter++]), answer[valuesIndexesCounter++], answer[valuesIndexesCounter++],
                answer[valuesIndexesCounter++], answer[valuesIndexesCounter++]);
        return intel;
    }

    @Override
    public Hardware createVideoCard() {
        VideoCard videoCard;
        String[] answer;
        int valuesIndexesCounter = 0;
        System.out.println("Enter the memory size, bus memory, price, product article and country of production " +
                "(as in: 0, 0, 1, etc)");
        answer = reader.nextLine().split(", ");
        if (answer.length != HARDWARE_FIELDS_COUNT) {
            System.err.println(INCORRECT_INPUT_MESSAGE);
            return createVideoCard();
        }
        videoCard = new VideoCard(Integer.parseInt(answer[valuesIndexesCounter++]), Integer.parseInt(answer[valuesIndexesCounter++]),
                new BigDecimal(answer[valuesIndexesCounter++]), answer[valuesIndexesCounter++], answer[valuesIndexesCounter++]);
        return videoCard;
    }
}
