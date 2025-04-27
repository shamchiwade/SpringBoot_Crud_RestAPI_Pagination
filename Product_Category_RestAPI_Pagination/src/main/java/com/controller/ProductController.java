package com.controller;
import org.springframework.data.domain.Pageable; 
import org.springframework.data.domain.Page;     
import org.springframework.data.domain.PageRequest; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Product;
import com.service.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Hi, welcome to the Product ";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }



    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int productId,
                                                 @RequestBody Product updatedProduct) {
        return productRepository.findById(productId)
                .map(existingProduct -> {
                    existingProduct.setProductName(updatedProduct.getProductName());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setCategory(updatedProduct.getCategory());
                    Product savedProduct = productRepository.save(existingProduct);
                    return ResponseEntity.ok(savedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") int productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> {
                    productRepository.delete(existingProduct);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
