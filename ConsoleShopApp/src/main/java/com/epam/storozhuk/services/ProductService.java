package com.epam.storozhuk.services;

import com.epam.storozhuk.entities.Hardware;

import java.util.List;

public interface ProductService {
    List<Hardware> getAllProducts();

    boolean addNewProduct(Hardware product);

    Hardware getProductById(int id);

    Hardware removeProduct(int index);
}
