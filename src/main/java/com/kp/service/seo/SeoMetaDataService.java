package com.kp.service.seo;

import com.kp.domain.model.seo.SeoMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by tcan on 03/10/15.
 */
@Service
public class SeoMetaDataService {

    private static final String DOT = ".";
    public static final String TITLE = "title";
    private static final String DOT_TITLE = DOT + TITLE;
    public static final String KEYWORDS = "keywords";
    private static final String DOT_KEYWORDS = DOT + KEYWORDS;
    public static final String DESCRIPTION = "description";
    private static final String DOT_DESCRIPTION = DOT + DESCRIPTION;

    @Autowired
    private PropertiesFactoryBean seoProperty;

    public SeoMetaData buildPageSeoMetaData(String page) throws IOException {
        String pageTitle = (String) seoProperty.getObject().get(page + DOT_TITLE);
        String pageKeywords = (String) seoProperty.getObject().get(page + DOT_KEYWORDS);
        String pageDescription = (String) seoProperty.getObject().get(page + DOT_DESCRIPTION);

        return new SeoMetaData(pageTitle, pageKeywords, pageDescription);
    }

    public SeoMetaData buildPageSeoMetaData(String page, Map<String, String[]> paramMap) throws IOException {
        String pageTitle = MessageFormat.format((String) seoProperty.getObject().get(page + DOT_TITLE), getValue(paramMap, TITLE));
        String pageKeywords = MessageFormat.format((String) seoProperty.getObject().get(page + DOT_KEYWORDS), getValue(paramMap, KEYWORDS));
        String pageDescription = MessageFormat.format((String) seoProperty.getObject().get(page + DOT_DESCRIPTION), getValue(paramMap, DESCRIPTION));

        return new SeoMetaData(pageTitle, pageKeywords, pageDescription);
    }

    private String[] getValue(Map<String, String[]> paramMap, String title) {
        return paramMap.get(title);
    }
}
