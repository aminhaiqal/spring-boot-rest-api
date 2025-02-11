package com.rest.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.services.LikeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {
    
    private final LikeService likeService;

    @PostMapping("/articles/{articleId}")
    public ResponseEntity<String> likeArticle(@PathVariable Long articleId, @RequestParam Long userId, @RequestParam boolean isLike) {
        String message = likeService.likeArticle(articleId, userId, isLike);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/comments/{commentId}")
    public ResponseEntity<String> likeComment(@PathVariable Long commentId, @RequestParam Long userId, @RequestParam boolean isLike) {
        String message = likeService.likeComment(commentId, userId, isLike);
        return ResponseEntity.ok(message);
    }
}
