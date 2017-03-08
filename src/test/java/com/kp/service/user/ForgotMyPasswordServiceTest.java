package com.kp.service.user;

import com.kp.domain.Notification;
import com.kp.domain.User;
import com.kp.exception.model.KPException;
import com.kp.service.notification.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 08/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ForgotMyPasswordServiceTest {

    @InjectMocks
    private ForgotMyPasswordService service;

    @Mock
    private UserService userService;

    @Mock
    private NotificationService notificationService;

    @Test
    public void shouldSentMail() {
        final User persistedUser = new User(1l, "email", "pass", "turgay");
        persistedUser.setFullname("Turgay Can");
        when(userService.getUserByUsernameOrEmail("turgay")).thenReturn(persistedUser);
        final Notification notification = new Notification();
        notification.setContent("$adsoyad$, şifreniz : $password$");
        when(notificationService.getById(1)).thenReturn(notification);

        when(notificationService.sendMail(notification)).thenReturn(true);

        service.execute("turgay");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        Mockito.verify(userService, atLeastOnce()).mergeWithPassword(userCaptor.capture());

        assertEquals(8, userCaptor.getValue().getPassword().length());

    }

    @Test(expected = KPException.class)
    public void shouldThrowExceptionIfNotSentMail() {
        final User persistedUser = new User(1l, "email", "pass", "turgay");
        persistedUser.setFullname("Turgay Can");
        when(userService.getUserByUsernameOrEmail("turgay")).thenReturn(persistedUser);
        final Notification notification = new Notification();
        notification.setContent("$adsoyad$, şifreniz : $password$");
        when(notificationService.getById(1)).thenReturn(notification);

        when(notificationService.sendMail(notification)).thenReturn(false);

        service.execute("turgay");
    }

}