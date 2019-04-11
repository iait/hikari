package com.iait.hikari.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iait.hikari.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select max(e.id) from Article e")
    Long getMaxId();

}
