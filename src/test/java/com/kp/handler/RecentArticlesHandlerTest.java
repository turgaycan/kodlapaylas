package com.kp.handler;

import com.kp.domain.Article;
import com.kp.service.article.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 26/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecentArticlesHandlerTest {

    @InjectMocks
    private RecentArticlesHandler handler;

    @Mock
    private ArticleService articleService;

    @Test
    public void shouldNotReturnAnyRecentArticleIfArticleIdNotNumeric() {
        final ModelAndView mav = handler.listRecentArticles("a", new MockHttpServletRequest());

        verify(articleService, never()).getById(any());

        assertEquals("/contents/commons/recent-articles", mav.getViewName());
    }

    @Test
    public void shouldNotReturnAnyRecentArticleIfArticleNotFound() {
        when(articleService.getById(Long.parseLong("1"))).thenReturn(null);
        final ModelAndView mav = handler.listRecentArticles("1", new MockHttpServletRequest());

        assertEquals("/contents/commons/recent-articles", mav.getViewName());
    }

    @Test
    public void shouldNotReturnRecentArticleIfArticleFound() {
        final Article article = new Article();
        when(articleService.getById(Long.parseLong("1"))).thenReturn(article);
        final ArrayList<Article> recents = newArrayList(new Article(), new Article(), new Article());
        when(articleService.getRecentArticles(article, 3)).thenReturn(recents);

        final ModelAndView mav = handler.listRecentArticles("1", new MockHttpServletRequest());

        assertEquals("/contents/commons/recent-articles", mav.getViewName());
        final List<Article> recentArticles = (List<Article>) mav.getModel().get("recentArticles");
        assertEquals(3, recentArticles.size());
        assertEquals(recents, recentArticles);
    }

    @Test
    public void shouldListRecentArticlesForIndex(){
        final ArrayList<Article> articles = newArrayList(new Article(), new Article(), new Article(), new Article());
        when(articleService.getArticlesAsPageable(0, 4)).thenReturn(new PageImpl<>(articles));
        final ModelAndView mav = handler.listRecentArticlesForIndex();

        assertEquals("/contents/commons/recent-articles", mav.getViewName());
        final List<Article> recentArticles = (List<Article>) mav.getModel().get("recentArticles");
        assertEquals(3, recentArticles.size());
    }

}