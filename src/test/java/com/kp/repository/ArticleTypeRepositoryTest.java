package com.kp.repository;

import com.google.common.collect.Lists;
import com.kp.config.RepositoryTestConfig;
import com.kp.domain.ArticleType;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by turgaycan on 9/27/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = RepositoryTestConfig.class)
public class ArticleTypeRepositoryTest {

    @Autowired
    private ArticleTypeRepository repository;

    @Ignore
    @Test
    public void testFindRootArticleTypes() throws Exception {

        ArticleType articleType1 = new ArticleType(1l, "type1", null);
        ArticleType articleType12 = new ArticleType(1l, "type1", articleType1);
        repository.save(Lists.newArrayList(articleType1, articleType12));

//        repository.flush();

        List<ArticleType> rootArticleTypes = repository.findRootArticleTypes();

        assertThat(rootArticleTypes.size(), is(1));


    }
}