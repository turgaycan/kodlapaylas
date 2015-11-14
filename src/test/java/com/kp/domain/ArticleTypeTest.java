package com.kp.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by tcan on 18/10/15.
 */
public class ArticleTypeTest {

    @Test
    public void testGetRoot() {

        ArticleType root = new ArticleType(1l, "root", null);
        ArticleType child1 = new ArticleType(2l, "child1", root);
        ArticleType child11 = new ArticleType(3l, "child11", child1);
        ArticleType child111 = new ArticleType(4l, "child111", child11);

        assertThat(child11.getRoot(), is(root));
        assertThat(child111.getRoot(), is(root));
        assertThat(child1.getRoot(), is(root));
        assertThat(root.getRoot(), is(root));

    }
}