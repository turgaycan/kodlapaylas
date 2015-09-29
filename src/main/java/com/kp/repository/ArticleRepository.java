package com.kp.repository;

import com.kp.domain.Article;
import com.kp.domain.ArticleType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by turgaycan on 9/26/15.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("FROM Article a LEFT JOIN FETCH  a.user u LEFT JOIN FETCH a.articleType at WHERE a.id =(:id)")
    Article findArticleById(@Param("id") Long id);

    @Query(value = "FROM Article a LEFT JOIN FETCH a.articleType at ORDER BY a.createdate DESC")
    List<Article> findRecentArticles();


    List<Article> findArticlesByArticleType(ArticleType articleType, PageRequest pageRequest);
}
