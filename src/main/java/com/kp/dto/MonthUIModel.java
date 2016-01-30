package com.kp.dto;

import java.io.Serializable;

/**
 * Created by tcan on 26/12/15.
 */
public class MonthUIModel implements Serializable {

    private static final long serialVersionUID = -2568777221944067441L;

    private String monthName;
    private int monthValue;

    public MonthUIModel(int monthValue, String monthName) {
        this.monthValue = monthValue;
        this.monthName = monthName;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(int monthValue) {
        this.monthValue = monthValue;
    }
}
