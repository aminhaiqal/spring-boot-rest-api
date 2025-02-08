package com.rest.demo.services;

import com.rest.demo.models.Article;
import com.rest.demo.repos.ArticleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepo articleRepo;

    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepo.findById(id);
    }
}
