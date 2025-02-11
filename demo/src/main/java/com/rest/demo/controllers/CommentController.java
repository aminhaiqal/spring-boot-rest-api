package com.rest.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.dtos.CommentRequest;
import com.rest.demo.models.Comment;
import com.rest.demo.services.CommentService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    
    private CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    // Get all comments for an article
    @GetMapping("/articles/{articleId}")
    public ResponseEntity<List<Comment>> getCommentsByArticle(@PathVariable Long articleId) {
        return ResponseEntity.ok(service.getCommentbyArticleId(articleId));
    }

    // Get a single comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return service.getCommentById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/article/{articleId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long articleId, @RequestBody CommentRequest request) {
        return ResponseEntity.ok(service.addComment(articleId, request.getContent()));
    }

    // Delete comment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        service.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
    
    // Like or Unlike an comment
    
}
