package com.db.kursach.controllers;

import com.db.kursach.models.Employee;
import com.db.kursach.models.Order;
import com.db.kursach.models.Product;
import com.db.kursach.repositories.ProductRepository;
import com.db.kursach.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> products(@RequestParam(name = "productName",required = false) String productName){
        List<Product> productsItems = productService.listProducts(productName);
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
