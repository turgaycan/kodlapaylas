package com.kp.repository;

import com.kp.domain.Category;
import com.kp.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Created by tcan on 17/10/15.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

    public Page<Tag> findByArticleType(Category category, Pageable page);

    public List<Tag> orderByCountDesc(Pageable page);

}
