package com.kp.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tcan on 11/09/16.
 */
public class ArticleTest {

    @Test
    public void shouldGetResizedImageUrl(){
        final Article article = new Article();
        article.setMainImageUrl("java.png");
        final String actualUrl = article.buildResizedImageUrl("300_300");

        assertEquals(actualUrl, ("java_300_300.png"));
    }
}