package com.db.kursach.controllers;

import com.db.kursach.dto.ProductDTO;
import com.db.kursach.models.Product;
import com.db.kursach.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> products(){
        List<ProductDTO> products = productService.listProducts()
                .stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> productInfo(@PathVariable Long id){
        ProductDTO product = modelMapper.map(productService.getProductById(id), ProductDTO.class);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
        Product product=modelMapper.map(productDTO, Product.class);
        productService.saveProduct(product);
        return ResponseEntity.ok("Добавлен продукт " + product.getName());
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Удален продукт с id " + id);
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<String> editProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO)
    {
        Product product=modelMapper.map(productDTO, Product.class);
        productService.editProduct(id,product);
        return ResponseEntity.ok("Произошло изменения продукта с id " + id);
    }
}
