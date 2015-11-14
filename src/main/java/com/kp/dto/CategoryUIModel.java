package com.kp.dto;

import com.kp.domain.ArticleType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tcan on 18/10/15.
 */
public class CategoryUIModel implements Serializable {
    private static final long serialVersionUID = -3200520401801462887L;

    private ArticleType category;
    private List<ArticleType> subCategories;

    public CategoryUIModel() {
    }

    public CategoryUIModel(ArticleType category, List<ArticleType> subCategories) {
        this.category = category;
        this.subCategories = subCategories;
    }


    public ArticleType getCategory() {
        return category;
    }

    public void setCategory(ArticleType category) {
        this.category = category;
    }

    public List<ArticleType> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<ArticleType> subCategories) {
        this.subCategories = subCategories;
    }
}
