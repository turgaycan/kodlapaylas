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

    @Query(value = "SELECT * FROM kp.category cat WHERE cat.parent_id is null order by cat.id asc",
            nativeQuery = true)
    List<Category> findRootCategories();

    @Query(value = "SELECT * FROM kp.category cat WHERE lower(cat.name) = lower(:name)",
            nativeQuery = true)
    Optional<Category> findByName(@Param("name") String name);

    List<Category> findByParentId(Long parentId);

}
