package com.epam.storozhuk.strategy.impl;

import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.strategy.ProductGenerator;

import java.math.BigDecimal;
import java.util.Random;

public class RandomProductGenerator implements ProductGenerator {
    private static final String[] RANDOM_COUNTRIES = {"China", "Japan", "Taiwan", "South Korea",
            "USA", "India", "Singapore", "Malaysia"};
    private static final String[] INTEL_CPU_GENERATIONS = {"Coffee Lake", "Arrandale", "Broadwell", "Sandy Bridge", "Ivy Bridge",
            "Haswell", "Skylake"};
    private static final String[] INTEL_CPU_MODELS = {"Pentium", "Celeron", "Atom", "i3", "i5", "i7", "i9", "Xeon"};
    private static final int[] VIDEO_MEMORY_CAPACITY = {64, 128, 256, 512, 1024, 2048, 4096, 8192};
    private static final int[] VIDEO_BUS_MEMORY = {64, 128, 192, 256, 384, 512};
    private static final int CPU_CORES_MAX_COUNT = 12;
    private static final int CPU_MIN_FREQUENCY = 2001;
    private static final int CPU_MAX_RANGED_FREQUENCY = 2000;
    private static final int PRODUCT_HIGHEST_PRICE = 1000;
    private static final int PRODUCT_LOWEST_PRICE = 70;
    private static final int PRODUCT_ARTICLE_NUMBERS_RANGE = 1000000;
    private Random random;

    public RandomProductGenerator() {
        random = new Random();
    }

    @Override
    public Hardware createCPU() {
        int coresCount = random.nextInt(CPU_CORES_MAX_COUNT) + 1;
        int frequency = random.nextInt(CPU_MIN_FREQUENCY) + CPU_MAX_RANGED_FREQUENCY;
        BigDecimal price = BigDecimal.valueOf(Math.random() * (PRODUCT_HIGHEST_PRICE - PRODUCT_LOWEST_PRICE + 1) + PRODUCT_LOWEST_PRICE);
        String article = "simpleCPU" + random.nextInt(PRODUCT_ARTICLE_NUMBERS_RANGE);
        String country = RANDOM_COUNTRIES[random.nextInt(RANDOM_COUNTRIES.length)];
        return new CPU(coresCount, frequency, price, article, country);
    }

    @Override
    public Hardware createIntel() {
        int coresCount = random.nextInt(CPU_CORES_MAX_COUNT) + 1;
        int frequency = random.nextInt(CPU_MIN_FREQUENCY) + CPU_MAX_RANGED_FREQUENCY;
        BigDecimal price = BigDecimal.valueOf(Math.random() * (PRODUCT_HIGHEST_PRICE - PRODUCT_LOWEST_PRICE + 1) + PRODUCT_LOWEST_PRICE);
        String article = "intelCPU" + random.nextInt(PRODUCT_ARTICLE_NUMBERS_RANGE);
        String country = RANDOM_COUNTRIES[random.nextInt(RANDOM_COUNTRIES.length)];
        String generation = INTEL_CPU_GENERATIONS[random.nextInt(INTEL_CPU_GENERATIONS.length)];
        String model = INTEL_CPU_MODELS[random.nextInt(INTEL_CPU_MODELS.length)];
        return new Intel(coresCount, frequency, price, article, country, generation, model);
    }

    @Override
    public Hardware createVideoCard() {
        int memorySize = VIDEO_MEMORY_CAPACITY[random.nextInt(VIDEO_MEMORY_CAPACITY.length)];
        int busMemory = VIDEO_BUS_MEMORY[random.nextInt(VIDEO_BUS_MEMORY.length)];
        BigDecimal price = BigDecimal.valueOf(Math.random() * (PRODUCT_HIGHEST_PRICE - PRODUCT_LOWEST_PRICE + 1) + PRODUCT_LOWEST_PRICE);
        String article = "VideoCard" + random.nextInt(PRODUCT_ARTICLE_NUMBERS_RANGE);
        String country = RANDOM_COUNTRIES[random.nextInt(RANDOM_COUNTRIES.length)];
        return new VideoCard(memorySize, busMemory, price, article, country);
    }
}
