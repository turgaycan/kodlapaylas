package com.kp.controller;

import com.kp.domain.Tag;
import com.kp.domain.model.dto.PagingDTO;
import com.kp.dto.DateRange;
import com.kp.dto.MonthUIModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 18/09/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArchiveControllerTest extends CommonControllerTest {

    @InjectMocks
    private ArchiveController controller;

    @Test
    public void shouldListArchiveArticles() {
        when(dateUtils.possibleArchiveYears()).thenReturn(newArrayList(2012, 2013, 2014, 2015, 2016));
        when(dateUtils.monthUiModels()).thenReturn(newArrayList(new MonthUIModel(1, "Ocak")));

        final ModelAndView modelAndView = controller.listArchiveArticles();

        final List<Integer> years = (List<Integer>) modelAndView.getModel().get("years");
        assertThat(years.size(), is(5));
        assertThat(years.get(0), is(2012));
        assertThat(years.get(4), is(2016));

        final String pageUrl = (String) modelAndView.getModel().get("pageUrl");

        assertThat(pageUrl, is("/arsiv/2012"));

        final List<MonthUIModel> months = (List<MonthUIModel>) modelAndView.getModel().get("months");

        assertThat(months.size(), is(1));
        assertThat(months.get(0).getMonthValue(), is(1));
        assertThat(months.get(0).getMonthName(), is("Ocak"));

    }

    @Test
    public void shouldListArchiveArticlesBySelectedMonth() {
        final Date startDate = new Date(2012, 1, 1);
        final Date endDate = new Date(2013, 1, 1);

        final DateRange dateRange = new DateRange(startDate, endDate);

        when(dateUtils.prepareDateRange(Integer.valueOf("2012"))).thenReturn(dateRange);
        when(dateUtils.isNotValidYear(2012)).thenReturn(true);
        when(dateUtils.currentYear()).thenReturn(2012);
        when(articleService.getByCreatedateAfterAndCreatedateBefore(dateRange, 0, PagingDTO.DEFAULT_PAGE_SIZE)).thenReturn(new PageImpl<>(articleList));
        when(categoryService.getAll()).thenReturn(categoryList);
        when(tagService.getByCategory(categoryList.get(0))).thenReturn(tagList);

        final ModelAndView mav = controller.listArchiveArticles("2012");

        assertThat(articleList, is(mav.getModel().get("pageArticles")));

        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getFirst()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getBegin()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getPrev()));
        assertThat(0, is(((PagingDTO) mav.getModel().get("pagingDTO")).getCurrent()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getNext()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getEnd()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getTotalPages()));

        assertThat("/arsiv/2012", is(mav.getModel().get("pageUrl")));
        assertThat(0, is(((List<Integer>) mav.getModel().get("years")).size()));
        assertThat(tagList, is((List<Tag>) mav.getModel().get("tags")));
    }

    @Test
    public void shouldlistArchiveArticlesByYear() {
        final Date startDate = new Date(2012, 1, 1);
        final Date endDate = new Date(2013, 1, 1);

        final DateRange dateRange = new DateRange(startDate, endDate);

        when(dateUtils.prepareDateRange(Integer.valueOf("2012"))).thenReturn(dateRange);
        when(dateUtils.isNotValidYear(2012)).thenReturn(true);
        when(dateUtils.currentYear()).thenReturn(2012);
        when(articleService.getByCreatedateAfterAndCreatedateBefore(dateRange, 1, PagingDTO.DEFAULT_PAGE_SIZE)).thenReturn(new PageImpl<>(articleList));
        when(categoryService.getAll()).thenReturn(categoryList);
        when(tagService.getByCategory(categoryList.get(0))).thenReturn(tagList);

        final ModelAndView mav = controller.listArchiveArticlesByYear("2012", "1");

        assertThat(articleList, is(mav.getModel().get("pageArticles")));

        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getFirst()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getBegin()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getPrev()));
        assertThat(0, is(((PagingDTO) mav.getModel().get("pagingDTO")).getCurrent()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getNext()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getEnd()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getTotalPages()));

        assertThat("/arsiv/2012", is(mav.getModel().get("pageUrl")));
        assertThat(0, is(((List<Integer>) mav.getModel().get("years")).size()));
        assertThat(tagList, is((List<Tag>) mav.getModel().get("tags")));
    }

    @Test
    public void shouldNotListArchiveArticlesByYearAndMonthIfGivenParamNotValid() {
        final ModelAndView mav = controller.listArchiveArticlesByYearAndMonth("2012", "month");

        assertEquals("redirect:/error", mav.getViewName());
    }

    @Test
    public void shouldListArchiveArticlesByYearAndMonth() {
        final Date startDate = new Date(2012, 10, 1);
        final Date endDate = new Date(2012, 11, 1);

        final DateRange dateRange = new DateRange(startDate, endDate);

        when(dateUtils.dateWithMonthAtEndDay(Integer.valueOf("2012"), 11)).thenReturn(dateRange);
        when(dateUtils.isNotValidYear(2012)).thenReturn(true);
        when(dateUtils.currentYear()).thenReturn(2012);

        when(articleService.getByCreatedateAfterAndCreatedateBefore(dateRange, 10, PagingDTO.DEFAULT_PAGE_SIZE)).thenReturn(new PageImpl<>(articleList));
        when(categoryService.getAll()).thenReturn(categoryList);
        when(tagService.getByCategory(categoryList.get(0))).thenReturn(tagList);
        when(dateUtils.monthUiModels()).thenReturn(monthUIModels);

        final ModelAndView mav = controller.listArchiveArticlesByYearAndMonth("2012", "10");

        assertThat(articleList, is(mav.getModel().get("pageArticles")));

        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getFirst()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getBegin()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getPrev()));
        assertThat(0, is(((PagingDTO) mav.getModel().get("pagingDTO")).getCurrent()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getNext()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getEnd()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getTotalPages()));

        assertThat("/arsiv/2012", is(mav.getModel().get("pageUrl")));
        assertThat(0, is(((List<Integer>) mav.getModel().get("years")).size()));
        assertThat(tagList, is((List<Tag>) mav.getModel().get("tags")));
        assertThat(monthUIModels, is((List<MonthUIModel>) mav.getModel().get("months")));
    }

}