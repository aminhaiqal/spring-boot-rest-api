package com.rest.demo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rest.demo.models.Article;
import com.rest.demo.models.Comment;
import com.rest.demo.models.Like;
import com.rest.demo.models.User;
import com.rest.demo.repos.ArticleRepo;
import com.rest.demo.repos.CommentRepo;
import com.rest.demo.repos.LikeRepo;
import com.rest.demo.repos.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
    
    private final LikeRepo likeRepo;
    private final ArticleRepo articleRepo;
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;

    public String likeArticle(Long articleId, Long userId, boolean isLike) {
        User user = userRepo.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        Article article = articleRepo.findById(articleId)
        .orElseThrow(() -> new RuntimeException("Article not found"));

        Optional<Like> existingLike = likeRepo.findByUserAndArticle(user, article);

        if (existingLike.isPresent()) {
            Like like = existingLike.get();
            like.setLike(isLike);
            likeRepo.save(like);
            return isLike ? "Liked successfully!" : "Unliked successfully!";
        }
        
        Like like = new Like();
        like.setUser(user);
        like.setArticle(article);
        like.setLike(isLike);
        likeRepo.save(like);

        return "Liked successfully!";
    }

    public String likeComment(Long commentId, Long userId, boolean isLike) {
        User user = userRepo.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = commentRepo.findById(commentId)
        .orElseThrow(() -> new RuntimeException("Comment not found"));

        Optional<Like> existingLike = likeRepo.findByUserAndComment(user, comment);

        if(existingLike.isPresent()) {
            likeRepo.delete(existingLike.get());
            return "Like Removed";
        }

        Like like = new Like();
        like.setUser(user);
        like.setComment(comment);
        like.setLike(isLike);
        likeRepo.save(like);

        return "Liked successfully!";
    }
}
