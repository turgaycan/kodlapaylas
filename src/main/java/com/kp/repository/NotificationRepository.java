package com.kp.repository;

import com.kp.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tcan on 27/02/17.
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
