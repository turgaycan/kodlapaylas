package com.kp.repository;

import com.kp.domain.Article;
import com.kp.domain.ArticleType;
import com.kp.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by turgaycan on 9/26/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    Article article1;
    Article article2;
    Article article3;

    @Before
    public void setUp() {
        article1 = new Article(1l, "content1", "title1", "tags1", new Date(), new ArticleType(1l, "name"), new User(1l, "email1", "pass"));
        article2 = new Article(2l, "content2", "title2", "tags2", new Date(), new ArticleType(1l, "name"), new User(2l, "email2", "pass"));
        article3 = new Article(3l, "content3", "title3", "tags3", new Date(), new ArticleType(2l, "name1"), new User(3l, "email3", "pass"));
        repository.save(Arrays.asList(article1, article2, article3));
    }

    @After
    public void destroy() {
        repository.deleteAll();
    }

    @Test
    public void testFindArticleById() {

        Article actualArticle = repository.findOne(1l);

        assertFalse(actualArticle.isDeleted());
        assertThat(actualArticle, is(article1));
        assertThat(actualArticle.getUser(), is(article1.getUser()));

    }
}