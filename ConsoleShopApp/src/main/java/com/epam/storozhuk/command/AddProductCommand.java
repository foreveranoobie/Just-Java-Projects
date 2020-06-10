package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.services.ProductService;
import com.epam.storozhuk.services.Reader;
import com.epam.storozhuk.strategy.ProductGenerator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AddProductCommand implements Command {
    private Map<String, Supplier<Hardware>> hardwareTypes;
    private Map<String, Supplier<Hardware>> cpuTypes;
    private Reader reader;
    private ProductService productService;
    private ProductGenerator productGenerator;

    public AddProductCommand(ApplicationContext applicationContext) {
        productGenerator = applicationContext.getProductGenerator();
        hardwareTypes = initHardwareTypesMap();
        cpuTypes = initCPUTypesMap();
        this.reader = applicationContext.getReaderService();
        productService = applicationContext.getProductService();
    }

    @Override
    public void execute() {
        productService.addNewProduct(createProduct());
    }

    private Hardware createProduct() {
        System.out.println("Enter the product type:\n1 - CPU\n2 - VideoCard");
        String answer = reader.nextLine();
        return hardwareTypes.get(answer).get();
    }

    private Hardware chooseCPUType() {
        System.out.println("Enter the CPU type:\n1 - Simple CPU\n2 - Intel CPU");
        String answer = reader.nextLine();
        return cpuTypes.get(answer).get();
    }

    private Map<String, Supplier<Hardware>> initCPUTypesMap() {
        Map<String, Supplier<Hardware>> cpuTypes = new HashMap<>();
        cpuTypes.put("1", productGenerator::createCPU);
        cpuTypes.put("2", productGenerator::createIntel);
        return Collections.unmodifiableMap(cpuTypes);
    }

    private Map<String, Supplier<Hardware>> initHardwareTypesMap() {
        Map<String, Supplier<Hardware>> hardwareTypes = new HashMap<>();
        hardwareTypes.put("1", this::chooseCPUType);
        hardwareTypes.put("2", productGenerator::createVideoCard);
        return Collections.unmodifiableMap(hardwareTypes);
    }
}
