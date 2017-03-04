package com.kp.controller;

import com.kp.controller.base.PageController;
import com.kp.domain.Article;
import com.kp.domain.model.dto.PagingDTO;
import com.kp.service.article.ArticleService;
import com.kp.util.KpUrlPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by tcan on 18/02/16.
 */
@RequestMapping(value = "/tag")
@Controller
public class TagController extends PageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/{tagName}", method = RequestMethod.GET)
    public ModelAndView listCategoryArticles(@PathVariable String tagName) {
        return getModelAndView(tagName, PagingDTO.DEFAULT_PAGE - 1);
    }

    @RequestMapping(value = "/{tagName}/p{pageNum:\\d+$}", method = RequestMethod.GET)
    public ModelAndView listCategoryArticles(@PathVariable String tagName, @PathVariable Integer pageNum) {
        int pageIndex = pageIndex(pageNum);
        return getModelAndView(tagName, pageIndex);
    }

    @Override
    protected String buildPageUrl(String url) {
        return KpUrlPaths.buildTagUrl(url);
    }

    @Override
    protected ModelAndView getModelAndView(String tagName, Integer pageNum) {
        final Page<Article> articlePages = articleService.getArticlesByTagAsPageable(tagName, pageNum, PagingDTO.DEFAULT_PAGE_SIZE);
        LOGGER.info("articlePages {} ", articlePages.getTotalElements());
        return pageModelAndView(KpUrlPaths.CATEGORY_VIEW, articlePages, tagName);
    }

    @Override
    protected List<Integer> addArchiveYearsToMav() {
        return dateUtils.possibleArchiveYears();
    }
}
