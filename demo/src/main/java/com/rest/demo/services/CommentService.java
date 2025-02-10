package com.rest.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rest.demo.models.Article;
import com.rest.demo.models.Comment;
import com.rest.demo.repos.ArticleRepo;
import com.rest.demo.repos.CommentRepo;

@Service
public class CommentService {
    
    private final CommentRepo commentRepo;
    private final ArticleRepo articleRepo;

    public CommentService(CommentRepo commentRepo, ArticleRepo articleRepo) {
        this.commentRepo = commentRepo;
        this.articleRepo = articleRepo;
    }

    public List<Comment> getCommentbyArticleId(Long articleId) {
        return commentRepo.findByArticleId(articleId);
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepo.findById(id);
    }

    public Comment addComment(Long articleId, String content) {
        Article article = articleRepo.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        Comment comment = Comment.builder()
                .article(article)
                .build();

        return commentRepo.save(comment);
    }

    public void deleteComment(Long id) {
        if (!commentRepo.existsById(id)) {
            throw new RuntimeException("Comment not found");
        }
        commentRepo.deleteById(id);
    }
}
