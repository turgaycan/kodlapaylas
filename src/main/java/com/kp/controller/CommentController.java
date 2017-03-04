package com.kp.controller;

import com.kp.domain.Article;
import com.kp.domain.Comment;
import com.kp.domain.User;
import com.kp.dto.*;
import com.kp.dto.enumurations.KpErrors;
import com.kp.exception.model.KpErrorResponse;
import com.kp.service.article.ArticleService;
import com.kp.service.article.CommentService;
import com.kp.service.security.AuthenticationService;
import com.kp.service.user.UserService;
import com.kp.util.DateUtils;
import com.kp.util.KpUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by tcan on 04/10/15.
 */
@Controller
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/yorumlar/{articleId:\\d+$}", method = RequestMethod.GET)
    public ModelAndView listComments(@PathVariable Long articleId) {
        LOGGER.info("article id {}", articleId);
        ModelAndView mav = new ModelAndView("/contents/comments");
        final Article article = articleService.getById(articleId);
        if (article == null) {
            return mav;
        }

        CommentBaseModel commentBaseModel = commentService.buildCommentModel(article);
        mav.addObject("commentBaseModel", commentBaseModel);
        return mav;
    }

    @RequestMapping(value = "/yorum/yeni", method = RequestMethod.POST)
    public ModelAndView newComment(HttpServletRequest request,
                                   @Valid @ModelAttribute CommentModel commentModel,
                                   BindingResult bindingResult) {
        String articleId = commentModel.getArticleId();
        LOGGER.info("model {}", articleId);
        ModelAndView mav = new ModelAndView("/article");

        if (!NumberUtils.isNumber(commentModel.getArticleId()) || bindingResult.hasErrors()) {
            mav.addAllObjects(bindingResult.getModel());

            return mav;
        }

        final Article article = articleService.getById(Long.parseLong(articleId));

        if (article == null) {
            commentModel.setErrorResponse(new KpErrorResponse(KpErrors.NOT_FOUND));
            mav.addObject("newComment", commentModel);
            mav.addAllObjects(bindingResult.getModel());

            return KpUtil.redirectToMAV(mav, request.getRequestURI());
        }

        LOGGER.info("model {}", commentModel.getMessage());
        mav.addObject("persistedComment", createNewComment(commentModel, article));

        return KpUtil.redirectToMAV(mav, article.buildUrl());
    }

    @RequestMapping(value = "/yorum/tekrar-yeni", method = RequestMethod.POST)
    @ResponseBody
    public ReplyCommentModel replyComment(@Valid @ModelAttribute ReplyCommentModel replyCommentModel,
                                          BindingResult bindingResult) {
        if (!NumberUtils.isNumber(replyCommentModel.getCommentId()) || bindingResult.hasErrors()) {
            LOGGER.info("comment model id {}", replyCommentModel.getCommentId());
            replyCommentModel.setErrorResponse(new KpErrorResponse(bindingResult));
            return replyCommentModel;
        }
        final Optional<Comment> persistedComment = commentService.getById(Long.valueOf(replyCommentModel.getCommentId()));
        if (persistedComment.isPresent()) {
            LOGGER.info("reply comment model {}", replyCommentModel.getMessage());
            Comment parentComment = persistedComment.get();
            createNewComment(replyCommentModel, parentComment.getArticle(), parentComment);
            return replyCommentModel;
        }
        replyCommentModel.setErrorResponse(new KpErrorResponse(KpErrors.NOT_FOUND));
        return replyCommentModel;
    }


    private Comment createNewComment(BaseCommentModel baseCommentModel, Article article, Comment parentComment) {
        Comment newComment = getComment(baseCommentModel, article);
        newComment.setParent(parentComment);
        return saveComment(newComment);
    }

    private Comment createNewComment(BaseCommentModel baseCommentModel, Article article) {
        Comment newComment = getComment(baseCommentModel, article);
        return saveComment(newComment);
    }

    private Comment saveComment(Comment newComment) {
        return commentService.save(newComment);
    }

    private Comment getComment(BaseCommentModel baseCommentModel, Article article) {
        Comment newComment = new Comment();
        newComment.setArticle(article);
        newComment.setContent(baseCommentModel.getMessage());
        newComment.setCreatedate(dateUtils.now());
        User user = findOrCreateGuestUser(baseCommentModel.getEmail());
        newComment.setUser(user);
        return newComment;
    }

    private User findOrCreateGuestUser(String email) {
        return authenticationService.isKpAuthenticated() ? authenticationService.getCurrentUser() :
                userService.createGuest(email);
    }
}
