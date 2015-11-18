package com.kp.handler;


import com.kp.service.article.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tcan on 13/10/15.
 */

@Controller
public class FeatureArticlesHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureArticlesHandler.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/feature-articles", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView listFeatureArticles() {
        ModelAndView mav = new ModelAndView("/contents/commons/feature-articles");
        LOGGER.info("Feature Articles page");
        mav.addObject("featureArticles", articleService.findFeatureArticles());
        return mav;
    }
}
