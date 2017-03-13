package com.kp.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tcan on 16/10/15.
 */
public class CommentBaseModel implements Serializable {
    private static final long serialVersionUID = -8575817794901305222L;

    private int commentSize;
    private List<CommentUIModel> commentUIModels;

    public CommentBaseModel(){}

    public CommentBaseModel(int commentSize, List<CommentUIModel> commentUIModels) {
        this.commentSize = commentSize;
        this.commentUIModels = commentUIModels;
    }

    public int getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(int commentSize) {
        this.commentSize = commentSize;
    }

    public List<CommentUIModel> getCommentUIModels() {
        return commentUIModels;
    }

    public void setCommentUIModels(List<CommentUIModel> commentUIModels) {
        this.commentUIModels = commentUIModels;
    }

}
