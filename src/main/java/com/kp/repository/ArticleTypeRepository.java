package com.kp.repository;

import com.kp.domain.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by turgaycan on 9/27/15.
 */
public interface ArticleTypeRepository extends JpaRepository<ArticleType, Long> {

    @Query(value = "SELECT * FROM kp.article_type at WHERE at.parent_id is null",
            nativeQuery = true)
    List<ArticleType> findRootArticleTypes();

    @Query(value = "SELECT * FROM kp.article_type at WHERE lower(at.name) = lower(:name)",
            nativeQuery = true)
    Optional<ArticleType> findByName(@Param("name") String name);
}
