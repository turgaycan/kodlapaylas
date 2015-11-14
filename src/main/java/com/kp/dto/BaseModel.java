package com.kp.dto;

import com.kp.exception.model.KpErrorResponse;

import java.io.Serializable;

/**
 * Created by tcan on 10/10/15.
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 5021059924587141329L;
    private KpErrorResponse errorResponse;

    public KpErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(KpErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public boolean hasErrors(){
        return errorResponse.hasErrors();
    }
}
