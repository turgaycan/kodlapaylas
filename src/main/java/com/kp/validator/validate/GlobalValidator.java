package com.kp.validator.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by tcan on 05/10/15.
 */
@Component
public class GlobalValidator implements Validator {
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof Validateable) {
            KpInfoValidator validator = ((Validateable) target).validator();

            if (validator != null) {
                autowireCapableBeanFactory.autowireBean(validator);
                validator.validate(target, errors);
            }
        }
    }
}
