package com.db.kursach.services.impl;

import com.db.kursach.exceptions.NotCreatedException;
import com.db.kursach.exceptions.NotFoundException;
import com.db.kursach.models.Product;
import com.db.kursach.repositories.ProductRepository;
import com.db.kursach.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public List<Product> listProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        if (productRepository.findById(id).isEmpty())
            throw new NotFoundException("Продукт отсутствует");
        return productRepository.findById(id).get();
    }
    public Product getProductByName(String productName) {return productRepository.getProductByName(productName);}

    public void saveProduct(Product product) {
        if (productRepository.getProductByName(product.getName())!=null){
            throw new NotCreatedException("Продукт с таким названием уже существует","name");
        }
        productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    public void editProduct(Long id,Product product){
        if (productRepository.findById(id).isEmpty())
            throw new NotFoundException("Продукт отсутствует");
        if(productRepository.getProductByName(product.getName())!=null&& !Objects.equals(productRepository.getProductByName(product.getName()).getId(), id))
            throw new NotCreatedException("Продукт с таким названием уже существует","name");
        Product productToEdit=productRepository.findById(id).get();
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
