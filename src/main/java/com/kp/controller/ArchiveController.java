package com.kp.controller;

import com.google.common.collect.Lists;
import com.kp.controller.base.PageController;
import com.kp.domain.Article;
import com.kp.domain.model.dto.PagingDTO;
import com.kp.dto.DateRange;
import com.kp.util.KpUrlPaths;
import com.kp.util.KpUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        final List<Integer> possibleArchiveYears = dateUtils.possibleArchiveYears();
        mav.addObject("years", possibleArchiveYears);
        mav.addObject("pageUrl", buildPageUrl(possibleArchiveYears.get(0).toString()));
        populateMonths(mav);
        return mav;
    }

    @RequestMapping(value = "/{year:\\d+}", method = RequestMethod.GET)
    public ModelAndView listArchiveArticles(@PathVariable String year) {
        return getModelAndView(year, PagingDTO.DEFAULT_PAGE - 1);
    }

    @RequestMapping(value = "/{year:\\d+}/p{pageNum:\\d+$}", method = RequestMethod.GET)
    public ModelAndView listArchiveArticlesByYear(@PathVariable("year") String year,
                                                  @PathVariable("pageNum") String pageNum) {

        return getModelAndView(year, Integer.valueOf(pageNum));
    }

    @RequestMapping(value = "/{year:\\d+}/{month:\\d+$}", method = RequestMethod.GET)
    public ModelAndView listArchiveArticlesByYearAndMonth(@PathVariable("year") String year, @PathVariable("month") String month) {
        final ModelAndView modelAndView = checkIfFailRedirect(year, month);
        if (modelAndView != null) {
            return modelAndView;
        }
        final Integer yearAsInteger = Integer.valueOf(year);
        final Integer monthAsInteger = Integer.valueOf(month);
        DateRange dateRange = dateUtils.dateWithMonthAtEndDay(archiveYear(yearAsInteger), monthAsInteger + 1);
        return getModelAndView(year, monthAsInteger, dateRange);
    }

    @RequestMapping(value = "/{year:\\d+}/{month:\\d+$}/p{pageNum:\\d+$}", method = RequestMethod.GET)
    public ModelAndView listArchiveArticlesByYear(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("pageNum") String pageNum) {
        final ModelAndView modelAndView = checkIfFailRedirect(year, month, pageNum);
        if (modelAndView != null) {
            return modelAndView;
        }
        final int yearAsInteger = archiveYear(Integer.valueOf(year));
        final Integer monthAsInteger = Integer.valueOf(month);
        DateRange dateRange = dateUtils.dateWithMonthAtEndDay(yearAsInteger, monthAsInteger);
        final Integer pageNumber = Integer.valueOf(pageNum);
        return getModelAndView(year, pageNumber, dateRange);
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

    private ModelAndView checkIfFailRedirect(String... params) {
        for (String eachParam : params) {
            if (!NumberUtils.isNumber(eachParam)) {
                return KpUtil.redirectToMAV(new ModelAndView("error"), "/error");
            }
        }
        return null;
    }

    @Override
    protected String buildPageUrl(String url) {
        return KpUrlPaths.buildArchiveUrl(url);
    }

    private void populateMonths(ModelAndView mav) {
        mav.addObject("months", dateUtils.monthUiModels());
    }

    @Override
    protected List<Integer> addArchiveYearsToMav() {
        return Lists.newArrayList();
    }
}
