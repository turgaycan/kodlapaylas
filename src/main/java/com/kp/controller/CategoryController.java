package com.kp.controller;

import com.kp.domain.Article;
import com.kp.domain.ArticleType;
import com.kp.domain.model.dto.PagingDTO;
import com.kp.service.article.ArticleService;
import com.kp.service.article.ArticleTypeService;
import com.kp.util.KpUrlPaths;
import com.kp.util.KpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/kategori")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping(value = "/{categoryName:[A-Za-z0-9]+}", method = RequestMethod.GET)
    public ModelAndView listCategoryArticles(@PathVariable String categoryName) {
        return getModelAndView(categoryName, PagingDTO.DEFAULT_PAGE);
    }

    @RequestMapping(value = "/{categoryName:[A-Za-z0-9]+}/{pageNum:\\d+$}", method = RequestMethod.GET)
    public ModelAndView listCategoryArticles(@PathVariable String categoryName, @PathVariable Integer pageNum) {
        int pageIndex = getPageIndex(pageNum);
        return getModelAndView(categoryName, pageIndex);
    }

    private int getPageIndex(Integer pageNum) {
        if (pageNum == null || pageNum <= 0) {
            return PagingDTO.DEFAULT_PAGE;
        }
        return pageNum;
    }

    private ModelAndView getModelAndView(@PathVariable String categoryName, int page) {
        final Optional<ArticleType> category = articleTypeService.findByName(categoryName);
        if (!category.isPresent()) {
            return KpUtil.redirectToMAV("/error/404");
        }

        ModelAndView mav = new ModelAndView("/kategori");
        final Page<Article> articlePages = articleService.findByArticleType(category.get(), page, PagingDTO.DEFAULT_PAGE_SIZE);
        mav.addObject("pageArticles", articlePages.getContent());
        mav.addObject("pagingDTO", PagingDTO.newInstance().buildPagingDTO(articlePages));
        mav.addObject("pageUrl", KpUrlPaths.buildCategoryUrl(categoryName));
        return mav;
    }

}
