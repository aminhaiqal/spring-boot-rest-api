package com.rest.demo.repos;

import com.rest.demo.models.Category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    
    // Find by name
    Optional<Category> findByName(String name);

    // Find categories by partial name (for search)
    List<Category> findByNameContaining(String name);

    // Find all categories sorted by name
    List<Category> findAllByOrderByNameAsc();

    // Find categories with a specific slug pattern (using a LIKE query)
    @Query("SELECT c FROM Category c WHERE c.slug LIKE %:pattern%")
    List<Category> findBySlugLike(@Param("pattern") String pattern);

    // Find categories where slug is null or empty
    List<Category> findBySlugIsNullOrSlug(String slug);
}
