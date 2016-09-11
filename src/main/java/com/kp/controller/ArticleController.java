package com.kp.controller;

import com.kp.controller.base.CommonController;
import com.kp.domain.Article;
import com.kp.util.KpUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Created by turgaycan on 9/26/15.
 */
@Controller
public class ArticleController extends CommonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @RequestMapping(value = "/{title:[A-Za-z0-9-|_|.|~]+}-{articleId:\\d+$}", method = RequestMethod.GET)
    public ModelAndView article(@PathVariable("title") String title, @PathVariable("articleId") String articleId,
                                HttpServletRequest request) {
        String url = "/error";
        if (!NumberUtils.isNumber(articleId)) {
            LOGGER.error("numeric failed..");
            return KpUtil.redirectToMAV(url);
        }

        Article currentArticle = articleService.findById(Long.valueOf(articleId));
        if (currentArticle == null) {
            return KpUtil.redirectToMAV(url);
        }

        currentArticle = articleService.save(currentArticle);

        ModelAndView mav = new ModelAndView("article");
        mav.addObject("article", currentArticle);
        populateCommonsForArticle(mav, currentArticle);
        return mav;
    }


    @RequestMapping(value = "/kp/articles/{page:\\d+$}", method = RequestMethod.GET)
    public ModelAndView articles(@PathVariable("page") Long page) {

        return new ModelAndView("/kp/articles");
    }

    @Override
    protected List<Integer> addArchiveYearsToMav() {
        return dateUtils.possibleArchiveYears();
    }

}
