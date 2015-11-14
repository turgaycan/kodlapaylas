package com.kp.controller;

import com.kp.controller.base.PageController;
import com.kp.domain.Article;
import com.kp.domain.model.dto.PagingDTO;
import com.kp.util.KpUrlPaths;
import com.kp.util.KpUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tcan on 04/10/15.
 */
@Controller
@RequestMapping(value = KpUrlPaths.ARCHIVE)
public class ArchiveController extends PageController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listArchiveArticles() {
        ModelAndView mav = new ModelAndView(KpUrlPaths.ARCHIVE_VIEW);
        List<Integer> possibleArchiveYears = new ArrayList<>();
        for (int index = dateUtils.BORN_YEAR; index <= dateUtils.currentYear(); index++) {
            possibleArchiveYears.add(index);
        }
        mav.addObject("years", possibleArchiveYears);
        return mav;
    }


    @RequestMapping(value = "/{year:\\d+}", method = RequestMethod.GET)
    public ModelAndView listArchiveArticles(@PathVariable String year) {
        LOGGER.info("year : {} ", year);
        return getModelAndView(year, PagingDTO.DEFAULT_PAGE);
    }

    @RequestMapping(value = "/{year:\\d+}/{pageNum:\\d+$", method = RequestMethod.GET)
    public ModelAndView listArchiveArticlesByYear(@PathVariable("year") String year,
                                                  @PathVariable Integer pageNum) {
        return getModelAndView(year, pageNum);
    }

    @Override
    public ModelAndView getModelAndView(String year, Integer pageNum) {
        if (!NumberUtils.isNumber(year)) {
            LOGGER.error("numeric value failed..", pageNum);
            return KpUtil.redirectToMAV(KpUrlPaths.ERROR);
        }

        int currentYear = archiveYear(Integer.getInteger(year));
        final Page<Article> archiveArticlePages = articleService.findByCreatedateAfterAndCreatedateBefore(currentYear, pageNum, PagingDTO.DEFAULT_PAGE_SIZE);
        System.out.println(archiveArticlePages.getContent().size());
        return pageModelAndView(KpUrlPaths.ARCHIVE_VIEW, archiveArticlePages, year);
    }

    @Override
    protected String buildPageUrl(String url) {
        return KpUrlPaths.buildCategoryUrl(KpUrlPaths.buildArchiveUrl(url));
    }
}
