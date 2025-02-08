package com.rest.demo.services;

import com.rest.demo.models.Article;
import com.rest.demo.models.Category;
import com.rest.demo.repos.ArticleRepo;
import com.rest.demo.repos.CategoryRepo;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArticleService {

    private final ArticleRepo articleRepo;
    private final CategoryRepo categoryRepo;

    public ArticleService(ArticleRepo articleRepo, CategoryRepo categoryRepo) {
        this.articleRepo = articleRepo;
        this.categoryRepo = categoryRepo;
    }


    private String generateSlug(String title) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        String normalized = Normalizer.normalize(title, Normalizer.Form.NFD);
        return normalized.replaceAll("[^\\w\\s-]", "")
                .trim()
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }

    // Get all articles
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    // Get article by ID
    public Optional<Article> getArticleById(Long id) {
        return articleRepo.findById(id);
    }

    // Create a new article
    public Article createArticle(Article article, List<Long> categoryIds) {
        String slug = generateSlug(article.getTitle());

        Set<Category> categories = new HashSet<>(categoryRepo.findAllById(categoryIds));

        if (categories.size() != categoryIds.size()) {
            throw new RuntimeException("One or more categories not found");
        }
    
        // Create a new Article object while keeping other fields the same
        Article a = Article.builder()
                .id(article.getId())
                .title(article.getTitle())
                .slug(slug)
                .content(article.getContent())
                .categories(categories)
                .createdAt(article.getCreatedAt() != null ? article.getCreatedAt() : LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    
        return articleRepo.save(a);
    }

    // Update an existing article
    public Optional<Article> updateArticle(Long id, Article articleDetails) {
        return articleRepo.findById(id).map(article -> {
            article.setTitle(articleDetails.getTitle());
            article.setSlug(articleDetails.getSlug());
            article.setContent(articleDetails.getContent());
            article.setCategories(articleDetails.getCategories());
            return articleRepo.save(article);
        });
    }

    // Delete an article by ID
    public boolean deleteArticle(Long id) {
        if (articleRepo.existsById(id)) {
            articleRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
