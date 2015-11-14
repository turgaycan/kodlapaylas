package com.kp.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tcan on 05/10/15.
 */
public class CommentModel extends BaseCommentModel {
    private static final long serialVersionUID = 4962311194950002389L;

    @NotBlank
    private String articleId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

}
