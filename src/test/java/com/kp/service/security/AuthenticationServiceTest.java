package com.kp.service.security;

import com.kp.domain.User;
import com.kp.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


/**
 * Created by tcan on 06/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService service;

    @Mock
    private UserService userService;

    @Test
    public void shouldReturnFalseIfNoAuth() {
        SecurityContextHolder.getContext().setAuthentication(null);

        assertFalse(service.isKpAuthenticated());
    }

    @Test
    public void shouldReturnFalseIfNotAuth() {
        Authentication notAuth = new UsernamePasswordAuthenticationToken("notauth", "pass");
        SecurityContextHolder.getContext().setAuthentication(notAuth);

        assertFalse(service.isKpAuthenticated());
    }

    @Test
    public void shouldReturnFalseIfUserIsAnonymous() {
        Authentication auth = new UsernamePasswordAuthenticationToken("anonymousUser", "pass", null);

        SecurityContextHolder.getContext().setAuthentication(auth);

        assertFalse(service.isKpAuthenticated());
    }

    @Test
    public void shouldReturnNullIfNotAuthWhenGetCurrentUser() {
        Authentication notAuth = new UsernamePasswordAuthenticationToken(null, "pass");
        SecurityContextHolder.getContext().setAuthentication(notAuth);

        assertNull(service.getCurrentUser());
    }

    @Test
    public void shouldReturnNullIfAuthButNotFindUsername() {
        Authentication notAuth = new UsernamePasswordAuthenticationToken(new User(1l, "email", "pass", "username"), "pass");
        SecurityContextHolder.getContext().setAuthentication(notAuth);

        when(userService.getUserByUsernameOrEmail("username")).thenReturn(null);
        assertNull(service.getCurrentUser());
    }

    @Test
    public void shouldReturnNullIfNotAuth1() {
        Authentication auth = new UsernamePasswordAuthenticationToken(new User(1l, "email", "pass", "username"), "pass");
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(userService.getUserByUsernameOrEmail(anyString())).thenReturn(new User(1l, "email", "pass", "username"));

        final User currentUser = service.getCurrentUser();

        assertNotNull(currentUser);
        assertEquals("username", currentUser.getUsername());
    }

    @Test
    public void shouldReturnNullWhenGetLoggedUser() {
        Authentication notAuth = new UsernamePasswordAuthenticationToken("username", "pass");
        SecurityContextHolder.getContext().setAuthentication(notAuth);

        final UserDetails loggedUser = service.getLoggedUser();
        assertNull(loggedUser);
    }

    @Test
    public void shouldGetLoggedUser() {
        final User principal = new User(1l, "email", "pass", "username");
        UsernamePasswordAuthenticationToken notAuth = new UsernamePasswordAuthenticationToken(principal, "pass");
        notAuth.setDetails(principal);
        SecurityContextHolder.getContext().setAuthentication(notAuth);

        final UserDetails loggedUser = service.getLoggedUser();
        assertNotNull(loggedUser);
    }
}