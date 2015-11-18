package com.kp.service.article;

import com.google.common.collect.Lists;
import com.kp.domain.Article;
import com.kp.domain.Comment;
import com.kp.dto.CommentBaseModel;
import com.kp.dto.CommentUIModel;
import com.kp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<Comment> findComments(Article article) {
        return commentRepository.findByArticleOrderByCreatedateAsc(article);
    }

    public CommentBaseModel buildCommentModel(Article article) {
        List<CommentUIModel> commentUIModels = new ArrayList<>();
        final List<Comment> comments = findComments(article);
        int commentSize = comments.size();
        List<Comment> toRemoveList = Lists.newArrayList();
        for (Comment comment : comments) {
            if (findByParent(comment).isPresent()) {
                CommentUIModel uiModel = new CommentUIModel();
                uiModel.setComment(comment);
                final List<Comment> replyComments = replyChildComments(comment, Lists.newArrayList(), comments, toRemoveList);
                uiModel.setReplyComments(replyComments);
                comments.removeAll(toRemoveList);
                commentUIModels.add(uiModel);
            } else {
                if (toRemoveList.contains(comment)) {
                    continue;
                }
                CommentUIModel uiModel = new CommentUIModel();
                uiModel.setComment(comment);
                commentUIModels.add(uiModel);
            }
        }

        return new CommentBaseModel(commentSize, commentUIModels);
    }

    protected List<Comment> replyChildComments(Comment comment, List<Comment> replyCommentList,
                                               List<Comment> comments,
                                               List<Comment> toRemoveList) {
        for (Comment each : comments) {
            final Comment parent = each.getParent();
            if (comment.equals(parent)) {
                replyCommentList.add(each);
                toRemoveList.add(each);
                replyChildComments(each, replyCommentList, comments, toRemoveList);
            }
        }
        return replyCommentList;
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(commentRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findByParent(Comment comment) {
        return Optional.ofNullable(commentRepository.findByParent(comment));
    }

}
