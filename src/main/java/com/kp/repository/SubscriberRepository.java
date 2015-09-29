package com.kp.repository;

import com.kp.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by turgaycan on 9/28/15.
 */
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Optional<Subscriber> findByEmail(String email);
}
