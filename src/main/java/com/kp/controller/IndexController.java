package com.kp.controller;

import com.kp.controller.base.CommonController;
import com.kp.domain.Category;
import com.kp.service.article.ArticleService;
import com.kp.service.article.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * Created by turgaycan on 9/20/15.
 */
@Controller
public class IndexController extends CommonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private static final long PROGRAMMING_ID = 5l;
    private static final long DATABASE_ID = 7l;
    private static final String ACTIVITIES = "Etkinlikler";
    private static final int PAGENUM = 1;
    private static final int PAGESIZE = 1;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() throws IOException {
        LOGGER.info("Getting home page");
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("lastArticle", articleService.getLastOne());
        final Category activities = new Category(19l, ACTIVITIES);
        mav.addObject("latestEvents", articleService.getByCategory(activities, PAGENUM, PAGESIZE).getContent());
        mav.addObject("mostViewsOfProgramming", articleService.getByCategoryOrderByViewNumber(PROGRAMMING_ID));
        mav.addObject("mostViewsOfDatabase", articleService.getByCategoryOrderByViewNumber(DATABASE_ID));
        mav.addObject("tags", tagService.getByOrderCountDesc());
        addSeoMetaDataToMav(mav);
        addArchiveYearsToMav(mav);
        return mav;
    }


    @RequestMapping(value = {"/hakkimda"}, method = RequestMethod.GET)
    public ModelAndView aboutUs() {
        LOGGER.info("Getting about us..");
        final ModelAndView mav = new ModelAndView("aboutus");
        return mav;
    }

    @RequestMapping(value = {"/iletisim"}, method = RequestMethod.GET)
    public ModelAndView contactMe() {
        LOGGER.info("Getting contact me..");
        final ModelAndView mav = new ModelAndView("contact");
        return mav;
    }

    @RequestMapping(value = {"/hata"}, method = RequestMethod.GET)
    public ModelAndView error() {
        LOGGER.info("Getting error");
        final ModelAndView mav = new ModelAndView("/error");
        return mav;
    }

    @Override
    protected List<Integer> addArchiveYearsToMav() {
        return dateUtils.possibleArchiveYears();
    }
}
