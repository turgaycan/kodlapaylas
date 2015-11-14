package com.kp.service.auth;

import com.kp.domain.User;
import com.kp.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by tcan on 08/10/15.
 */
@Component
public class KpAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(KpAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String providedPassword = (String) authentication.getCredentials();
        //!authentication.getName().equals("anonymousUser")
        LOGGER.info("email : {}", email, "password : {}", providedPassword);
        User user = userService.findAndAuthenticateUser(email, providedPassword);
        if (user == null) {
            throw new BadCredentialsException("Username/Password does not match for " + authentication.getPrincipal());
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
