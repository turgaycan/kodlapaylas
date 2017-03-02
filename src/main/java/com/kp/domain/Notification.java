package com.kp.domain;

import com.kp.domain.base.BaseEntity;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by tcan on 26/02/17.
 */
@Entity
@Table(name = "notification", schema = "kp")
@SequenceGenerator(name = "notification_id_seq", sequenceName = "notification_id_seq", allocationSize = 1)
public class Notification extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "notification_id_seq")
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private int priority = 10;

    @Transient
    private String recipient;
    @Transient
    private String from;
    @Transient
    private Map<String, String> paramKeyValuePair;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Map<String, String> getParamKeyValuePair() {
        return paramKeyValuePair;
    }

    public void setParamKeyValuePair(Map<String, String> paramKeyValuePair) {
        this.paramKeyValuePair = paramKeyValuePair;
    }
}
