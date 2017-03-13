package com.kp.dto;

import com.kp.domain.Comment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tcan on 11/10/15.
 */
public class CommentUIModel implements Serializable {
    private static final long serialVersionUID = 5509356887956357296L;

    private Comment comment;
    private List<Comment> replyComments;

    public CommentUIModel(){}

    public CommentUIModel(Comment comment, List<Comment> replyComments) {
        this.comment = comment;
        this.replyComments = replyComments;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public boolean hasComment(Comment comment){
        return replyComments.contains(comment);
    }

}
