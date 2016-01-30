package com.kp.controller;

import com.kp.controller.base.PageController;
import com.kp.domain.Article;
import com.kp.domain.model.dto.PagingDTO;
import com.kp.dto.DateRange;
import com.kp.util.KpUrlPaths;
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
        mav.addObject("pageUrl", buildPageUrl(possibleArchiveYears.get(0).toString()));
        populateMonths(mav);
        return mav;
    }

    @RequestMapping(value = "/{year:\\d+}", method = RequestMethod.GET)
    public ModelAndView listArchiveArticles(@PathVariable String year) {
        return getModelAndView(year, PagingDTO.DEFAULT_PAGE - 1);
    }

    @RequestMapping(value = "/{year:\\d+}/{pageNum:\\d+$}", method = RequestMethod.GET)
    public ModelAndView listArchiveArticlesByYear(@PathVariable("year") String year,
                                                  @PathVariable Integer pageNum) {
        return getModelAndView(year, pageNum);
    }

    @RequestMapping(value = "/{year:\\d+}/{pageNum:\\d+$}/{month:d+$}", method = RequestMethod.GET)
    public ModelAndView listArchiveArticlesByYear(@PathVariable("year") String year, @PathVariable Integer pageNum, @PathVariable Integer month) {

        DateRange dateRange = dateUtils.dateWithMonthAtEndDay(archiveYear(Integer.valueOf(year)), month);
        return getModelAndView(year, pageNum, dateRange);
    }

    @Override
    protected ModelAndView getModelAndView(String year, Integer pageNum) {
        DateRange dateRange = dateUtils.prepareDateRange(archiveYear(Integer.valueOf(year)));
        return getModelAndView(year, pageNum, dateRange);
    }

    private ModelAndView getModelAndView(String year, Integer pageNum, DateRange dateRange) {
        final Page<Article> archiveArticlePages = articleService.findByCreatedateAfterAndCreatedateBefore(dateRange, pageNum, PagingDTO.DEFAULT_PAGE_SIZE);
        ModelAndView mav = pageModelAndView(KpUrlPaths.ARCHIVE_VIEW, archiveArticlePages, year);
        populateMonths(mav);
        return mav;
    }

    @Override
    protected String buildPageUrl(String url) {
        return KpUrlPaths.buildArchiveUrl(url);
    }

    private void populateMonths(ModelAndView mav) {
        mav.addObject("months", dateUtils.monthUiModels());
    }
}
