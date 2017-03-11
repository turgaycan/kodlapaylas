package com.kp.dto;

import com.kp.domain.Category;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tcan on 18/10/15.
 */
public class CategoryUIModel implements Serializable {
    private static final long serialVersionUID = -3200520401801462887L;

    private Category category;
    private List<Category> subCategories;

    public CategoryUIModel() {
    }

    public CategoryUIModel(Category category, List<Category> subCategories) {
        this.category = category;
        this.subCategories = subCategories;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }
}
