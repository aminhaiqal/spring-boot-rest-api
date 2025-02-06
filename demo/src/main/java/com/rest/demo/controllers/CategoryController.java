package com.rest.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.demo.models.Category;
import com.rest.demo.repos.CategoryRepo;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepo repo;

    // Constructor Injection
    public CategoryController(CategoryRepo repo) {
        this.repo = repo;
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(repo.findAll());
    }

    // Find categories by slug pattern
    @GetMapping("/search/slug/{pattern}")
    public ResponseEntity<List<Category>> searchBySlugLike(@PathVariable String pattern) {
        List<Category> categories = repo.findBySlugLike(pattern);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category c) {
        return ResponseEntity.ok(repo.save(c));
    }

    // Update category (handle non-existent ID)
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category c) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        c.setId(id);
        return ResponseEntity.ok(repo.save(c));
    }

    // Delete category (handle errors, return 204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
