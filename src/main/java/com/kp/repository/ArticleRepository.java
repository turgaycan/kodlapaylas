package com.kp.repository;

import com.kp.domain.Article;
import com.kp.domain.ArticleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


/**
 * Created by turgaycan on 9/26/15.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("FROM Article a LEFT JOIN FETCH  a.user u LEFT JOIN FETCH a.articleType at WHERE a.id =(:id)")
    Article findArticleById(@Param("id") Long id);

    Page<Article> findByArticleTypeOrderByCreatedateDesc(ArticleType articleType, Pageable page);

    Page<Article> findByArticleTypeOrTitleLike(ArticleType articleType, String title, Pageable page);

    Page<Article> findByArticleType(ArticleType articleType, Pageable page);

    Page<Article> findByCreatedateBetween(Date startDate, Date endDate, Pageable page);

    Page<Article> findByCreatedateAfterAndCreatedateBefore(Date startDate, Date endDate, Pageable page);

    Article findById(Long id);

    List<Article> findByArticleTypeIn(List<ArticleType> articleTypes);
}
