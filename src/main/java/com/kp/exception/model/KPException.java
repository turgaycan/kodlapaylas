package com.kp.exception.model;

/**
 * Created by turgaycan on 9/26/15.
 */
public class KPException extends RuntimeException {

    public KPException(String message) {
        super(message);
    }

    public KPException(String message, Throwable cause) {
        super(message, cause);
    }
}
