package com.kp.service.article;

import com.kp.domain.ArticleType;
import com.kp.repository.ArticleTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by turgaycan on 9/27/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleTypeServiceTest {

    @InjectMocks
    private ArticleTypeService service;

    @Mock
    private ArticleTypeRepository articleTypeRepository;

    private ArticleType articleType1;
    private ArticleType articleType2;
    private ArticleType articleType3;
    private ArticleType articleType4;
    private ArticleType childArticleType1;

    @Before
    public void init() {
        articleType1 = new ArticleType(1l, "type1", null);
        articleType2 = new ArticleType(2l, "type1", null);
        articleType3 = new ArticleType(3l, "type1", null);
        articleType4 = new ArticleType(4l, "type1", null);
        childArticleType1 = new ArticleType(5l, "child type1", articleType1);
    }

    @Test
    public void shouldFindAll() {
        when(articleTypeRepository.findRootArticleTypes()).thenReturn(newArrayList(articleType1, articleType2, articleType3, articleType4));

        List<ArticleType> allRootTypes = service.getAllRootTypes();

        assertThat(allRootTypes.size(), is(4));
        assertThat(allRootTypes, hasItems(articleType1, articleType2, articleType3, articleType4));
    }

    @Test
    public void shouldGetByName() {
        when(articleTypeRepository.findByName("type1")).thenReturn(Optional.ofNullable(articleType1));

        final ArticleType actual = service.getByName("type1");

        assertEquals(Long.valueOf(1), actual.getId());
    }

    @Test
    public void shouldGetAll() {
        when(articleTypeRepository.findAll()).thenReturn(newArrayList(articleType1, articleType2, articleType3, articleType4, childArticleType1));

        final List<ArticleType> all = service.getAll();

        assertEquals(5, all.size());
    }

    @Test
    public void shouldGetByParentId() {
        when(articleTypeRepository.findByParentId(1l)).thenReturn(newArrayList(childArticleType1));
        final List<ArticleType> articleTypeList = service.getByParentId(1l);

        assertEquals(1, articleTypeList.size());
        assertEquals(Long.valueOf(5l), articleTypeList.get(0).getId());
        assertEquals("child type1", articleTypeList.get(0).getName());
    }

}