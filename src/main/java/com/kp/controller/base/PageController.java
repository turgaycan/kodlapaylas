package com.kp.controller.base;

import com.google.common.collect.Lists;
import com.kp.domain.Article;
import com.kp.domain.base.BaseEntity;
import com.kp.domain.model.dto.PagingDTO;
import com.kp.service.article.ArticleService;
import com.kp.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by tcan on 04/10/15.
 */
public abstract class PageController extends CommonController{

    protected static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Autowired
    protected DateUtils dateUtils;

    protected abstract String buildPageUrl(String url);

    protected abstract ModelAndView getModelAndView(String page, Integer pageNum);

    protected int pageIndex(Integer pageNum) {
        if (pageNum == null || pageNum <= 0) {
            return PagingDTO.DEFAULT_PAGE;
        }
        return pageNum;
    }

    protected int archiveYear(Integer year) {
        if (year == null || !dateUtils.isValidYear(year)) {
            return dateUtils.currentYear();
        }
        return year;
    }

    protected ModelAndView pageModelAndView(String view, Page<Article> articlePages, String url) {
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("pageArticles", articlePages.getContent());
        mav.addObject("pagingDTO", PagingDTO.newInstance().buildPagingDTO(articlePages));
        mav.addObject("pageUrl", buildPageUrl(url));
        mav.addObject("years", Lists.newArrayList());
        populateCommons(mav);
        return mav;
    }

}
