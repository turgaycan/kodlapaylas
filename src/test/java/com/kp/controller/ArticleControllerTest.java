package com.kp.controller;

import com.kp.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by turgaycan on 9/28/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest {

    @InjectMocks
    private ArticleController controller;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    public void testArticle() throws Exception {

    }
}