package com.kp.repository;

import com.kp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by turgaycan on 9/20/15.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);

    User findOneByUsername(String username);

    User findByUsernameOrEmail(String username, String email);

    @Query(value = "SELECT u From User u ORDER BY u.createdate DESC")
    Page<User> findPageableOrderByCreatedateDesc(Pageable page);
}
