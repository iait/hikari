package com.iait.hikari.ctrls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iait.hikari.entities.Article;
import com.iait.hikari.payloads.ArticlePayload;
import com.iait.hikari.services.ArticleService;

@RestController
@RequestMapping("/hikari")
public class ArticleCtrl {

    @Autowired
    private ArticleService articleService;
    
    @GetMapping(path="articles")
    public ResponseEntity<List<ArticlePayload>> getArticles() {
        List<Article> articles = articleService.getAllArticles();
        List<ArticlePayload> response = articles.stream().map(article -> {
            ArticlePayload articleResponse = new ArticlePayload();
            articleResponse.setId(article.getId());
            articleResponse.setTitle(article.getTitle());
            articleResponse.setCategory(article.getCategory());
            return articleResponse;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping(path="articles")
    public ResponseEntity<Void> addArticle(@RequestBody ArticlePayload request) {
        articleService.addArticle(request.getTitle(), request.getCategory());
        return ResponseEntity.ok().build();
    }
    
}
