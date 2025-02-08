package com.rest.demo.controllers;

import com.rest.demo.models.Article;
import com.rest.demo.services.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    // Constructor Injection
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // Get all articles (Consider adding pagination)
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    // Get a single article by ID (Handles not found)
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
