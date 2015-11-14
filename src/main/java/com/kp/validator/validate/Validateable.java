package com.kp.validator.validate;

import com.kp.validator.validate.KpInfoValidator;

/**
 * Created by tcan on 05/10/15.
 */
public interface Validateable<F> {
    public KpInfoValidator<F> validator();
}
