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
import com.iait.hikari.payloads.ArticleResponse;
import com.iait.hikari.services.ArticleService;

@RestController
@RequestMapping("/hikari")
public class ArticleCtrl {

    @Autowired
    private ArticleService articleService;
    
    @GetMapping(path="articles")
    public ResponseEntity<List<ArticleResponse>> getArticles() {
        List<Article> articles = articleService.getAllArticles();
        List<ArticleResponse> response = articles.stream().map(article -> {
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(article.getId());
            articleResponse.setTitle(article.getTitle());
            articleResponse.setCategory(article.getCategory());
            return articleResponse;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping(path="article")
    public ResponseEntity<Void> addArticle(@RequestBody ArticleResponse request) {
        articleService.addArticle(request.getTitle(), request.getCategory());
        return ResponseEntity.ok().build();
    }
    
}
