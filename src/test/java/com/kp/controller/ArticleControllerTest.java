package com.kp.controller;

import com.kp.domain.Article;
import com.kp.domain.model.seo.SeoMetaData;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by turgaycan on 9/28/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest extends CommonControllerTest {

    @InjectMocks
    private ArticleController controller;

    @Test
    public void shouldRedirectToErrorPageWhenShowOneIfArticleIdNotNumeric() throws IOException {
        final ModelAndView mav = controller.article("title", "1a", new MockHttpServletRequest());

        assertEquals("redirect:/error", mav.getViewName());
    }

    @Test
    public void shouldRedirectToErrorPageWhenShowOneIfArticleIdNotFound() throws IOException {
        when(articleService.getById(1l)).thenReturn(null);
        final ModelAndView mav = controller.article("title", "1", new MockHttpServletRequest());

        assertEquals("redirect:/error", mav.getViewName());
    }

    @Test
    public void shouldShowOnePage() throws IOException {
        final Article article = articleList.get(0);
        when(articleService.getById(1l)).thenReturn(article);
        when(articleService.save(article)).thenReturn(article);
        final ArrayList<Article> recentArticles = Lists.newArrayList(articleList.subList(1, 4));
        when(articleService.getRecentArticles(article, Article.RECENT_ARTICLE_LIMIT)).thenReturn(recentArticles);
        when(articleService.getRelatedArticles(recentArticles, article, Article.RECENT_ARTICLE_LIMIT)).thenReturn(recentArticles);
        when(commentService.buildCommentModel(article)).thenReturn(commentBaseModel);
        final SeoMetaData seoMetaData = new SeoMetaData("title", "keywords", "description");
        when(seoMetaDataService.buildPageSeoMetaData(eq("article"), any(Map.class))).thenReturn(seoMetaData);
        when(tagService.getByCategory(article.getCategory())).thenReturn(tagList);

        final ModelAndView mav = controller.article("title", "1", new MockHttpServletRequest());

        assertEquals("article", mav.getViewName());
        assertEquals(article, mav.getModel().get("article"));
        assertEquals(recentArticles, mav.getModel().get("relatedArticles"));
        assertEquals(commentBaseModel, mav.getModel().get("commentBaseModel"));
        assertEquals(seoMetaData, mav.getModel().get("seoMetaData"));
        assertEquals(tagList, mav.getModel().get("tags"));
    }
}