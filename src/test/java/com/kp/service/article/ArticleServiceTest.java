package com.kp.service.article;

import com.kp.domain.Article;
import com.kp.domain.Category;
import com.kp.domain.User;
import com.kp.domain.spec.PageSpec;
import com.kp.dto.DateRange;
import com.kp.repository.ArticleRepository;
import com.kp.service.cache.CacheService;
import com.kp.util.DateUtils;
import com.kp.util.ListUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by tcan on 04/10/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceTest {

    @InjectMocks
    private ArticleService service;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CacheService cacheService;

    @Mock
    private CommentService commentService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private DateUtils dateUtils;

    private Article article;
    private Category category;
    private Category parentCategory;
    private Pageable pageable;
    private List<Article> articles = new ArrayList<>();

    @Before
    public void init() {
        parentCategory = new Category(0l, "parentCategory", null);
        category = new Category(1l, "category", parentCategory);
        article = new Article(1l, "content", "title", "tags", new Date(), category, new User());
        pageable = new PageRequest(0, 10);

        articles.add(article);

        for (long index = 2; index < 10; index++) {
            final Category currentType = new Category(index, "category" + index);
            articles.add(new Article(index, "content" + index, "title" + index, "tags" + index, new Date(), currentType, new User()));
        }
    }

    @Test
    public void shouldSave() {
        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        when(articleRepository.saveAndFlush(articleCaptor.capture())).thenReturn(article);

        ArgumentCaptor<String> cacheKeyCaptor = ArgumentCaptor.forClass(String.class);
        doNothing().when(cacheService).set(cacheKeyCaptor.capture(), eq(article));

        final Article persisted = service.save(article);

        assertNotNull(persisted);
        assertEquals(1, articleCaptor.getValue().getViewNumber());
        assertEquals("article-1", cacheKeyCaptor.getValue());
    }


    @Test
    public void shouldGetArticleById() {
        when(articleRepository.findById(1l)).thenReturn(article);
        final Article found = service.getById(1l);

        assertThat(found, is(article));
    }

    @Test
    public void shouldGetRecentArticles() {
        when(articleRepository.findByCategoryPageable(category.getId(), pageable)).thenReturn(new PageImpl<>(articles));

        final List<Article> recentArticles = service.getRecentArticles(article, 5);

        assertEquals(5, recentArticles.size());
        assertEquals(Long.valueOf(2), recentArticles.get(0).getId());
        assertEquals(Long.valueOf(6), recentArticles.get(4).getId());
    }

    @Test
    public void shouldGetRelatedArticles() {
        final List<Article> recentArticles = ListUtil.defensiveArrayList(articles, 5);

        when(articleRepository.findByCategoryOrTitleLike(article.getCategory(), article.getTitle(), PageSpec.buildPageSpecificationByFieldDesc(0, 10, "createdate"))).thenReturn(new PageImpl<>(articles));

        final List<Article> relatedArticles = service.getRelatedArticles(recentArticles, article, 5);

        assertNotNull(relatedArticles);
        assertEquals(4, relatedArticles.size());
        assertEquals(Long.valueOf(9), relatedArticles.get(3).getId());
    }

    @Test
    public void shouldGetByCategory() {

        when(articleRepository.findByCategory(category, PageSpec.buildPageSpecificationByFieldDesc(1, 10, "createdate"))).thenReturn(new PageImpl<>(articles));

        final Page<Article> articleList = service.getByCategory(category, 1, 10);

        assertEquals(9, articleList.getNumberOfElements());
    }

    @Test
    public void shouldGetByCategoryIn() {

        when(articleRepository.findByCategoryIn(newArrayList(category), PageSpec.buildPageSpecificationByFieldDesc(1, 10, "createdate"))).thenReturn(new PageImpl<>(articles));

        final Page<Article> articleList = service.getByCategoryIn(newArrayList(category), 1, 10);

        assertEquals(9, articleList.getNumberOfElements());
    }

    @Test
    public void shouldGetByCreatedateAfterAndCreatedateBefore() {
        final DateRange dateRange = new DateRange(new Date(), new Date());

        when(articleRepository.findByCreatedateAfterAndCreatedateBefore(
                dateRange.getStartDate(),
                dateRange.getEndDate(),
                PageSpec.buildPageSpecificationByFieldDesc(1, 10, "createdate"))).thenReturn(new PageImpl<>(articles));

        final Page<Article> articleList = service.getByCreatedateAfterAndCreatedateBefore(dateRange, 1, 10);

        assertEquals(9, articleList.getNumberOfElements());
    }

    @Test
    public void shouldGetById() {
        when(articleRepository.findById(1l)).thenReturn(article);
        final Article persisted = service.getById(1l);

        assertNotNull(persisted);
    }

    @Test
    public void shouldGetOne() {
        when(articleRepository.findOne(1l)).thenReturn(article);
        final Article persisted = service.getOne(1l);

        assertNotNull(persisted);
    }

    @Test
    public void shouldGetFeatureArticles() {
        when(articleRepository.findPageableOrderByCreatedateDesc(PageSpec.buildPageSpecificationByFieldDesc(0, 10, "viewNumber"))).thenReturn(new PageImpl<>(articles));

        final List<Article> featureArticles = service.getFeatureArticles();

        assertNotNull(featureArticles);
        assertEquals(9, featureArticles.size());
    }

    @Test
    public void shouldGetLastOne() {
        when(articleRepository.findPageableOrderByCreatedateDesc(PageSpec.buildPageSpecificationByFieldDesc(0, 1, "createdate"))).thenReturn(new PageImpl<>(articles));
        when(commentService.getArticleCommentCount(article)).thenReturn(5l);

        final Article lastOne = service.getLastOne();

        assertNotNull(lastOne);
        assertEquals(Long.valueOf(1), lastOne.getId());
        assertEquals(5l, lastOne.getCommentListSize());
    }

    @Test
    public void shouldGetByCategoryOrderByViewNumber() {
        when(categoryService.getByParentId(0l)).thenReturn(newArrayList(category));
        when(articleRepository.findByCategoryId(new Long[]{1l})).thenReturn(articles);

        final List<Article> articleList = service.getByCategoryOrderByViewNumber(0l);

        assertNotNull(articleList);
        assertEquals(9, articleList.size());
    }

    @Test
    public void shouldGetArticlesAsPageable() {
        when(articleRepository.findPageableOrderByCreatedateDesc(PageSpec.buildPageSpecificationByFieldDesc(1, 10, "createdate"))).thenReturn(new PageImpl<>(articles));
        final Page<Article> articleList = service.getArticlesAsPageable(1, 10);

        assertNotNull(articleList);
        assertEquals(9, articleList.getTotalElements());
    }

    @Test
    public void shouldGetArticlesAsPageableNotCacheAble() {
        when(articleRepository.findPageableOrderByCreatedateDesc(PageSpec.buildPageSpecificationByFieldDesc(1, 10, "createdate"))).thenReturn(new PageImpl<>(articles));
        final Page<Article> articleList = service.getArticlesAsPageableNotCacheAble(1, 10);

        assertNotNull(articleList);
        assertEquals(9, articleList.getTotalElements());
    }

    @Test
    public void shouldGetArticlesByTagAsPageable() {
        when(articleRepository.findByTagsContaining("tag", PageSpec.buildPageSpecificationByFieldDesc(1, 10, "createdate"))).thenReturn(new PageImpl<>(articles));
        final Page<Article> articleList = service.getArticlesByTagAsPageable("tag", 1, 10);

        assertNotNull(articleList);
        assertEquals(9, articleList.getTotalElements());
    }

    @Test
    public void shouldCountOfTotalArticles() {
        when(articleRepository.count()).thenReturn(455l);
        final long countOfTotalArticles = service.countOfTotalArticles();

        assertEquals(455l, countOfTotalArticles);
    }

    @Test
    public void shouldGetAll() {
        when(articleRepository.findAll()).thenReturn(articles);
        final List<Article> allArticles = service.getAll();

        assertEquals(9, allArticles.size());
    }

}