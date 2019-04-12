package com.iait.hikari.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iait.hikari.entities.Article;
import com.iait.hikari.enums.Category;
import com.iait.hikari.repositories.ArticleRepository;

@Service
public class ArticleService {
    
    @Autowired
    ArticleRepository articleRepository;
    
    @Transactional(readOnly=true)
    public List<Article> getAllArticles() {
        return new ArrayList<>(articleRepository.findAll());
    }
    
    @Transactional(readOnly=true)
    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }
    
    @Transactional
    public Long addArticle(String title, Category category) {
        
        Long id = articleRepository.getMaxId();
        if (id == null) {
            id = 0L;
        }
        id++;
        
        Article article = new Article(id, title, category);
        articleRepository.save(article);
        
        return id;
    }

}
