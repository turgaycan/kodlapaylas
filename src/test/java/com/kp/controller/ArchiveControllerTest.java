package com.kp.controller;

import com.kp.domain.Article;
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
        final Date startDate = new Date(2012,1,1);
        final Date endDate = new Date(2013,1,1);

        final DateRange dateRange = new DateRange(startDate, endDate);

        when(dateUtils.prepareDateRange(Integer.valueOf("2012"))).thenReturn(dateRange);
        when(articleService.getByCreatedateAfterAndCreatedateBefore(dateRange, 0, PagingDTO.DEFAULT_PAGE_SIZE)).thenReturn(new PageImpl<>(newArrayList(new Article(), new Article())));

        final ModelAndView modelAndView = controller.listArchiveArticles("2012");
    }

    @Test
    public void listArchiveArticlesByYear() {

    }

    @Test
    public void listArchiveArticlesByYearAndMonth() {

    }

    @Test
    public void listArchiveArticlesByYear1() {

    }

}