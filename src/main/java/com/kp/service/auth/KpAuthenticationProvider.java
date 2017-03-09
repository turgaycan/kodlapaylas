package com.kp.service.auth;

import com.kp.auth.CustomAuthenticationManager;
import com.kp.domain.User;
import com.kp.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by tcan on 08/10/15.
 */
@Component
public class KpAuthenticationProvider implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KpAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            final User user = userService.getUserByUsernameOrEmail(username.toLowerCase());
            if (user == null) {
                LOGGER.debug("user not found with the provided username");
                return null;
            }
            LOGGER.debug(" user from username " + user.toString());
            final Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }


    public void login(UserDetails userDetails, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        final Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            LOGGER.debug(String.format("Auto login %s successfully!", userDetails.getUsername()));
            return;
        }
        SecurityContextHolder.clearContext();
    }


}
