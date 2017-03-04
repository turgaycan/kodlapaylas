package com.kp.service.article;

import com.kp.domain.Article;
import com.kp.domain.Comment;
import com.kp.domain.model.CommentTree;
import com.kp.domain.spec.PageSpec;
import com.kp.dto.CommentBaseModel;
import com.kp.dto.CommentUIModel;
import com.kp.repository.CommentRepository;
import com.kp.util.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public Page<Comment> getPaginated(int pageIndex, int pageSize) {
        return commentRepository.findAll(PageSpec.buildPageSpecificationByFieldDesc(pageIndex, pageSize, "createdate"));
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByArticleId(Long articleId) {
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

        for (Comment comment : getCommentsByArticleId(article.getId())) {
            CommentUIModel commentUIModel = new CommentUIModel();
            commentUIModel.setComment(comment);

            commentUIModels.add(commentUIModel);
        }
        return new CommentBaseModel(commentUIModels.size(), commentUIModels);
    }

    @Transactional(readOnly = true)
    public Optional<Comment> getById(Long id) {
        return Optional.ofNullable(getOne(id));
    }

    @Transactional(readOnly = true)
    public long getArticleCommentCount(Article article) {
        return commentRepository.getArticleCount(article);
    }

    @Transactional(readOnly = true)
    public long countOfTotalComments() {
        return commentRepository.count();
    }

    @Transactional(readOnly = true)
    public Comment getOne(Long id) {
        return commentRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }
}
