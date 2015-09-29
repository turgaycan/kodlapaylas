package com.kp.domain;

import com.kp.domain.base.BaseEntity;
import com.kp.domain.model.BaseStatus;

import javax.persistence.*;

/**
 * Created by turgaycan on 9/28/15.
 */
@Entity
@Table(name = "subscriber", schema = "kp")
@SequenceGenerator(name = "subscriber_id_seq", sequenceName = "subscriber_id_seq", allocationSize = 1)
public class Subscriber extends BaseEntity {
    private static final long serialVersionUID = -992269196309225604L;

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "subscriber_id_seq")
    private Long id;
    @Column(unique = true, length = 50, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private BaseStatus status = BaseStatus.Active;

    public Subscriber() {
    }

    public Subscriber(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }
}
