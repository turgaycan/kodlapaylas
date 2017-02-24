package com.kp.repository;

import com.google.common.collect.Lists;
import com.kp.config.RepositoryTest;
import com.kp.domain.ArticleType;
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
public class ArticleTypeRepositoryTest {

    @Autowired
    private ArticleTypeRepository repository;

    @Test
    public void testFindRootArticleTypes() {

        ArticleType articleType1 = new ArticleType(1l, "type1", null);
        ArticleType articleType12 = new ArticleType(2l, "child_type1", articleType1);

        repository.save(Lists.newArrayList(articleType1, articleType12));
        repository.flush();

        List<ArticleType> rootArticleTypes = repository.findRootArticleTypes();

        assertThat(rootArticleTypes.size(), is(1));
        assertThat(rootArticleTypes.get(0).getId(), is(1l));
        assertThat(rootArticleTypes.get(0).getName(), is("type1"));
    }
}