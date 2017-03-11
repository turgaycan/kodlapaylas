package com.kp.repository;

import com.kp.config.RepositoryTest;
import com.kp.domain.Article;
import com.kp.domain.Category;
import com.kp.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@RepositoryTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        final Category category = new Category(1l, "name");
        final Category persistedCategory = categoryRepository.saveAndFlush(category);

        final User user = new User(1l, "email1", "pass", "email1");
        final User persistedUser = userRepository.saveAndFlush(user);

        Article article1 = new Article(1l, "content1", "title1", "tags1", new Date(), persistedCategory, persistedUser);
        article1.setUrl("url1");
        repository.saveAndFlush(article1);
        Article article2 = new Article(2l, "content2", "title2", "tags2", new Date(), persistedCategory, persistedUser);
        article2.setUrl("url2");
        Article article3 = new Article(3l, "content3", "title3", "tags3", new Date(), persistedCategory, persistedUser);
        article3.setUrl("url3");
        repository.save(Arrays.asList(article2, article3));
        repository.flush();
    }

    @Test
    public void testFindArticleById() {

        Article actualArticle = repository.findOne(1l);

        assertFalse(actualArticle.isDeleted());
        assertThat(actualArticle.getId(), is(1l));
        assertThat(actualArticle.getContent(), is("content1"));

    }
}