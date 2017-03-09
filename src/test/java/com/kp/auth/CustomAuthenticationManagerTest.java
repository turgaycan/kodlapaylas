package com.kp.auth;

import com.kp.domain.User;
import com.kp.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 09/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomAuthenticationManagerTest {

    @InjectMocks
    private CustomAuthenticationManager manager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserService userService;

    @Test
    public void shouldThrowBadCredentialsUserNameBlankIrNotEmpty() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, null);

        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException bce) {
            assertEquals("Username is not empty/blank", bce.getMessage());
        }
    }

    @Test
    public void shouldThrowBadCredentialsPasswordBlankOrNotEmpty() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("name", null, null);

        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException bce) {
            assertEquals("Password is not empty/blank", bce.getMessage());
        }
    }

    @Test
    public void shouldThrowBadCredentialsUsernameOrEmailNotFound() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("name", "pass", null);
        when(userService.getUserByUsernameOrEmail("name")).thenReturn(null);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException bce) {
            assertEquals("Username/Email not found.", bce.getMessage());
        }
    }

    @Test
    public void shouldThrowBadCredentialsUserNotFound() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("name", "pass", null);
        when(userService.getUserByUsernameOrEmail("name")).thenReturn(new User(1l, "mail", "pass", "name"));
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(null);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException bce) {
            assertEquals("User not found.", bce.getMessage());
        }
    }

    @Test
    public void shouldAuthenticate() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("name", "pass", null);
        final User persisted = new User(1l, "mail", "pass", "name");
        when(userService.getUserByUsernameOrEmail("name")).thenReturn(persisted);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(persisted);

        final Authentication authenticated = manager.authenticate(authentication);

        assertEquals("pass", authenticated.getCredentials());

    }

    @Test
    public void shouldThrowBadCredentialsPasswordNotValid() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("name", "pass", null);
        final User persisted = new User(1l, "mail", "pass", "name");
        when(userService.getUserByUsernameOrEmail("name")).thenReturn(persisted);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(persisted);
        when(userService.isPasswordValid(anyString(), anyString())).thenReturn(false);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException bce) {
            assertEquals("Wrong password.", bce.getMessage());
        }
    }

}