package com.kp.exception.model;

/**
 * Created by turgaycan on 9/23/15.
 */
public class ErrorResponse extends RuntimeException {

    public ErrorResponse() {
    }

    public ErrorResponse(String message) {
        super(message);
    }

    public ErrorResponse(Throwable cause) {
        super(cause);
    }
}
