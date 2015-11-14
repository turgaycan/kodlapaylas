package com.kp.service.security;

import com.kp.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by tcan on 06/10/15.
 */
@Service
public class AuthenticationService {

//    @Autowired
//    private SecurityExpressionOperations securityExpressionOperations;

    public boolean isKpAuthenticated() {
        return getKpAuthentication().isAuthenticated() &&
//                securityExpressionOperations.isFullyAuthenticated()
                !getKpAuthentication().getName().equals("anonymousUser");
    }

    public boolean isKpNotAuthenticated() {
        return !isKpAuthenticated();
    }

    public User getCurrentUser() {
        return (User) getKpAuthentication().getPrincipal();
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
}
