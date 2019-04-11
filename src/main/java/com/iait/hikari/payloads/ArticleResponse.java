package com.iait.hikari.payloads;

import com.iait.hikari.enums.Category;

public class ArticleResponse {
    
    private Long id;
    private String title;
    private Category category;
    
    public ArticleResponse() {}
    
    public ArticleResponse(Long id, String title, Category category) {
        super();
        this.id = id;
        this.title = title;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
