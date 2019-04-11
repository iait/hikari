package com.iait.hikari.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.iait.hikari.enums.Category;

@Entity
@Table(name="articles")
public class Article implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="article_id")
    private Long id;
    
    private String title;
    
    @Enumerated(EnumType.STRING)
    private Category category;

    public Article() {}
    
    public Article(Long articleId, String title, Category category) {
        super();
        this.id = articleId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Article other = (Article) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", category=" + category + "]";
    }

}
