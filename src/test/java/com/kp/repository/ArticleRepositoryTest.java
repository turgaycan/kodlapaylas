//package com.kp.repository;
//
//import com.kp.config.RepositoryConfig;
//import com.kp.config.RepositoryTestConfig;
//import com.kp.domain.Article;
//import com.kp.domain.ArticleType;
//import com.kp.domain.User;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Optional;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//
///**
// * Created by turgaycan on 9/26/15.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = RepositoryTestConfig.class)
//public class ArticleRepositoryTest {
//
//    @Autowired
//    private ArticleRepository repository;
//
//    Article article1;
//    Article article2;
//    Article article3;
//
//    @Before
//    public void setUp() {
////        article1 = new Article(1l, "content1", "title1", "tags1", new Date(), new ArticleType(1l, "name"), new User(1l, "email1", "username1", "pass"));
////        article2 = new Article(2l, "content2", "title2", "tags2", new Date(), new ArticleType(1l, "name"), new User(2l, "email2", "username2", "pass"));
////        article3 = new Article(3l, "content3", "title3", "tags3", new Date(), new ArticleType(2l, "name1"), new User(3l, "email3", "username3", "pass"));
////        repository.save(Arrays.asList(article1, article2, article3));
//    }
//
//    @After
//    public void destroy(){
////        repository.deleteAll();
//    }
//
//    @Test
//    public void testFindArticleById() {
//
////        Article actualArticle = repository.findOne(1l);
//
////        assertTrue(actualArticle.isPresent());
////        Article article = actualArticle.get();
////        assertThat(actualArticle, is(article1));
////        assertThat(actualArticle.getUser(), is(article1.getUser()));
//
//    }
//}