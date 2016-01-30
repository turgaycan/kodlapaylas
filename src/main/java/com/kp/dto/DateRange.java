package com.kp.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tcan on 26/12/15.
 */
public class DateRange implements Serializable {
    private static final long serialVersionUID = -7476245627618311122L;

    private Date startDate;
    private Date endDate;

    public DateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
