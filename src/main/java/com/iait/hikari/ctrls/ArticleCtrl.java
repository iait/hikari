package com.iait.hikari.ctrls;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
        return ResponseEntity.ok(response);
    }
    
    @GetMapping(path="articles/{id}")
    public ResponseEntity<ArticlePayload> getArticle(@PathVariable Long id) {
        Optional<Article> optArticle = articleService.getArticle(id);
        if (optArticle.isPresent()) {
            Article article = optArticle.get();
            ArticlePayload articleResponse = new ArticlePayload();
            articleResponse.setId(article.getId());
            articleResponse.setTitle(article.getTitle());
            articleResponse.setCategory(article.getCategory());
            return ResponseEntity.ok(articleResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping(path="articles")
    public ResponseEntity<Void> addArticle(@RequestBody ArticlePayload request, 
            UriComponentsBuilder builder) {
        Long id = articleService.addArticle(request.getTitle(), request.getCategory());
        URI location = builder.path("/articles/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }
    
}
