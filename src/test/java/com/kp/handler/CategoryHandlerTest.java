package com.kp.handler;

import com.kp.domain.Category;
import com.kp.dto.CategoryUIModel;
import com.kp.service.article.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Created by tcan on 26/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryHandlerTest {

    @InjectMocks
    private CategoryHandler handler;

    @Mock
    private CategoryService categoryService;

    @Test
    public void shouldListRootCategories() {
        when(categoryService.getAllRootCategories()).thenReturn(newArrayList(new Category()));
        final ModelAndView mav = handler.listRootCategories();

        assertEquals("contents/commons/root-categories", mav.getViewName());
        final List<Category> categories = (List<Category>) mav.getModel().get("categories");
        assertEquals(1, categories.size());
    }

    @Test
    public void shouldBuildCategoryUiModelsCategories() {
        final ArrayList<Category> categories = newArrayList(new Category(), new Category());
        when(categoryService.getAllRootCategories()).thenReturn(categories);
        when(categoryService.getAll()).thenReturn(categories);
        final ModelAndView mav = handler.buildCategoryUiModelsCategories();

        assertEquals("contents/commons/all-categories", mav.getViewName());
        final List<CategoryUIModel> categoryUIModels = (List<CategoryUIModel>) mav.getModel().get("categoryUIModels");
        assertEquals(2, categoryUIModels.size());
    }

}