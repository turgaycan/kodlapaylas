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
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;

    public Tag() {
    }

    public Tag(Long id, int count) {
        this.id = id;
        this.count = count;
    }

    public Tag(Long id, String name, int count, Category category) {
        this(name, count, category);
        this.id = id;
    }

    public Tag(String name, int count, Category category) {
        this.name = name;
        this.count = count;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String tagUrl() {
        return KpUrlPaths.TAG_WITH_SLASH + name.toLowerCase();
    }
}
