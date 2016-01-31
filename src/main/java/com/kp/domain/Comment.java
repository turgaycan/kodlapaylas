package com.kp.domain;

import com.google.common.collect.Lists;
import com.kp.domain.base.BaseEntity;
import com.kp.domain.model.CommentStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by turgaycan on 9/22/15.
 */
@Entity
@Table(name = "comment", schema = "kp")
@SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1)
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 3951817938640184706L;

    private static final int COMMENT_MAX_SIZE = 4000;

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "comment_id_seq")
    private Long id;
    @Column(nullable = false, length = COMMENT_MAX_SIZE)
    private String content;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdate;
    @Enumerated(EnumType.STRING)
    @Column(name = "comment_status")
    private CommentStatus commentStatus = CommentStatus.WAITING;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
    private Article article;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
    private Comment parent;

    public Comment() {
    }

    public Comment(Long id, Comment parent) {
        this.id = id;
        this.parent = parent;
    }

    public Comment(Long id, Comment parent, Article article) {
        this.id = id;
        this.parent = parent;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public CommentStatus getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(CommentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }


    public boolean hasReplyComments() {
        return !isRootComment();
    }

    public boolean isRootComment() {
        return parent == null;
    }

    public boolean isChild(Comment comment) {
        return this.equals(comment);
    }


    public List<Comment> replyParentComments() {
        List<Comment> replyCommentList = new ArrayList<>();
        while (hasReplyComments()) {
            replyCommentList.add(parent);
            Comment replyComment = parent.getParent();
            if (replyComment.isRootComment()) {
                replyCommentList.add(replyComment);
                break;
            }
            replyCommentList.add(replyComment);
            setParent(replyComment.getParent());
        }
        return replyCommentList;
    }

    public static List<Comment> rootComments(List<Comment> commentList) {
        List<Comment> rootComments = Lists.newArrayList();
        rootComments.addAll(commentList.stream().filter(comment -> comment.isRootComment()).collect(Collectors.toList()));

        return rootComments;
    }

    public static List<Comment> childComments(List<Comment> comments) {
        List<Comment> childComments = Lists.newArrayList();
        childComments.addAll(comments.stream().filter(c -> c.hasReplyComments()).collect(Collectors.toList()));

        return childComments;
    }

    public static List<Comment> findReplys(List<Comment> replyComments, Comment root) {
        List<Comment> childComments = Lists.newArrayList();
        for (Comment replyComment : replyComments) {
            if (replyComment.getParent().equals(root)) {
                childComments.add(replyComment);
            }
        }

        return childComments;
    }
}
