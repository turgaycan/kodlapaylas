package com.kp.domain;

import com.kp.domain.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by turgaycan on 9/22/15.
 */
@Entity
@Table(name = "kp_statistic", schema = "kp")
@SequenceGenerator(name = "kp_statistic_id_seq", sequenceName = "kp_statistic_id_seq", allocationSize = 1)
public class KPStatistic extends BaseEntity {
    private static final long serialVersionUID = -5431172826465792848L;

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "kp_statistic_id_seq")
    private Long id;
    private int counter;
    @Column(name = "ip_address", nullable = false)
    private String ipAddress;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdate;

    public KPStatistic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
