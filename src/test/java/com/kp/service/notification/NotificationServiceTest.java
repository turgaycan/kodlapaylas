package com.kp.service.notification;

import com.kp.domain.Notification;
import com.kp.repository.NotificationRepository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * use greenmail for integration tests!
 * Created by tcan on 27/02/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService service;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private JavaMailSender mailSender;

    @Captor
    private ArgumentCaptor<MimeMessagePreparator> preparatorArgumentCaptor;

    @Test
    public void shouldGetById(){
        when(notificationRepository.findOne(1l)).thenReturn(new Notification());
        final Notification one = service.getById(1l);

        assertNotNull(one);
    }

    @Ignore
    @Test
    public void shouldSendMail() throws Exception {
        Notification notification = new Notification();
        notification.setRecipient("turgay.can@yandex.com.tr");
        notification.setFrom("info@turgaycan.com");
        notification.setSubject("test");
        notification.setContent("Test message content");

        final boolean isSent = service.sendMail(notification);

        assertTrue(isSent);

        verify(mailSender).send(preparatorArgumentCaptor.capture());

        assertEquals("", preparatorArgumentCaptor.getValue());

    }
}