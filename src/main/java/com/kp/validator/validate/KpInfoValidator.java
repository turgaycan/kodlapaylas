package com.kp.validator.validate;

import org.springframework.validation.Errors;

/**
 * Created by tcan on 05/10/15.
 */
public interface KpInfoValidator<T> {
    void validate(T target, Errors errors);
}
