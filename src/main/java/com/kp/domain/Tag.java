package com.kp.domain;

import com.kp.domain.base.BaseEntity;

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
    private ArticleType articleType;

    public Tag() {
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
}
