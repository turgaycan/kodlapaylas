package com.kp.util;

/**
 * Created by turgaycan on 9/30/15.
 */
public class KpUrlPaths {

    public static final String CATEGORY = "/kategori/";

    public static String buildCategoryUrl(String caterogyName) {
        return CATEGORY + caterogyName;
    }

    private KpUrlPaths() {
    }
}
