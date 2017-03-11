package com.kp.controller.admin;

import com.kp.domain.Article;
import com.kp.domain.Category;
import com.kp.domain.Comment;
import com.kp.service.article.ArticleService;
import com.kp.service.article.CategoryService;
import com.kp.service.article.CommentService;
import com.kp.service.security.AuthenticationService;
import com.kp.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Created by tcan on 10/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleManagementControllerTest {

    @InjectMocks
    private ArticleManagementController controller;

    @Mock
    private ArticleService articleService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private CommentService commentService;

    @Mock
    private DateUtils dateUtils;

    @Mock
    private AuthenticationService authenticationService;

    private List<Article> articleList = new ArrayList<>();
    private List<Comment> commentList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();

    @Before
    public void init() {
        for (long index = 1; index <= 5; index++) {
            final Article article = new Article(index, "content" + index, "title" + index, "tags" + index, new Date(), null, null);
            articleList.add(article);
            final Comment comment = new Comment(index, null, article);
            commentList.add(comment);
            final Category category = new Category(index, "name" + index, null);
            categoryList.add(category);
        }
    }

    @Test
    public void shouldListArticles() {
        when(articleService.getAll()).thenReturn(articleList);

        final ModelAndView mav = controller.list();

        assertEquals("/admin/articles", mav.getViewName());
        final List<Article> articles = (List<Article>) mav.getModel().get("articles");
        assertEquals(articleList.size(), articles.size());
        final Article actual = articles.get(0);
        assertEquals(actual, actual);
        assertEquals(Long.valueOf(1), actual.getId());
    }


    @Test
    public void shouldListArticlesByIndex() {
        when(articleService.getArticlesAsPageable(0, 10)).thenReturn(new PageImpl<>(articleList));

        final ModelAndView mav = controller.list(1, 10);

        assertEquals("/admin/articles", mav.getViewName());
        final List<Article> articles = (List<Article>) mav.getModel().get("articles");
        assertEquals(articleList.size(), articles.size());
        final Article actual = articles.get(0);
        assertEquals(actual, actual);
        assertEquals(Long.valueOf(1), actual.getId());
    }

    @Test
    public void shouldShowOneById() {
        final Article one = articleList.get(0);
        when(articleService.getOne(1l)).thenReturn(one);
        when(commentService.getCommentsByArticleId(1l)).thenReturn(commentList.subList(0, 1));

        final ModelAndView mav = controller.showOne(1l);

        assertEquals("/admin/article", mav.getViewName());
        final Article actual = (Article) mav.getModel().get("article");
        final int commentsCount = (int) mav.getModel().get("commentsCount");
        assertEquals(one, actual);
        assertEquals(Long.valueOf(1), actual.getId());
        assertEquals(1, commentsCount);
    }

    @Test
    public void shouldEditOne() {
        final Article one = articleList.get(0);
        when(articleService.getOne(1l)).thenReturn(one);
        when(categoryService.getAll()).thenReturn(categoryList);

        final ModelAndView mav = controller.editOne(1l);

        assertEquals("/admin/article-edit", mav.getViewName());
        final Article actual = (Article) mav.getModel().get("article");
        final List<Category> categories = (List<Category>) mav.getModel().get("articleTypes");
        assertEquals(one, actual);
        assertEquals(Long.valueOf(1), actual.getId());
        assertEquals(5, categories.size());
    }

}