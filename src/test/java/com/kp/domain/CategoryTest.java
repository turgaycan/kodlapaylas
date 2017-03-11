package com.kp.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by tcan on 18/10/15.
 */
public class CategoryTest {

    @Test
    public void testGetRoot() {

        Category root = new Category(1l, "root", null);
        Category child1 = new Category(2l, "child1", root);
        Category child11 = new Category(3l, "child11", child1);
        Category child111 = new Category(4l, "child111", child11);

        assertThat(child11.getRoot(), is(root));
        assertThat(child111.getRoot(), is(root));
        assertThat(child1.getRoot(), is(root));
        assertThat(root.getRoot(), is(root));

    }
}