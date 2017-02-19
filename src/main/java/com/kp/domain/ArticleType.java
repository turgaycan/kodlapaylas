package com.kp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kp.domain.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by turgaycan on 9/22/15.
 */

@Entity
@Table(name = "article_type", schema = "kp")
@SequenceGenerator(name = "article_type_id_seq", sequenceName = "article_type_id_seq", allocationSize = 1)
public class ArticleType extends BaseEntity {
    private static final long serialVersionUID = -723818945422023439L;

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "article_type_id_seq")
    private Long id;
    @Column(nullable = false)
    private String name;
    private String icon;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @JsonIgnore
    private ArticleType parent;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdate;
    private boolean child;

    public ArticleType() {
    }

    public ArticleType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ArticleType(Long id, String name, ArticleType parent) {
        this(id, name);
        this.parent = parent;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ArticleType getParent() {
        return parent;
    }

    public void setParent(ArticleType parent) {
        this.parent = parent;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public boolean isChild(){
        return child;
    }

    public void setChild(boolean child) {
        this.child = child;
    }

    public ArticleType getRoot() {
        while (getParent() != null) {
            return getParent().getRoot();
        }
        return this;
    }

    public boolean isRoot(){
        return getParent() == null;
    }

    public boolean isChildCategory(){
        return !isRoot();
    }


}
