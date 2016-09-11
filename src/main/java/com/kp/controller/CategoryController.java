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

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

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

    @RequestMapping(value = "/{categoryName:[A-Za-z0-9]+}/p{pageNum:\\d+$}", method = RequestMethod.GET)
    public ModelAndView listCategoryArticles(@PathVariable String categoryName, @PathVariable Integer pageNum) {
        int pageIndex = pageIndex(pageNum);
        return getModelAndView(categoryName, pageIndex);
    }

    @RequestMapping(value = "/kp", method = RequestMethod.GET)
    public ModelAndView listAllCategoryArticles() {
        return listALlCategoryArticles(PagingDTO.DEFAULT_PAGE - 1);
    }

    @RequestMapping(value = "/kp/p{pageNum:\\d+$}", method = RequestMethod.GET)
    public ModelAndView listALlCategoryArticles(@PathVariable Integer pageNum) {
        int pageIndex = pageIndex(pageNum);
        final Page<Article> articlePages = articleService.findArticlesAsPageable(pageIndex, PagingDTO.DEFAULT_PAGE_SIZE);
        return pageModelAndView(KpUrlPaths.CATEGORY_VIEW, articlePages, "kp");
    }

    @Override
    protected ModelAndView getModelAndView(String categoryName, Integer page) {
        final ArticleType rootArticleType = articleTypeService.findByName(categoryName);
        if (rootArticleType == null) {
            LOGGER.info("Category not found : {}  ", categoryName);
            return KpUtil.redirectToMAV("/error");
        }

        List<ArticleType> articleTypeList = newArrayList(rootArticleType);

        if (rootArticleType.isRoot() && !rootArticleType.isChild()) {
            articleTypeList.remove(rootArticleType);
            articleTypeList.addAll(articleTypeService.findByParentId(rootArticleType.getId()));
        }

        final Page<Article> articlePages = articleService.findByArticleTypeIn(articleTypeList, page, PagingDTO.DEFAULT_PAGE_SIZE);
        return pageModelAndView(KpUrlPaths.CATEGORY_VIEW, articlePages, categoryName);
    }

    @Override
    protected String buildPageUrl(String categoryName) {
        return KpUrlPaths.buildCategoryUrl(categoryName);
    }

    @Override
    protected List<Integer> addArchiveYearsToMav() {
        return dateUtils.possibleArchiveYears();
    }
}
