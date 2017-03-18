package com.kp.controller;

import com.kp.domain.Category;
import com.kp.domain.model.dto.PagingDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;


/**
 * Created by tcan on 13/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest extends CommonControllerTest {

    @InjectMocks
    private CategoryController controller;

    @Test
    public void shouldNotListCategoryArticlesByCategoryNameIfCategoryNotFound() {
        when(categoryService.getByName("cat1")).thenReturn(null);
        final ModelAndView mav = controller.listCategoryArticles("cat1");

        assertEquals("redirect:/error", mav.getViewName());
    }

    @Test
    public void shouldListCategoryArticlesByCategoryName() {
        final Category category = categoryList.get(0);
        when(categoryService.getByName("cat1")).thenReturn(category);
        when(articleService.getByCategoryIn(any(List.class), eq(0), eq(PagingDTO.DEFAULT_PAGE_SIZE))).thenReturn(new PageImpl(articleList));
        final List<Integer> years = newArrayList(2012, 2013, 2014, 2015);
        when(dateUtils.possibleArchiveYears()).thenReturn(years);
        when(categoryService.getAll()).thenReturn(categoryList);
        when(tagService.getByCategory(articleList.get(0).getCategory())).thenReturn(tagList);

        final ModelAndView mav = controller.listCategoryArticles("cat1");

        assertEquals("/category", mav.getViewName());
        assertEquals(articleList, mav.getModel().get("pageArticles"));
        assertEquals(tagList, mav.getModel().get("tags"));

        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getFirst()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getBegin()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getPrev()));
        assertThat(0, is(((PagingDTO) mav.getModel().get("pagingDTO")).getCurrent()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getNext()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getEnd()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getTotalPages()));

        assertEquals(years, mav.getModel().get("years"));
        assertEquals("/kategori/cat1", mav.getModel().get("pageUrl"));
    }

    @Test
    public void shouldListAllCategoryArticlesAsPageAble() {
        final Category category = categoryList.get(0);
        when(categoryService.getByName("cat1")).thenReturn(category);
        when(articleService.getArticlesAsPageable(1, PagingDTO.DEFAULT_PAGE_SIZE)).thenReturn(new PageImpl(articleList));
        final List<Integer> years = newArrayList(2012, 2013, 2014, 2015);
        when(dateUtils.possibleArchiveYears()).thenReturn(years);
        when(categoryService.getAll()).thenReturn(categoryList);
        when(tagService.getByCategory(articleList.get(0).getCategory())).thenReturn(tagList);

        final ModelAndView mav = controller.listAllCategoryArticles(2);

        assertEquals("/category", mav.getViewName());
        assertEquals(articleList, mav.getModel().get("pageArticles"));
        assertEquals(tagList, mav.getModel().get("tags"));

        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getFirst()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getBegin()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getPrev()));
        assertThat(0, is(((PagingDTO) mav.getModel().get("pagingDTO")).getCurrent()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getNext()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getEnd()));
        assertThat(1, is(((PagingDTO) mav.getModel().get("pagingDTO")).getTotalPages()));

        assertEquals(years, mav.getModel().get("years"));
        assertEquals("/kategori/kp", mav.getModel().get("pageUrl"));
    }
}