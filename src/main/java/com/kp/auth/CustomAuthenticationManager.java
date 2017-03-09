package com.kp.auth;

import com.kp.domain.User;
import com.kp.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

/**
 * Created by tcan on 31/01/17.
 */

public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        if (StringUtils.isBlank(username)) {
            throw new BadCredentialsException("Username is not empty/blank");
        }

        String password = (String) authentication.getCredentials();
        if (StringUtils.isBlank(password)) {
            throw new BadCredentialsException("Password is not empty/blank");
        }

        final User persisted = userService.getUserByUsernameOrEmail(username.toLowerCase());

        if (persisted == null) {
            throw new BadCredentialsException("Username/Email not found.");
        }

        UserDetails user = userDetailsService.loadUserByUsername(persisted.getUsername().toLowerCase());

        if (user == null) {
            throw new BadCredentialsException("User not found.");
        }

        boolean isPasswordValid = password.equals(user.getPassword());
        if (isPasswordValid) {
            return getAuthentication(password, user);
        }
        if (!userService.isPasswordValid(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        return getAuthentication(password, user);
    }

    private Authentication getAuthentication(String password, UserDetails user) {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }


}
