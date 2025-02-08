package com.rest.demo.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.demo.models.Article;
import java.time.LocalDateTime;


@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {

    // Find an article by its slug
    Optional<Article> findBySlug(String slug);

    // Find articles containing a keyword in the title (case insensitive)
    List<Article> findByTitleContainingIgnoreCase(String keyword);

    // Find all articles belonging to a specific category
    @Query("SELECT a FROM Article a JOIN a.categories c WHERE c.name = :categoryName")
    List<Article> findByCategoryName(@Param("categoryName") String categoryName);

    // Find articles created after a certain date
    List<Article> findByCreatedAtAfter(LocalDateTime createdAt);
}
