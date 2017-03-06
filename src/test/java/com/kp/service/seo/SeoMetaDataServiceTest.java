package com.kp.service.seo;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.config.PropertiesFactoryBean;


/**
 * Created by tcan on 07/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SeoMetaDataServiceTest {

    @InjectMocks
    private SeoMetaDataService service;

    @Mock
    private PropertiesFactoryBean seoProperty;
}