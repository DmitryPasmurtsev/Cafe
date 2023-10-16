package com.db.kursach.controllers;

import com.db.kursach.models.Product;
import com.db.kursach.services.ProductService;
import com.db.kursach.services.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> products(){
        List<Product> productsItems = productService.listProducts();
        return ResponseEntity.ok(productsItems);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> productInfo(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.ok("Добавлен продукт " + product.getName());
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Удален продукт с id " + id);
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<String> editProduct(@PathVariable Long id,@RequestBody Product product)
    {
        productService.editProduct(id,product);
        return ResponseEntity.ok("Произошло изменения продукта с id " + id);
    }
}
