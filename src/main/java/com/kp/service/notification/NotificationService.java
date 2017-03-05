package com.kp.service.notification;

import com.kp.domain.Notification;
import com.kp.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


/**
 * Created by tcan on 26/02/17.
 */
@Service
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Notification getById(long id) {
        return notificationRepository.findOne(id);
    }

    public boolean sendMail(Notification notification) {
        try {

            final MimeMessagePreparator prepare = prepare(notification);
            mailSender.send(prepare);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error occured when sending mail! Exception message : {0}", e.getMessage());
            return false;
        }
    }

    public MimeMessagePreparator prepare(Notification notification) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(notification.getFrom());
            messageHelper.setTo(notification.getRecipient());
            messageHelper.setSubject(notification.getSubject());
            messageHelper.setText(notification.getContent());
        };
    }
}
