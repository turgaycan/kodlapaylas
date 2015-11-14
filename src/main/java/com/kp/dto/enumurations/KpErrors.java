package com.kp.dto.enumurations;

/**
 * Created by tcan on 10/10/15.
 */
public enum KpErrors {
    NONE(""),
    NOT_FOUND("Bulunamadı.."),
    ALREADY_EXISTS("Sistemde mevcut!"),
    NOT_FOUND_SEARCH("Oops! It looks like nothing was found at this location. Maybe try another link\n or a search ? ");

    private String msg;

    KpErrors(String msg) {
        msg = msg;
    }

    public String getCode() {
        return msg;
    }
}
