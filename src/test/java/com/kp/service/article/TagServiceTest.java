package com.kp.service.article;

import com.kp.domain.Category;
import com.kp.domain.Tag;
import com.kp.domain.spec.PageSpec;
import com.kp.repository.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 04/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {

    @InjectMocks
    private TagService service;

    @Mock
    private TagRepository tagRepository;

    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    @Before
    public void init() {
        tag1 = new Tag(1l, 7);
        tag2 = new Tag(2l, 3);
        tag3 = new Tag(3l, 5);
    }

    @Test
    public void shouldGetByArticleType() {
        Category root = new Category(1l, "root");
        Category child = new Category(2l, "child", root);
        when(tagRepository.findByArticleType(root,
                PageSpec.buildPageSpecificationByFieldDesc(1, 15, "count"))).thenReturn(new PageImpl<>(newArrayList(tag1, tag2, tag3)));

        final List<Tag> tagList = service.getByArticleType(child);

        assertEquals(3, tagList.size());
        assertArrayEquals(newArrayList(tag1, tag2, tag3).toArray(), tagList.toArray());
    }

    @Test
    public void shouldGetByOrderCountDesc() {
        when(tagRepository.orderByCountDesc(PageSpec.buildPageSpecificationByFieldDesc(0, 15, "count"))).thenReturn(newArrayList(tag1, tag3, tag2));

        final List<Tag> tagList = service.getByOrderCountDesc();

        assertEquals(3, tagList.size());
        assertEquals(tag1, tagList.get(0));
        assertEquals(tag3, tagList.get(1));
        assertEquals(tag2, tagList.get(2));
    }

}