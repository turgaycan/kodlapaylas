package com.kp.service.security;

import com.kp.domain.User;
import com.kp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by tcan on 06/10/15.
 */
@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    public boolean isKpAuthenticated() {
        return getKpAuthentication() != null && getKpAuthentication().isAuthenticated() &&
                !getKpAuthentication().getName().equals("anonymousUser");
    }

    public boolean isKpNotAuthenticated() {
        return !isKpAuthenticated();
    }

    public User getCurrentUser() {
        final UserDetails current = (UserDetails) getKpAuthentication().getPrincipal();
        if(current == null){
            return null;
        }
        return userService.getUserByUsernameOrEmail(current.getUsername());
    }

    public Authentication getKpAuthentication() {
        return getContext()
                .getAuthentication();
    }

    private SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }

    public UserDetails getLoggeduser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails);
        }

        return null;
    }


}
