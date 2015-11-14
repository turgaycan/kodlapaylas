package com.kp.service.article;

import com.kp.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by tcan on 04/10/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceTest {

    @InjectMocks
    private ArticleService service;

    @Mock
    private DateUtils dateUtils;

    @Test
    public void testFindArticleById() throws Exception {

    }

    @Test
    public void testFindRecentArticles() throws Exception {

    }

    @Test
    public void testFindByArticleType() throws Exception {

    }

    @Test
    public void testFindByCreateDateBetween() throws Exception {

    }
}