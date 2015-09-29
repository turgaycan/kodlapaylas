package com.kp.controller;

import com.google.common.collect.Lists;
import com.kp.domain.Article;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by turgaycan on 9/26/15.
 */
@Controller
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    private static final int RECENT_ARTICLE_LIMIT = 3;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping(value = "/{title:[A-Za-z0-9-|_|.|~]+}-{articleId:\\d+$}", method = RequestMethod.GET)
    public ModelAndView article(@PathVariable("title") String title, @PathVariable("articleId") String articleId,
                                HttpServletRequest request) {
        String url = request.getRequestURI() + "/error";
        if (!NumberUtils.isNumber(articleId)) {
            LOGGER.error("numeric failed..");
            return KpUtil.redirectToMAV(url);
        }

        Optional<Article> currentArticle = articleService.findArticleById(Long.valueOf(articleId));
        if (currentArticle.isPresent()) {
            ModelAndView mav = new ModelAndView("/yazi-detay");
            Article article = currentArticle.get();
            mav.addObject("article", article);
            mav.addObject("articleTags", Lists.newArrayList(article.getTags().split(",")));
            //ComponentHandler ile component yap her ikisini
            mav.addObject("recentArticles", articleService.findRecentArticles(RECENT_ARTICLE_LIMIT));
            mav.addObject("categories", articleTypeService.findAllRootTypes());
            return mav;
        }
        return KpUtil.redirectToMAV(url);
    }
}
