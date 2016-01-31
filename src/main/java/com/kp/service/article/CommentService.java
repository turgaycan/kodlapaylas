package com.kp.service.article;

import com.kp.domain.Article;
import com.kp.domain.Comment;
import com.kp.domain.model.CommentTree;
import com.kp.dto.CommentBaseModel;
import com.kp.dto.CommentUIModel;
import com.kp.repository.CommentRepository;
import com.kp.util.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;


/**
 * Created by tcan on 05/10/15.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<Comment> findComments(Article article) {
        return commentRepository.findByArticleOrderByCreatedateAsc(article);
    }

    @Transactional(readOnly = true)
    public List<Comment> findCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticleIdOrderByCreatedateDesc(articleId);
    }

    @Transactional(readOnly = true)
    public List<CommentTree> findCommentTree(Long articleId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("articleId", articleId);
        return jdbcTemplate.query(Queries.COMMENT_QUERY,
                new BeanPropertyRowMapper(CommentTree.class),
                parameters);
    }

    public CommentBaseModel buildCommentModel(Article article) {
        List<CommentUIModel> commentUIModels = new ArrayList<>();

        for (Comment comment : findCommentsByArticleId(article.getId())) {
            CommentUIModel commentUIModel = new CommentUIModel();
            commentUIModel.setComment(comment);

            commentUIModels.add(commentUIModel);
        }
        return new CommentBaseModel(commentUIModels.size(), commentUIModels);
    }

    private List<Comment> getChildComments(CommentTree commentTree, List<CommentTree> childs) {
        List<Comment> childComments = newArrayList();
        for (CommentTree replyCommentTree : childs) {
            if (commentTree.getId().equals(replyCommentTree.getParentId())) {
                CommentUIModel commentUIModel = new CommentUIModel();
                commentUIModel.setComment(getOne(replyCommentTree.getId()));

            }
        }
        return childComments;
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(getOne(id));
    }

    private Comment getOne(Long id) {
        return commentRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findByParent(Comment comment) {
        return Optional.ofNullable(commentRepository.findByParent(comment));
    }

    @Transactional(readOnly = true)
    public long getArticleCommentCount(Article article) {
        return commentRepository.getArticleCount(article);
    }

}
