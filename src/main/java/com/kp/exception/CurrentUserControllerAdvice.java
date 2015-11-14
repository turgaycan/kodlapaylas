package com.kp.exception;

import com.kp.domain.User;
import com.kp.domain.model.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by turgaycan on 9/22/15.
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);

    @ModelAttribute
    public User currentUser(Authentication authentication) {
        if (authentication != null &&
                authentication.getPrincipal() != null &&
                authentication.getPrincipal() instanceof User
                ) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

}
