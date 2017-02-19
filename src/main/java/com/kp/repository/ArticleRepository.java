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

    @Query(value = "SELECT a From Article a LEFT JOIN a.user u LEFT JOIN a.articleType at WHERE a.articleType.id = (:articleTypeId) ORDER BY a.createdate DESC",
            countQuery = "SELECT count(a) FROM Article a LEFT JOIN a.user u LEFT JOIN a.articleType at WHERE a.articleType.id = (:articleTypeId)")
    Page<Article> findByArticleTypePageable(@Param("articleTypeId") long articleTypeId, Pageable page);

    Page<Article> findByArticleTypeOrTitleLike(ArticleType articleType, String title, Pageable page);

    Page<Article> findByArticleType(ArticleType articleType, Pageable page);

    Page<Article> findByArticleTypeIn(List<ArticleType> articleType, Pageable page);


    Page<Article> findByCreatedateAfterAndCreatedateBefore(Date startDate, Date endDate, Pageable page);

    @Query(value = "SELECT a From Article a LEFT JOIN a.user u LEFT JOIN a.articleType at ORDER BY a.createdate DESC")
    Page<Article> findPageableOrderByCreatedateDesc(Pageable page);

    @Query(value = "select * from article a left join user u on u.id = a.user_id left join article_type at on at.id = a.article_type_id where a.id = (:id)",
            nativeQuery = true)
    Article findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM article a left join article_type at on at.id = a.article_type_id left join kp.user u on u.id = a.user_id WHERE  article_type_id IN (:article_type_id) order by view_number desc LIMIT 2",
            nativeQuery = true)
    List<Article> findByArticleTypeId(@Param("article_type_id") Long[] parentIds);

    Page<Article> findByTagsContaining(String tag, Pageable page);



}
