package com.kp.validator;

import com.kp.dto.UserModel;
import com.kp.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by turgaycan on 9/22/15.
 */
@Component
public class UserModelValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserModelValidator.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserModel.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.info("Validating {}", target);
        UserModel form = (UserModel) target;
        if (validatePasswords(errors, form)) return;
        if (validateEmail(errors, form)) return;
    }

    private boolean validatePasswords(Errors errors, UserModel form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            LOGGER.info("Validating pass {}", form.getPassword());
            errors.reject("password.no_match", "Passwords do not match");
            return true;
        }
        return false;
    }

    private boolean validateEmail(Errors errors, UserModel form) {
        if (userService.getUserByEmail(form.getEmail()) != null) {
            LOGGER.info("Validating {} email", form.getEmail());
            errors.reject("email.exists", "User with this email already exists");
            return true;
        }
        return false;
    }
}
