package com.kp.domain;

import com.google.common.collect.Lists;
import com.kp.domain.base.BaseEntity;
import com.kp.domain.model.ArticleStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by turgaycan on 9/22/15.
 */
@Entity
@Table(name = "article", schema = "kp")
@SequenceGenerator(name = "article_id_seq", sequenceName = "article_id_seq", allocationSize = 1)
public class Article extends BaseEntity {
    private static final long serialVersionUID = -243601926592177028L;
    private static final int MAX_CONTENT_SIZE = 100000;
    private static final int MAX_TITLE_SIZE = 100;
    public static final int RECENT_ARTICLE_LIMIT = 3;

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "article_id_seq")
    private Long id;
    @Column(nullable = false, length = MAX_CONTENT_SIZE)
    private String content;
    @Column(nullable = false, length = MAX_TITLE_SIZE)
    private String title;
    @Column(nullable = false)
    private String tags;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "view_number")
    private int viewNumber;
    @Column(name = "application_name")
    private String applicationName;
    @Column(name = "download_number")
    private int downloadNumber;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date modifydate;
    @Enumerated(EnumType.STRING)
    @Column(name = "article_status")
    private ArticleStatus articleStatus = ArticleStatus.IN_PROGRESS;
    @OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(fetch = FetchType.EAGER, targetEntity = ArticleType.class)
    @JoinColumn(name = "article_type_id")
    private ArticleType articleType;
    @Column(length = 500, nullable = false)
    private String url;
    @Column(name = "main_image_url")
    private String mainImageUrl;
    @Transient
    private long commentListSize;

    public Article() {
    }

    public Article(Long id, String content, String title, String tags, Date createdate, ArticleType articleType, User user) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.tags = tags;
        this.createdate = createdate;
        this.articleType = articleType;
        this.user = user;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(int downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public long getCommentListSize() {
        return commentListSize;
    }

    public void setCommentListSize(long commentListSize) {
        this.commentListSize = commentListSize;
    }

    public String buildUrl() {
        return url + "-" + id;
    }

    public List<String> getArticleTags() {
        return Lists.newArrayList(getTags().split(","));
    }

    public String getCategoryName() {
        return getArticleType().getName();
    }

    public String getUserFullname() {
        return getUser().getFullname();
    }

    public String buildResizedImageUrl(String size) {
        final String replacement = "_" + size + ".png";
        return getMainImageUrl().replaceAll(".png", replacement);
    }

    public void increment() {
        ++viewNumber;
    }

    public String getCacheKey(){
        return "article-" + id;
    }
}
