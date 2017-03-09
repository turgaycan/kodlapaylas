package com.kp.repository;

import com.kp.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by turgaycan on 9/28/15.
 */
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Subscriber findByEmail(String email);
}
