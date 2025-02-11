package com.rest.demo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.demo.models.Article;
import com.rest.demo.models.Comment;
import com.rest.demo.models.Like;
import com.rest.demo.models.User;

@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {
    
    Optional<Like> findByUserAndArticle(User user, Article article);
    Optional<Like> findByUserAndComment(User user, Comment comment);
    int countByArticle(Article article);
    int countByComment(Comment comment);
}
