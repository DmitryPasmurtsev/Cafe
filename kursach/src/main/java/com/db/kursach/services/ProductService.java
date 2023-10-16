package com.db.kursach.services;

import com.db.kursach.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> listProducts();

    Product getProductById(Long id);

    void saveProduct(Product product);

    void deleteProduct(Long id);

    void editProduct(Long id, Product product);
}
