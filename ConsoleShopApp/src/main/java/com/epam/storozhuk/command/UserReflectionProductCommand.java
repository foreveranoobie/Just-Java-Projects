package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.reflection.ReflectionInput;
import com.epam.storozhuk.reflection.impl.UserReflectionInput;
import com.epam.storozhuk.services.ProductService;
import com.epam.storozhuk.services.Reader;
import com.epam.storozhuk.util.BundleKeys;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public class UserReflectionProductCommand implements Command {
    private final static String NUMBER_ONE_ANSWER = "1";
    private final static String NUMBER_TWO_ANSWER = "2";
    private Map<String, Supplier<Hardware>> hardwareTypes;
    private Map<String, Supplier<Hardware>> cpuTypes;
    private Reader reader;
    private ProductService productService;
    ReflectionInput reflectionInput;
    ResourceBundle vocabulary;

    public UserReflectionProductCommand(ApplicationContext applicationContext) {
        this.reader = applicationContext.getReaderService();
        productService = applicationContext.getProductService();
        reflectionInput = new UserReflectionInput(applicationContext.getBundle());
        hardwareTypes = initHardwareTypesMap();
        cpuTypes = initCPUTypesMap();
        vocabulary = applicationContext.getBundle();
    }

    @Override
    public void execute() {
        productService.addNewProduct(createProduct());
    }

    private Hardware createProduct() {
        System.out.println(vocabulary.getString(BundleKeys.PRODUCT_TYPE_VARS));
        String answer = reader.nextLine();
        return hardwareTypes.get(answer).get();
    }

    private Hardware chooseCPUType() {
        System.out.println(vocabulary.getString(BundleKeys.CPU_TYPE_VARS));
        String answer = reader.nextLine();
        return cpuTypes.get(answer).get();
    }

    private Map<String, Supplier<Hardware>> initCPUTypesMap() {
        Map<String, Supplier<Hardware>> cpuTypes = new HashMap<>();
        cpuTypes.put(NUMBER_ONE_ANSWER, reflectionInput::createCPU);
        cpuTypes.put(NUMBER_TWO_ANSWER, reflectionInput::createIntel);
        return Collections.unmodifiableMap(cpuTypes);
    }

    private Map<String, Supplier<Hardware>> initHardwareTypesMap() {
        Map<String, Supplier<Hardware>> hardwareTypes = new HashMap<>();
        hardwareTypes.put(NUMBER_ONE_ANSWER, this::chooseCPUType);
        hardwareTypes.put(NUMBER_TWO_ANSWER, reflectionInput::createVideoCard);
        return Collections.unmodifiableMap(hardwareTypes);
    }
}
