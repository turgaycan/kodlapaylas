package com.kp.service.seo;

import com.kp.domain.model.seo.SeoMetaData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.config.PropertiesFactoryBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by tcan on 07/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SeoMetaDataServiceTest {

    @InjectMocks
    private SeoMetaDataService service;

    @Mock
    private PropertiesFactoryBean seoProperty;

    @Test
    public void shouldBuildPageSeoMetaDataWithPage() throws IOException {
        final Properties properties = mock(Properties.class);
        when(seoProperty.getObject()).thenReturn(properties);
        when(properties.get("index.title")).thenReturn("title");
        when(properties.get("index.keywords")).thenReturn("keywords");
        when(properties.get("index.description")).thenReturn("description");

        final SeoMetaData actual = service.buildPageSeoMetaData("index");

        assertEquals("title", actual.getTitle());
        assertEquals("keywords", actual.getKeywords());
        assertEquals("description", actual.getDescription());
    }

    @Test
    public void shouldBuildPageSeoMetaDataWithPage1() throws IOException {
        final Properties properties = mock(Properties.class);
        when(seoProperty.getObject()).thenReturn(properties);
        when(properties.get("index.title")).thenReturn("{0}, title");
        when(properties.get("index.keywords")).thenReturn("{0}, keywords");
        when(properties.get("index.description")).thenReturn("{0}, description");

        final HashMap<String, String[]> paramMap = new HashMap<>();
        paramMap.put("title", new String[]{"title"});
        paramMap.put("keywords", new String[]{"keywords"});
        paramMap.put("description", new String[]{"description"});

        final SeoMetaData actual = service.buildPageSeoMetaData("index", paramMap);

        assertEquals("title, title", actual.getTitle());
        assertEquals("keywords, keywords", actual.getKeywords());
        assertEquals("description, description", actual.getDescription());
    }
}