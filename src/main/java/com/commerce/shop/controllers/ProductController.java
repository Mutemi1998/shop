package com.commerce.shop.controllers;

import com.commerce.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.commerce.shop.dao.Product;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public Iterable<Product> getAllProducts() {
        Iterable<Product> products = productService.getAllProducts();
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return  productService.createProduct(product.getName(),product.getDescription(),product.getPrice(),product.getQuantity(),product.getCategory().getId(),product.isAvailable());
    }
    @DeleteMapping
    @RequestMapping("id/{id}")
    public boolean deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
    @GetMapping
    @RequestMapping("/{id}")
    public Product getAllAvailableProducts(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
