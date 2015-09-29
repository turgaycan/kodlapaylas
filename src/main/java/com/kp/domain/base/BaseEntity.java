package com.kp.domain.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by turgaycan on 9/20/15.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Column
    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
