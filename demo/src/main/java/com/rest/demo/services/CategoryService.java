package com.rest.demo.services;

import com.rest.demo.models.Category;
import com.rest.demo.repos.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public List<Category> searchBySlugLike(String pattern) {
        return categoryRepo.findBySlugLike(pattern);
    }

    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    public Optional<Category> updateCategory(Long id, Category category) {
        if (!categoryRepo.existsById(id)) {
            return Optional.empty();
        }
        category.setId(id);
        return Optional.of(categoryRepo.save(category));
    }

    public boolean deleteCategory(Long id) {
        if (!categoryRepo.existsById(id)) {
            return false;
        }
        categoryRepo.deleteById(id);
        return true;
    }
}
