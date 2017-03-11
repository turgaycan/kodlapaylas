package com.kp.dto;

import com.kp.domain.Article;
import com.kp.service.article.ArticleService;
import com.kp.validator.validate.KpInfoValidator;
import com.kp.validator.validate.Validateable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tcan on 08/02/17.
 */
public class ArticleUpdateInfo extends Article implements Validateable<ArticleUpdateInfo>, Serializable {

    @Override
    public KpInfoValidator<ArticleUpdateInfo> validator() {
        return new KpInfoValidator<ArticleUpdateInfo>() {

            @Autowired
            private ArticleService articleService;

            @Override
            public void validate(ArticleUpdateInfo target, Errors errors) {
                if (target.getTitle().length() > Article.MAX_TITLE_SIZE) {
                    errors.rejectValue("article", "Makale başlığı 100 karakterden fazla olamaz!");
                    return;
                }

                if (target.getContent().length() > Article.MAX_CONTENT_SIZE) {
                    errors.rejectValue("article", "Makale içeriği 100000 karakterden fazla olamaz!");
                    return;
                }

                if (target.getTags().length() < Article.MIN_TAG_LENGTH) {
                    errors.rejectValue("article", "Makale etiketleri 3 karakterden küçük olamaz!");
                    return;
                }

                if (target.getCategory() == null) {
                    errors.rejectValue("article", "Makale kategorisi seçiniz!");
                    return;
                }
                final Article article = articleService.getById(target.getId());

                if (article == null) {
                    errors.rejectValue("article", "Makale bulunamadı!");
                    return;
                }
            }
        };
    }

    public Article decorateArticle(Article article, Date now) {
        article.setTitle(getTitle());
        article.setContent(getContent());
        article.setTags(getTags());
        article.setArticleStatus(getArticleStatus());
        article.setCategory(getCategory());
        article.setModifydate(now);
        article.setUrl(article.createUrl());

        return article;
    }
}
