package com.kp.service.notification;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.kp.domain.Notification;
import com.kp.repository.NotificationRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by tcan on 27/02/17.
 */
@SpringBootApplication
@DirtiesContext
@PropertySource(value = "classpath:/application-test.properties")
@ActiveProfiles("test")
@Configuration
@ComponentScan(basePackages = {
        "com.kp.service", "com.kp.exception", "com.kp.util", "com.kp.validator"
})
@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    private GreenMail smtpServer;

    @Before
    public void setUp() throws Exception {
        smtpServer = new GreenMail(new ServerSetup(25, null, "smtp"));
        smtpServer.start();
    }

    @Test
    public void shouldSendMail() throws Exception {
        Notification notification = new Notification();
        notification.setRecipient("turgay.can@yandex.com.tr");
        notification.setFrom("info@turgaycan.com");
        notification.setSubject("test");
        notification.setContent("Test message content");

        final boolean isSent = notificationService.sendMail(notification);

        assertTrue(isSent);
        String content = "<span>" + notification.getContent() + "</span>";
        assertReceivedMessageContains(content);
    }

    private void assertReceivedMessageContains(String expected) throws IOException, MessagingException {
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        String content = (String) receivedMessages[0].getContent();
        System.out.println(content);
        assertTrue(content.contains(expected));
    }

    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }
}