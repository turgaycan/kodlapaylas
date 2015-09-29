package com.kp.service.article;

import com.google.common.collect.Lists;
import com.kp.domain.ArticleType;
import com.kp.repository.ArticleTypeRepository;
import org.junit.Ignore;
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

    @Ignore
    @Test
    public void testFindAll() throws Exception {
        ArticleType articleType1 = new ArticleType(1l, "type1", null);
        ArticleType articleType12 = new ArticleType(1l, "type1", articleType1);
        ArticleType articleType2 = new ArticleType(1l, "type1", null);
        ArticleType articleType21 = new ArticleType(1l, "type1", articleType2);
        ArticleType articleType3 = new ArticleType(1l, "type1", null);
        ArticleType articleType4 = new ArticleType(1l, "type1", null);
        ArticleType articleType41 = new ArticleType(1l, "type1", articleType4);

        when(articleTypeRepository.findAll()).thenReturn(Lists.newArrayList(articleType1, articleType12, articleType2, articleType21, articleType3, articleType4, articleType41));

        List<ArticleType> allRootTypes = service.findAllRootTypes();

        assertThat(allRootTypes.size(), is(4));
        assertThat(allRootTypes, hasItems(articleType1, articleType2, articleType3, articleType4));

    }
}