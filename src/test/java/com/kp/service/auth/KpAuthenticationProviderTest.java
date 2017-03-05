package com.kp.service.auth;

import com.kp.auth.CustomAuthenticationManager;
import com.kp.domain.User;
import com.kp.service.user.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;


/**
 * Created by tcan on 05/03/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({KpAuthenticationProvider.class, LoggerFactory.class})
public class KpAuthenticationProviderTest {

    @InjectMocks
    private KpAuthenticationProvider provider;

    @Mock
    private UserService userService;

    @Mock
    private CustomAuthenticationManager authenticationManager;

    private Logger LOGGER;

    @Before
    public void setup() {
        PowerMockito.mockStatic(LoggerFactory.class);
        LOGGER = mock(Logger.class);
        PowerMockito.when(LoggerFactory.getLogger(KpAuthenticationProvider.class)).thenReturn(LOGGER);
    }

    @Test
    public void shouldReturnNullAndNotLoadUserByUsernameIfNotFound() {
        when(userService.getUserByUsernameOrEmail("turgay")).thenReturn(null);

        final UserDetails turgay = provider.loadUserByUsername("turgay");

        assertNull(turgay);
    }

    @Test
    public void shouldLoadUserByUsername() {
        final User persistedUser = new User(1l, "email", "pass", "turgay");
        when(userService.getUserByUsernameOrEmail("turgay")).thenReturn(persistedUser);
        final UserDetails turgay = provider.loadUserByUsername("turgay");

        assertNotNull(turgay);
        assertEquals("turgay", turgay.getUsername());
    }

    @Ignore("revised!")
    @Test
    public void shouldLogin() {
        when(authenticationManager.authenticate(any())).thenReturn(new UsernamePasswordAuthenticationToken(null, null, null));

        provider.login(new User(1l,"email", "pass", "turgay"), "pass");

        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        verify(LOGGER).debug(logCaptor.capture());

        assertEquals("Auto login turgay successfully!", logCaptor.getValue());
    }

}