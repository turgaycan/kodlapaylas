package com.kp.repository;

import com.kp.domain.ArticleType;
import com.kp.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by tcan on 17/10/15.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

    public Page<Tag> findByArticleType(ArticleType articleType, Pageable page);
}
