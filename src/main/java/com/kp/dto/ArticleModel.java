package com.kp.dto;

import com.google.common.base.Joiner;
import com.kp.domain.Article;
import com.kp.domain.User;
import com.kp.util.TurkishCharUtil;
import com.kp.validator.validate.KpInfoValidator;
import com.kp.validator.validate.Validateable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tcan on 20/02/17.
 */
public class ArticleModel extends Article implements Validateable<ArticleModel>, Serializable {
    @Override
    public KpInfoValidator<ArticleModel> validator() {
        return (target, errors) -> {

            if (target.getTitle().length() > Article.MAX_TITLE_SIZE) {
                errors.rejectValue("article", "Makale başlığı 100 karakterden fazla olamaz!");
                return;
            }

            if (target.getTitle().length() > Article.MAX_CONTENT_SIZE) {
                errors.rejectValue("article", "Makale içeriği 100000 karakterden fazla olamaz!");
                return;
            }

            if (target.getTags().length() < 3) {
                errors.rejectValue("article", "Makale etiketleri 3 karakterden küçük olamaz!");
                return;
            }

            if (target.getArticleType() == null) {
                errors.rejectValue("article", "Makale kategorisi seçiniz!");
                return;
            }


        };
    }

    public Article buildNewArticle(User user) {
        Article article = new Article();
        article.setTitle(getTitle());
        article.setContent(getContent());
        article.setTags(getTags());
        article.setArticleStatus(getArticleStatus());
        article.setArticleType(getArticleType());
        article.setCreatedate(new Date());
        article.setUrl(createUrl());
        article.setMainImageUrl(createMainUrl());
        article.setUser(user);
        return article;
    }

    private String createMainUrl() {
        return getArticleType().getName() + ".png";
    }
}
