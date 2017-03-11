package com.kp.dto;


import com.kp.domain.Article;
import com.kp.domain.Category;
import com.kp.service.article.ArticleService;
import com.kp.validator.validate.KpInfoValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 11/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleUpdateInfoTest {

    @Mock
    private ArticleService articleService;

    @Test
    public void shouldNotValidateIfTitleLengthOverMaxLength() {
        ArticleUpdateInfo articleUpdateInfo = new ArticleUpdateInfo();
        articleUpdateInfo.setTitle(RandomStringUtils.randomAlphanumeric(Article.MAX_TITLE_SIZE + 1));
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");

        final KpInfoValidator<ArticleUpdateInfo> validator = articleUpdateInfo.validator();

        validator.validate(articleUpdateInfo, errors);

        final String errorMessage = errors.getAllErrors().get(0).getCode();
        assertEquals("Makale başlığı 100 karakterden fazla olamaz!", errorMessage);
    }

    @Test
    public void shouldNotValidateIfContentLengthOverMax() {
        ArticleUpdateInfo articleUpdateInfo = new ArticleUpdateInfo();
        articleUpdateInfo.setTitle("title");
        articleUpdateInfo.setContent(RandomStringUtils.randomAlphanumeric(Article.MAX_CONTENT_SIZE + 1));
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");

        final KpInfoValidator<ArticleUpdateInfo> validator = articleUpdateInfo.validator();

        validator.validate(articleUpdateInfo, errors);

        final String errorMessage = errors.getAllErrors().get(0).getCode();
        assertEquals("Makale içeriği 100000 karakterden fazla olamaz!", errorMessage);
    }

    @Test
    public void shouldNotValidateIfTagsLengthLowerMin() {
        ArticleUpdateInfo articleUpdateInfo = new ArticleUpdateInfo();
        articleUpdateInfo.setTitle("title");
        articleUpdateInfo.setContent("content");
        articleUpdateInfo.setTags("ta");
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");

        final KpInfoValidator<ArticleUpdateInfo> validator = articleUpdateInfo.validator();

        validator.validate(articleUpdateInfo, errors);

        final String errorMessage = errors.getAllErrors().get(0).getCode();
        assertEquals("Makale etiketleri 3 karakterden küçük olamaz!", errorMessage);
    }

    @Test
    public void shouldNotValidateIfCategoryIsNull() {
        ArticleUpdateInfo articleUpdateInfo = new ArticleUpdateInfo();
        articleUpdateInfo.setTitle("title");
        articleUpdateInfo.setContent("content");
        articleUpdateInfo.setTags("tag");
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");

        final KpInfoValidator<ArticleUpdateInfo> validator = articleUpdateInfo.validator();

        validator.validate(articleUpdateInfo, errors);

        final String errorMessage = errors.getAllErrors().get(0).getCode();
        assertEquals("Makale kategorisi seçiniz!", errorMessage);
    }

    @Ignore
    @Test
    public void shouldNotValidateIfArticleNotFound() {
        ArticleUpdateInfo articleUpdateInfo = new ArticleUpdateInfo();
        articleUpdateInfo.setId(1l);
        articleUpdateInfo.setTitle("title");
        articleUpdateInfo.setContent("content");
        articleUpdateInfo.setTags("tag");
        articleUpdateInfo.setCategory(new Category());

        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");

        when(articleService.getById(1l)).thenReturn(null);

        articleUpdateInfo.validator().validate(articleUpdateInfo, errors);

        final String errorMessage = errors.getAllErrors().get(0).getCode();
        assertEquals("Makale bulunamadı!", errorMessage);
    }
}