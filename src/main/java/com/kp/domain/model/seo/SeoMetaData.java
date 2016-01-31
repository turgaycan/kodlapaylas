package com.kp.domain.model.seo;

import java.io.Serializable;

/**
 * Created by tcan on 30/01/16.
 */
public class SeoMetaData implements Serializable {
    private static final long serialVersionUID = 5207176325875694429L;

    private String title;
    private String keywords;
    private String description;

    public SeoMetaData(String title, String keywords, String description) {
        this.title = title;
        this.keywords = keywords;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
