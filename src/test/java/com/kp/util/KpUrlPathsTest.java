package com.kp.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tcan on 09/03/17.
 */
public class KpUrlPathsTest {

    @Test
    public void buildKpCategoryUrl() {
        final String kpCategoryUrl = KpUrlPaths.buildCategoryUrl("kp");

        assertEquals("/kategori/kp", kpCategoryUrl);
    }

    @Test
    public void buildNoneKpCategoryUrl() {
        final String kpCategoryUrl = KpUrlPaths.buildCategoryUrl("test");

        assertEquals("/kategori/test", kpCategoryUrl);
    }

    @Test
    public void buildArchiveUrl() {
        final String archiveUrl = KpUrlPaths.buildArchiveUrl("2017");

        assertEquals("/arsiv/2017", archiveUrl);
    }

    @Test
    public void buildTagUrl() {
        final String tagUrl = KpUrlPaths.buildTagUrl("turgay");

        assertEquals("/tag/turgay", tagUrl);
    }

}