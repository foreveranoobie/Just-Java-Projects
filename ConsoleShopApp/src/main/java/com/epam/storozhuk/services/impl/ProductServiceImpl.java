package com.epam.storozhuk.services.impl;

import com.epam.storozhuk.dao.impl.BasketDAO;
import com.epam.storozhuk.dao.impl.OrderDAO;
import com.epam.storozhuk.dao.impl.ProductDAO;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Hardware removeProduct(int index) {
        return productDAO.removeProduct(index);
    }

    public List<Hardware> getAllProducts() {
        return productDAO.getProducts();
    }

    public boolean addNewProduct(Hardware product) {
        return productDAO.addProduct(product);
    }

    public Hardware getProductById(int id) {
        return productDAO.getProducts().get(id - 1);
    }
}
