package com.rest.demo.dtos;

import com.rest.demo.models.Article;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ArticleResponse {
    private Long id;
    private String title;
    private String slug;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> comments;
    private int totalLikes;
    
    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.slug = article.getSlug();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
        this.comments = article.getComments().stream()
                .map(comment -> new CommentResponse(comment.getId(), comment.getContent(), comment.getCreatedAt()))
                .toList();
        this.totalLikes = article.getLikes().size();
    }
}
