package com.kp.repository;

import com.google.common.collect.Lists;
import com.kp.config.RepositoryTest;
import com.kp.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by turgaycan on 9/27/15.
 */

@RunWith(SpringRunner.class)
@RepositoryTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void testFindRootArticleTypes() {

        Category category1 = new Category(1l, "type1", null);
        Category category12 = new Category(2l, "child_type1", category1);

        repository.save(Lists.newArrayList(category1, category12));
        repository.flush();

        List<Category> rootCategories = repository.findRootCategories();

        assertThat(rootCategories.size(), is(1));
        assertThat(rootCategories.get(0).getId(), is(1l));
        assertThat(rootCategories.get(0).getName(), is("type1"));
    }
}