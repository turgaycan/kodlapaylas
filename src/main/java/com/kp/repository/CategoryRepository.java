package com.kp.repository;

import com.kp.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by turgaycan on 9/27/15.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM kp.article_type at WHERE at.parent_id is null order by at.id asc",
            nativeQuery = true)
    List<Category> findRootArticleTypes();

    @Query(value = "SELECT * FROM kp.article_type at WHERE lower(at.name) = lower(:name)",
            nativeQuery = true)
    Optional<Category> findByName(@Param("name") String name);

    List<Category> findByParentId(Long parentId);

}
