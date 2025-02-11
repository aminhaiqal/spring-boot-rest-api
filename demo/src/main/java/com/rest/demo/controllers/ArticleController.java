package com.rest.demo.controllers;

import com.rest.demo.dtos.ArticleRequest;
import com.rest.demo.models.Article;
import com.rest.demo.services.ArticleService;
import com.rest.demo.services.LikeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final LikeService likeService;

    // Constructor Injection
    public ArticleController(ArticleService articleService, LikeService likeService) {
        this.articleService = articleService;
        this.likeService = likeService;
    }

    // Get all articles
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    // Get a single article by ID
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new article
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody ArticleRequest req) {
        Article a = new Article();
        a.setTitle(req.getTitle());
        a.setContent(req.getContent());

        Article savedArticle = articleService.createArticle(a, req.getCategoryIds());
        return ResponseEntity.ok(savedArticle);
    }

    // Update an existing article
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        return articleService.updateArticle(id, article)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (articleService.deleteArticle(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Like or Unlike an article
    @PostMapping("/{articleId}/like/{userId}")
    public ResponseEntity<String> likeArticle(@PathVariable Long articleId, @PathVariable Long userId, @RequestParam boolean isLike) {
        String message = likeService.likeArticle(articleId, userId, isLike);
        return ResponseEntity.ok(message);
    }
}
