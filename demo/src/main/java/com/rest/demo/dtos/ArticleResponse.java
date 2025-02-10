package com.rest.demo.dtos;

import com.rest.demo.models.Comment;
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
    
    public ArticleResponse(Long id, String title, String slug, String content, LocalDateTime createdAt, LocalDateTime updatedAt, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.comments = comments.stream()
                .map(comment -> new CommentResponse(comment.getId(), comment.getContent(), comment.getCreatedAt()))
                .toList();
    }
}
