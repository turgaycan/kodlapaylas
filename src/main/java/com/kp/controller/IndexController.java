package com.kp.controller;

import com.kp.controller.base.CommonController;
import com.kp.domain.ArticleType;
import com.kp.service.article.ArticleService;
import com.kp.service.article.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by turgaycan on 9/20/15.
 */
@Controller
public class IndexController extends CommonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private static final long PROGRAMMING_ID = 5l;
    private static final long DATABASE_ID = 7l;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        LOGGER.info("Getting home page");
        final ModelAndView mav = new ModelAndView("/index");
        mav.addObject("lastArticle", articleService.findLastOne());
        //TODO turgay : refactor..
        mav.addObject("latestEvents", articleService.findByArticleType(new ArticleType(19l, "Etkinlikler"), 1, 1).getContent());
        mav.addObject("mostViewsOfProgramming", articleService.findByArticleTypeOrderByViewNumber(PROGRAMMING_ID));
        mav.addObject("mostViewsOfDatabase", articleService.findByArticleTypeOrderByViewNumber(DATABASE_ID));
        mav.addObject("tags", tagService.findByOrderCountDesc());
//        populateCommons(mav);
        return mav;
    }
}
