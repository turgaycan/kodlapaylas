package com.kp.exception.model;

import com.kp.dto.enumurations.KpErrors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.io.Serializable;

/**
 * Created by tcan on 03/10/15.
 */
public class KpErrorResponse implements Serializable {
    private static final long serialVersionUID = -3249054874895290704L;

    private Errors errors;
    private KpErrors kpErrors = KpErrors.NONE;
    private HttpStatus httpStatus;

    public KpErrorResponse() {
    }

    public KpErrorResponse(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public KpErrorResponse(Errors errors) {
        this.errors = errors;
    }

    public KpErrorResponse(KpErrors kpErrors) {
        this.kpErrors = kpErrors;
    }

    public KpErrorResponse(Errors errors, KpErrors kpErrors) {
        this.errors = errors;
        this.kpErrors = kpErrors;
    }

    public KpErrors getKpErrors() {
        return kpErrors;
    }

    public void setKpErrors(KpErrors kpErrors) {
        this.kpErrors = kpErrors;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public boolean hasErrors() {
        return errors.hasErrors() || kpErrors != KpErrors.NONE;
    }

}
