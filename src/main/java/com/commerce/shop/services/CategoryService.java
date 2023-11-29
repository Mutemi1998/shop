package com.commerce.shop.services;

import com.commerce.shop.dao.Category;
import com.commerce.shop.repository.CategoryRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));
        return categoryRepository.save(updatedCategory);
    }
    public Iterable<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    public boolean deleteCategory(Long id) throws ChangeSetPersister.NotFoundException {
        if (!categoryRepository.existsById(id)) {
            throw new ChangeSetPersister.NotFoundException();
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
