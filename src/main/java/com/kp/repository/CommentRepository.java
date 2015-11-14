package com.kp.repository;

import com.kp.domain.Article;
import com.kp.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tcan on 05/10/15.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleOrderByCreatedateAsc(Article article);

    List<Comment> findByArticleOrderByCreatedateDesc(Article article);

    Comment findByParent(Comment comment);

    @Query("select count(c) from Comment c where c.article = ?1")
    public Long getArticleCount(Article article);
}
