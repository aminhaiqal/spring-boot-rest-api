package com.rest.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.models.Category;
import com.rest.demo.repos.CategoryRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryRepo repo;

    @GetMapping
    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    @GetMapping("/search/slug/{pattern}")
    public List<Category> searchBySlugLike(@PathVariable String pattern){
        return repo.findBySlugLike("%" + pattern + "%");
    }
     
    @PostMapping()
    public List<Category> createCategory(@RequestBody Category c) {
        repo.save(c);
        return repo.findAllByOrderByNameAsc();
    }
    
    @DeleteMapping("/{id}")
    public List<Category> deleteCategory(@PathVariable Long id) {
        repo.deleteById(id);
        return repo.findAllByOrderByNameAsc();
    }

    @PutMapping("/{id}")
    public List<Category> updateCategory(@PathVariable Long id, @RequestBody Category c) {
        c.setId(id);
        repo.save(c);
        return repo.findAllByOrderByNameAsc();
    }
}
