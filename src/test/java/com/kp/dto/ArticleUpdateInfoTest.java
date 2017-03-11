package com.kp.dto;


import com.kp.service.article.ArticleService;
import com.kp.validator.validate.KpInfoValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by tcan on 11/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleUpdateInfoTest {

    @Mock
    private ArticleService articleService;

    @Test
    public void shouldValidate() {
        ArticleUpdateInfo articleUpdateInfo = new ArticleUpdateInfo();
        articleUpdateInfo.setTitle(RandomStringUtils.randomAlphanumeric(101));
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");

        final KpInfoValidator<ArticleUpdateInfo> validator = articleUpdateInfo.validator();

        validator.validate(articleUpdateInfo, errors);

        final String errorMessage = errors.getAllErrors().get(0).getCode();
        assertEquals("Makale başlığı 100 karakterden fazla olamaz!", errorMessage);
    }
}