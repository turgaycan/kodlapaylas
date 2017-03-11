package com.kp.service.article;

import com.kp.domain.Category;
import com.kp.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by turgaycan on 9/27/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository categoryRepository;

    private Category category1;
    private Category category2;
    private Category category3;
    private Category category4;
    private Category childCategory1;

    @Before
    public void init() {
        category1 = new Category(1l, "type1", null);
        category2 = new Category(2l, "type1", null);
        category3 = new Category(3l, "type1", null);
        category4 = new Category(4l, "type1", null);
        childCategory1 = new Category(5l, "child type1", category1);
    }

    @Test
    public void shouldFindAll() {
        when(categoryRepository.findRootArticleTypes()).thenReturn(newArrayList(category1, category2, category3, category4));

        List<Category> allRootTypes = service.getAllRootTypes();

        assertThat(allRootTypes.size(), is(4));
        assertThat(allRootTypes, hasItems(category1, category2, category3, category4));
    }

    @Test
    public void shouldGetByName() {
        when(categoryRepository.findByName("type1")).thenReturn(Optional.ofNullable(category1));

        final Category actual = service.getByName("type1");

        assertEquals(Long.valueOf(1), actual.getId());
    }

    @Test
    public void shouldGetAll() {
        when(categoryRepository.findAll()).thenReturn(newArrayList(category1, category2, category3, category4, childCategory1));

        final List<Category> all = service.getAll();

        assertEquals(5, all.size());
    }

    @Test
    public void shouldGetByParentId() {
        when(categoryRepository.findByParentId(1l)).thenReturn(newArrayList(childCategory1));
        final List<Category> categoryList = service.getByParentId(1l);

        assertEquals(1, categoryList.size());
        assertEquals(Long.valueOf(5l), categoryList.get(0).getId());
        assertEquals("child type1", categoryList.get(0).getName());
    }

}