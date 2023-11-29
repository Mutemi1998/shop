package com.commerce.shop.services;

import com.commerce.shop.dao.Category;
import com.commerce.shop.dao.Product;
import com.commerce.shop.repository.CategoryRepository;
import com.commerce.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    public Product createProduct(String name, String description, double price, int quantity, Long categoryId, boolean available) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));

        Product product = new Product(null, name, description, price, quantity, category, available);
        return productRepository.save(product);
    }
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        return productRepository.save(product);
    }
    public boolean deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        productRepository.deleteById(id);
        return true;
    }
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
    }
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
    public List<Product> findAvailableProducts() {
        return productRepository.findByAvailable(true);
    }
}