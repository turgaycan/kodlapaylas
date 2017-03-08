package com.kp.service.seo;

import com.kp.domain.model.seo.SeoMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;

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
        final Properties properties = seoProperty.getObject();
        String pageTitle = (String) properties.get(page + DOT_TITLE);
        String pageKeywords = (String) properties.get(page + DOT_KEYWORDS);
        String pageDescription = (String) properties.get(page + DOT_DESCRIPTION);

        return new SeoMetaData(pageTitle, pageKeywords, pageDescription);
    }

    public SeoMetaData buildPageSeoMetaData(String page, Map<String, String[]> paramMap) throws IOException {
        final Properties properties = seoProperty.getObject();
        String pageTitle = getFormattedMessage((String) properties.get(page + DOT_TITLE), getValue(paramMap, TITLE));
        String pageKeywords = getFormattedMessage((String) properties.get(page + DOT_KEYWORDS), getValue(paramMap, KEYWORDS));
        String pageDescription = getFormattedMessage((String) properties.get(page + DOT_DESCRIPTION), getValue(paramMap, DESCRIPTION));

        return new SeoMetaData(pageTitle, pageKeywords, pageDescription);
    }

    private String getFormattedMessage(String pattern, String[] value) throws IOException {
        return MessageFormat.format(pattern, value);
    }

    private String[] getValue(Map<String, String[]> paramMap, String title) {
        return paramMap.get(title);
    }
}
