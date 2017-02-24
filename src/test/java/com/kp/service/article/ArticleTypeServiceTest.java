package com.kp.service.article;

import com.google.common.collect.Lists;
import com.kp.domain.ArticleType;
import com.kp.repository.ArticleTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
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

    @Test
    public void testFindAll() throws Exception {
        ArticleType articleType1 = new ArticleType(1l, "type1", null);
        ArticleType articleType2 = new ArticleType(2l, "type1", null);
        ArticleType articleType3 = new ArticleType(3l, "type1", null);
        ArticleType articleType4 = new ArticleType(4l, "type1", null);

        when(articleTypeRepository.findRootArticleTypes()).thenReturn(Lists.newArrayList(articleType1, articleType2, articleType3, articleType4));

        List<ArticleType> allRootTypes = service.findAllRootTypes();

        assertThat(allRootTypes.size(), is(4));
        assertThat(allRootTypes, hasItems(articleType1, articleType2, articleType3, articleType4));

    }
}