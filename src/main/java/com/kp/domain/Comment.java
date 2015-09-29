package com.kp.domain;

import com.kp.domain.base.BaseEntity;
import com.kp.domain.model.CommentStatus;

import javax.persistence.*;
import java.util.Date;

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
    private Article article;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Comment() {
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
}
