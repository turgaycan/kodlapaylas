package com.kp.handler;

import com.kp.domain.Article;
import com.kp.service.article.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 20/10/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class FeatureArticlesHandlerTest {

    @InjectMocks
    private FeatureArticlesHandler handler;

    @Mock
    private ArticleService articleService;

    @Test
    public void shouldListFeatureArticles() {
        when(articleService.getFeatureArticles()).thenReturn(newArrayList(new Article()));
        final ModelAndView mav = handler.listFeatureArticles();

        assertEquals("/contents/commons/feature-articles", mav.getViewName());
        final List<Article> featureArticles = (List<Article>) mav.getModel().get("featureArticles");

        assertEquals(1, featureArticles.size());
    }
}