package com.kp.domain;

import com.kp.domain.base.BaseEntity;
import com.kp.util.KpUrlPaths;

import javax.persistence.*;

/**
 * Created by turgaycan on 9/22/15.
 */
@Entity
@Table(name = "tag", schema = "kp")
@SequenceGenerator(name = "tag_id_seq", sequenceName = "tag_id_seq", allocationSize = 1)
public class Tag extends BaseEntity {
    private static final long serialVersionUID = -8292138817684891901L;

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tag_id_seq")
    private Long id;
    @Column(nullable = false)
    private String name;
    private int count;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_type_id")
    private ArticleType articleType;

    public Tag() {
    }

    public Tag(Long id, int count) {
        this.id = id;
        this.count = count;
    }

    public Tag(Long id, String name, int count, ArticleType articleType) {
        this(name, count, articleType);
        this.id = id;
    }

    public Tag(String name, int count, ArticleType articleType) {
        this.name = name;
        this.count = count;
        this.articleType = articleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public String tagUrl() {
        return KpUrlPaths.TAG_WITH_SLASH + name.toLowerCase();
    }
}
