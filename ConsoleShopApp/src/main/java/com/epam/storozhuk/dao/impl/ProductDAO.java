package com.epam.storozhuk.dao.impl;

import com.epam.storozhuk.dao.DAO;
import com.epam.storozhuk.entities.Hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDAO implements DAO, Serializable {
    private static final long serialVersionUID = -4734865437201288276L;
    private List<Hardware> productsList;

    public ProductDAO() {
        productsList = new ArrayList<>();
    }

    public ProductDAO(List<Hardware> hardwares) {
        productsList = new ArrayList<>(hardwares);
    }

    public boolean addProduct(Hardware productToAdd) {
        return productsList.add(productToAdd);
    }

    public Hardware removeProduct(int index) {
        return productsList.remove(index);
    }

    public List<Hardware> getProducts() {
        return Collections.unmodifiableList(productsList);
    }

}
