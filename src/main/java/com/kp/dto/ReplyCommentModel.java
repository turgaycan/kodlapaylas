package com.kp.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tcan on 10/10/15.
 */
public class ReplyCommentModel extends BaseCommentModel {
    private static final long serialVersionUID = 782724532473227569L;

    @NotBlank
    private String commentId;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
