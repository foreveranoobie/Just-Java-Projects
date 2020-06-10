package com.epam.storozhuk.application;

import com.epam.storozhuk.command.CommandActions;
import com.epam.storozhuk.command.CommandContainer;
import com.epam.storozhuk.exceptions.ApplicationException;
import com.epam.storozhuk.services.Reader;
import com.epam.storozhuk.services.impl.KeyboardReader;
import com.epam.storozhuk.strategy.ProductGenerator;
import com.epam.storozhuk.strategy.impl.RandomProductGenerator;
import com.epam.storozhuk.strategy.impl.UserInputProductGenerator;
import com.epam.storozhuk.util.ProductSerializer;

import java.security.Key;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Application {
    private final static String RUSSIAN_LANGUAGE = "ru";
    private final static String ENGLISH_LANGUAGE = "en";

    public static void main(String... args) {
        setSerializingFilename();
        ApplicationContext applicationContext;
        try {
            applicationContext = new ApplicationContext();
        } catch (ApplicationException customException) {
            System.out.println("Error: " + customException.getMessage());
            return;
        }
        ProductGenerator productGenerator = getProductGenerator(applicationContext.getReaderService());
        applicationContext.setProductGenerator(productGenerator);
        String locale = setInputLanguage();
        applicationContext.setBundle(locale);
        showMenu(applicationContext);
    }

    private static void showMenu(ApplicationContext applicationContext) {
        CommandContainer commandContainer = new CommandContainer(applicationContext);
        String answerNumber;
        Reader keyboardReader = applicationContext.getReaderService();
        while (true) {
            Stream.of(CommandActions.values()).forEach(System.out::println);
            answerNumber = keyboardReader.nextLine();
            try {
                commandContainer.getCommand(answerNumber).execute();
            } catch (ApplicationException customException) {
                System.out.println("Error: " + customException.getMessage());
            }
        }
    }

    private static String setInputLanguage() {
        Reader keyboardReader = new KeyboardReader();
        System.out.println("Enter the bundle locale:\n1 - RU;\n2 - EN");
        int answer = keyboardReader.nextInt();
        if (answer == 1) {
            return RUSSIAN_LANGUAGE;
        } else {
            return ENGLISH_LANGUAGE;
        }
    }

    private static void setSerializingFilename() {
        System.out.println("Enter the filename to work with during serialization/deserialization");
        Reader keyboardReader = new KeyboardReader();
        String serializationFile = keyboardReader.nextLine();
        ProductSerializer.setFileName(serializationFile);
    }

    private static ProductGenerator getProductGenerator(Reader keyboardReader) {
        Map<String, ProductGenerator> productGeneratorMap = initMapOfGeneratingMethods();
        System.out.println("Enter the type of data input for adding product function: 1 - Random numbers, 2 - Manually.\n" +
                "In case of incorrect input the products will have been adding manually.");
        ProductGenerator productGenerator = productGeneratorMap.get(keyboardReader.nextLine());
        if (productGenerator == null) {
            System.err.println("Incorrect input. The product creation will be adding manually");
            return new UserInputProductGenerator();
        }
        return productGenerator;
    }

    private static Map<String, ProductGenerator> initMapOfGeneratingMethods() {
        Map<String, ProductGenerator> productGeneratorMap = new HashMap<>();
        productGeneratorMap.put("1", new RandomProductGenerator());
        productGeneratorMap.put("2", new UserInputProductGenerator());
        return Collections.unmodifiableMap(productGeneratorMap);
    }
}
