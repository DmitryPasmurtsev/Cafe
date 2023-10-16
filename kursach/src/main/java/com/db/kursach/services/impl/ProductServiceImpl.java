package com.db.kursach.services.impl;

import com.db.kursach.models.Product;
import com.db.kursach.repositories.ProductRepository;
import com.db.kursach.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public List<Product> listProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public Product getProductByName(String productName) {return productRepository.getProductByName(productName);}

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    public void editProduct(Long id,Product product){
        Product productToEdit=productRepository.findById(id).orElseThrow();
        editingProduct(product,productToEdit);
        productRepository.save(productToEdit);
    }
    private void editingProduct(Product product,Product productToEdit){
        productToEdit.setAmount(product.getAmount());
        productToEdit.setPrice(product.getPrice());
        productToEdit.setDescription(product.getDescription());
        productToEdit.setName(product.getName());
        productToEdit.setCalories(product.getCalories());
        productToEdit.setUnitWeight(product.getUnitWeight());
    }
}
