package com.rest.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.demo.models.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);
}
