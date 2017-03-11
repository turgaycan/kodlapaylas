package com.kp.controller.admin;

import com.kp.controller.util.KpControllerUtil;
import com.kp.domain.Article;
import com.kp.domain.Comment;
import com.kp.domain.User;
import com.kp.dto.ArticleModel;
import com.kp.dto.ArticleUpdateInfo;
import com.kp.service.article.ArticleService;
import com.kp.service.article.CategoryService;
import com.kp.service.article.CommentService;
import com.kp.service.security.AuthenticationService;
import com.kp.util.DateUtils;
import com.kp.util.KpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by tcan on 06/02/17.
 */
@PreAuthorize("hasAuthority('ADMIN')")
@Controller
public class ArticleManagementController {

    private static final int DEFAULT_PAGESIZE = 10;
    private static final int DEFAULT_PAGEINDEX = 1;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public ModelAndView list() {
        final List<Article> articles = articleService.getAll();
        ModelAndView mav = new ModelAndView("/admin/articles");
        mav.addObject("articles", articles);
        return mav;
    }

    @RequestMapping(value = {"/articles/{pageIndex:\\d+$}/{pageSize:\\d+$}"}, method = RequestMethod.GET)
    public ModelAndView list(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {

        if (pageIndex <= 0) {
            pageIndex = DEFAULT_PAGEINDEX;
        }
        if (pageSize == 0) {
            pageSize = DEFAULT_PAGESIZE;
        }

        final Page<Article> articles = articleService.getArticlesAsPageable(pageIndex - 1, pageSize);
        ModelAndView mav = new ModelAndView("/admin/articles");
        mav.addObject("articles", articles.getContent());
        return mav;
    }

    @RequestMapping(value = "/show/article/{id:\\d+$}", method = RequestMethod.GET)
    public ModelAndView showOne(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/article");
        final Article article = articleService.getOne(id);
        mav.addObject("article", article);
        final List<Comment> comments = commentService.getCommentsByArticleId(id);
        mav.addObject("commentsCount", comments.size());
        return mav;
    }

    @RequestMapping(value = "/edit/article/{id:\\d+$}", method = RequestMethod.GET)
    public ModelAndView editOne(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/article-edit");
        final Article article = articleService.getOne(id);
        mav.addObject("article", article);
        mav.addObject("articleTypes", categoryService.getAll());
        return mav;
    }

    @RequestMapping(value = "/update/article", method = RequestMethod.POST)
    public ModelAndView updateOne(@Valid @ModelAttribute("articleUpdateInfo") ArticleUpdateInfo articleUpdateInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return KpControllerUtil.buildErrorMav(bindingResult, new ModelAndView("/admin/article-edit"));
        }

        final Article article = articleService.getById(articleUpdateInfo.getId());

        article.setTitle(articleUpdateInfo.getTitle());
        article.setContent(articleUpdateInfo.getContent());
        article.setTags(articleUpdateInfo.getTags());
        article.setArticleStatus(articleUpdateInfo.getArticleStatus());
        article.setCategory(articleUpdateInfo.getCategory());
        article.setModifydate(dateUtils.now());
        article.setUrl(article.createUrl());

        articleService.save(article);

        final String showArticleUrl = "/show/article/" + articleUpdateInfo.getId();
        return KpUtil.redirectToMAV(showArticleUrl);
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newOne() {
        final ModelAndView modelAndView = new ModelAndView("/admin/new-article");
        modelAndView.addObject("articleTypes", categoryService.getAll());
        modelAndView.addObject("article", new ArticleModel());
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("articleModel") ArticleModel articleModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return KpControllerUtil.buildErrorMav(bindingResult, new ModelAndView("/admin/new-article"));
        }

        final User currentUser = authenticationService.getCurrentUser();
        Article article = articleModel.buildNewArticle(currentUser);
        final Article persistedArticle = articleService.save(article);

        final String showArticleUrl = "/show/article/" + persistedArticle.getId();
        return KpUtil.redirectToMAV(showArticleUrl);
    }
}
