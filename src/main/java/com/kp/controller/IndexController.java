package com.kp.controller;

import com.kp.controller.base.CommonController;
import com.kp.service.article.ArticleService;
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

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        LOGGER.info("Getting home page");
        final ModelAndView mav = new ModelAndView("/index");
        mav.addObject("lastArticle", articleService.findLastOne());
//        populateCommons(mav);
        return mav;
    }
}
