package com.kp.domain.model;

import com.kp.domain.Article;

import java.io.Serializable;
import java.util.List;

/**
 * Created by turgaycan on 9/29/15.
 */
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 7931998348064963535L;
    private List<Article> articleList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
