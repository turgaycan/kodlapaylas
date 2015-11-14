package com.kp.validator.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Created by tcan on 05/10/15.
 */
@ControllerAdvice
public class KpValidation {
    @Autowired
    private GlobalValidator globalValidator;

    @InitBinder
    public void bind(WebDataBinder binder) {
        binder.addValidators(globalValidator);
    }
}
