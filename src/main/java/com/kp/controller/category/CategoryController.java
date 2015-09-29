package com.kp.controller.category;

import com.kp.domain.Article;
import com.kp.domain.ArticleType;
import com.kp.service.article.ArticleService;
import com.kp.service.article.ArticleTypeService;
import com.kp.util.KpUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


/**
 * Created by turgaycan on 9/29/15.
 */
@RequestMapping(value = "/{categoryName:[A-Za-z0-9-|_|.|~]+}")
@Controller
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    protected static final int DEFAULT_PAGE = 1;
    protected static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView constructView(@PathVariable("categoryName") String categoryName) {
        final Optional<ArticleType> category = articleTypeService.findByName(categoryName);
        if (!category.isPresent()) {
            return KpUtil.redirectToMAV("error/404");
        }
        ModelAndView mav = new ModelAndView("kategori");
        final List<Article> articleList = articleService.findByArticleType(category.get(), DEFAULT_PAGE, DEFAULT_PAGE_SIZE);
        mav.addObject("articles", articleList);
        return mav;
    }

    @RequestMapping(value = {"/{pageNum:\\d+$}", "/{pageNum:\\d+$}"}, method = RequestMethod.GET)
    public ModelAndView findJavaFeedMessagesByPaging(@PathVariable("categoryName") String categoryName, @PathVariable("pageNum") String pageNum) {
        if (!NumberUtils.isNumber(pageNum)) {
            LOGGER.error("Page Number format exception {}", pageNum);
            return KpUtil.redirectToMAV("/error/301");
        }
        final Optional<ArticleType> category = articleTypeService.findByName(categoryName);
        if (!category.isPresent()) {
            return KpUtil.redirectToMAV("error/404");
        }

        int pageNumber = Integer.parseInt(pageNum);
        if (pageNumber <= 0) {
            pageNumber = DEFAULT_PAGE;
        }

        ModelAndView mav = new ModelAndView("kategori");
        final List<Article> articleList = articleService.findByArticleType(category.get(), pageNumber, DEFAULT_PAGE_SIZE);
        mav.addObject("articles", articleList);

        return mav;
    }
}
