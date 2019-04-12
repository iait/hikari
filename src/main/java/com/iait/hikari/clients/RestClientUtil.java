package com.iait.hikari.clients;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.iait.hikari.entities.Article;
import com.iait.hikari.enums.Category;

public class RestClientUtil {

    public void getAllArticlesDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/hikari/articles";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Article[]> responseEntity = 
                restTemplate.exchange(url, HttpMethod.GET, requestEntity, Article[].class);
        Article[] articles = responseEntity.getBody();
        for (Article article : articles) {
              System.out.println("Id:" + article.getId() + ", Title:" + article.getTitle()
                      +", Category: " + article.getCategory());
        }
    }
    
    public void addArticleDemo(String title, Category category) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/hikari/articles";
        Article objArticle = new Article();
        objArticle.setTitle(title);
        objArticle.setCategory(category);
        HttpEntity<Article> requestEntity = new HttpEntity<>(objArticle, headers);
        URI location = restTemplate.postForLocation(url, requestEntity);
        System.out.println(location);
    }
    
    public static void main(String args[]) {
        RestClientUtil util = new RestClientUtil();
        util.addArticleDemo("Acer 123", Category.NOTEBOOK);
        util.addArticleDemo("Philips 273V", Category.MONITOR);
        util.getAllArticlesDemo();      
    }
    
}
