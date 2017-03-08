package com.kp.service.user;

import com.kp.domain.Notification;
import com.kp.domain.User;
import com.kp.exception.model.KPException;
import com.kp.service.notification.NotificationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tcan on 26/02/17.
 */
@Service
public class ForgotMyPasswordService {

    private static final long FORGOT_MAIL_TEMPLATE_ID = 1;
    private static final int PASSWORD_LENGTH = 8;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Transactional(rollbackFor = KPException.class)
    public void execute(String username) {
        final User currentUser = userService.getUserByUsernameOrEmail(username);
        final String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(PASSWORD_LENGTH);
        currentUser.setPassword(randomAlphanumeric);
        userService.mergeWithPassword(currentUser);

        Notification notification = notificationService.getById(FORGOT_MAIL_TEMPLATE_ID);
        final String preparedContent = prepare(currentUser, notification.getContent());
        notification.setContent(preparedContent);

        boolean isSent = notificationService.sendMail(notification);

        if (!isSent) {
            throw new KPException("Mail gönderimi yapılırken hata oluştu!");
        }
    }

    private String prepare(User currentUser, String content) {
        content.replaceAll("$adsoyad$", StringUtils.isNotBlank(currentUser.getFullname()) ? currentUser.getFullname() : currentUser.getEmail());
        content.replaceAll("$password$", currentUser.getPassword());
        return content;
    }
}
