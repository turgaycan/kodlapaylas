package com.kp.domain;

import com.kp.domain.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by turgaycan on 9/22/15.
 */
@Entity
@Table(name = "seo_stat", schema = "kp")
@SequenceGenerator(name = "seo_stat_id_seq", sequenceName = "seo_stat_id_seq", allocationSize = 1)
public class SeoStatistic extends BaseEntity {

    private static final long serialVersionUID = -5053475182190655561L;

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seo_stat_id_seq")
    private Long id;
    @Column(nullable = false)
    private String domain;
    private String google;
    private String yahoo;
    private String yandex;
    @Column(name = "alexa_global")
    private String alexaGlobal;
    @Column(name = "alexa_local")
    private String alexaLocal;
    @Column(name = "alexa_backlink")
    private String alexaBacklink;
    private boolean dmoz;
    private int pageRank;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "google_backlink")
    private String googleBacklink;

    public SeoStatistic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getYahoo() {
        return yahoo;
    }

    public void setYahoo(String yahoo) {
        this.yahoo = yahoo;
    }

    public String getYandex() {
        return yandex;
    }

    public void setYandex(String yandex) {
        this.yandex = yandex;
    }

    public String getAlexaGlobal() {
        return alexaGlobal;
    }

    public void setAlexaGlobal(String alexaGlobal) {
        this.alexaGlobal = alexaGlobal;
    }

    public String getAlexaLocal() {
        return alexaLocal;
    }

    public void setAlexaLocal(String alexaLocal) {
        this.alexaLocal = alexaLocal;
    }

    public String getAlexaBacklink() {
        return alexaBacklink;
    }

    public void setAlexaBacklink(String alexaBacklink) {
        this.alexaBacklink = alexaBacklink;
    }

    public boolean isDmoz() {
        return dmoz;
    }

    public void setDmoz(boolean dmoz) {
        this.dmoz = dmoz;
    }

    public int getPageRank() {
        return pageRank;
    }

    public void setPageRank(int pageRank) {
        this.pageRank = pageRank;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getGoogleBacklink() {
        return googleBacklink;
    }

    public void setGoogleBacklink(String googleBacklink) {
        this.googleBacklink = googleBacklink;
    }
}
