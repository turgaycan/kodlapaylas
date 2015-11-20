package com.kp.controller;

import com.kp.controller.base.PageController;
import com.kp.domain.Article;
import com.kp.domain.ArticleType;
import com.kp.domain.model.dto.PagingDTO;
import com.kp.util.KpUrlPaths;
import com.kp.util.KpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Created by turgaycan on 9/29/15.
 */
@Controller
@RequestMapping(value = KpUrlPaths.CATEGORY)
public class CategoryController extends PageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @RequestMapping(value = "/{categoryName:[A-Za-z0-9]+}", method = RequestMethod.GET)
    public ModelAndView listCategoryArticles(@PathVariable String categoryName) {
        return getModelAndView(categoryName, PagingDTO.DEFAULT_PAGE - 1);
    }

    @RequestMapping(value = "/{categoryName:[A-Za-z0-9]+}/{pageNum:\\d+$}", method = RequestMethod.GET)
    public ModelAndView listCategoryArticles(@PathVariable String categoryName, @PathVariable Integer pageNum) {
        int pageIndex = pageIndex(pageNum);
        return getModelAndView(categoryName, pageIndex);
    }

    @Override
    protected ModelAndView getModelAndView(String categoryName, Integer page) {
        final Optional<ArticleType> category = articleTypeService.findByName(categoryName);
        if (!category.isPresent()) {
            LOGGER.info("Category not found : {}  ", categoryName);
            return KpUtil.redirectToMAV("/error");
        }

        final Page<Article> articlePages = articleService.findByArticleType(category.get(), page , PagingDTO.DEFAULT_PAGE_SIZE);
        LOGGER.info("articlePages {} ", articlePages.getTotalElements());
        return pageModelAndView(KpUrlPaths.CATEGORY_VIEW, articlePages, categoryName);
    }

    @Override
    protected String buildPageUrl(String categoryName) {
        return KpUrlPaths.buildCategoryUrl(categoryName);
    }

}
