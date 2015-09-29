package com.kp.service.currentuser;

import com.kp.domain.model.CurrentUser;
import com.kp.domain.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by turgaycan on 9/22/15.
 */
@Service
public class CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserService.class);

    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }
}
