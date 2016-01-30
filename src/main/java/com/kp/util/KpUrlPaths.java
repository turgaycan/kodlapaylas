package com.kp.util;


/**
 * Created by turgaycan on 9/30/15.
 */
public class KpUrlPaths {

    public static final String BACK_SLASH = "/";
    public static final String CATEGORY = "/kategori";
    public static final String CATEGORY_VIEW = "/category";
    public static final String ARCHIVE = "/arsiv";
    public static final String ARCHIVE_VIEW = "archive";
    public static final String ALL = "/kp";
    public static final String SEARCH = "/arama";
    public static final String ERROR = "/error";
    public static final String ARCHIVE_WITH_SLASH = ARCHIVE + BACK_SLASH;
    public static final String CATEGORY_WITH_SLASH = CATEGORY + BACK_SLASH;
    public static final String ALL_WITH_SLASH = CATEGORY + ALL;

    public static String buildCategoryUrl(String categoryName) {
        return categoryName.equals("kp") ? ALL_WITH_SLASH : CATEGORY_WITH_SLASH + categoryName;
    }

    public static String buildArchiveUrl(String archive) {
        return ARCHIVE_WITH_SLASH + archive;
    }

    private KpUrlPaths() {
    }
}
