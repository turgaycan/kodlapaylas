package com.kp.repository;

import com.kp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by turgaycan on 9/20/15.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);
}
