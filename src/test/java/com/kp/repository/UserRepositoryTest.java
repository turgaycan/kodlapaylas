package com.kp.repository;

import com.kp.config.RepositoryTest;
import com.kp.domain.User;
import com.kp.domain.model.Role;
import com.kp.domain.model.UserStatus;
import com.kp.domain.spec.PageSpec;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by turgaycan on 9/26/15.
 */
@RunWith(SpringRunner.class)
@RepositoryTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        for (long index = 1; index <= 5; index++) {
            User user = new User(index, "test@email.com" + index, "pass" + index, "user" + index);
            user.setCreatedate(new Date());
            user.setRole(Role.USER);
            user.setWebsite("website");
            user.setFullname("turgay can" + index);
            user.setUserStatus(UserStatus.NEW);
            user.setAvatar("avatar" + index);
            userRepository.save(user);
        }
        userRepository.flush();
    }

    @Test
    public void shouldFindOneByEmail() {
        final User persistedOne = userRepository.findOneByEmail("test@email.com1");

        assertNotNull(persistedOne);
    }

    @Test
    public void shouldFindOneByUsername() {
        final User persistedOne = userRepository.findOneByUsername("user1");

        assertNotNull(persistedOne);
    }

    @Test
    public void shouldFindOneByUsernameOrEmail() {
        final User persistedOne = userRepository.findByUsernameOrEmail("user1", "test@email.com1");

        assertNotNull(persistedOne);
    }

    @Test
    public void shouldFindPageableOrderByCreatedateDesc() {
        final Pageable page = PageSpec.buildPageSpecificationByFieldDesc(0, 4, "createdate");
        final Page<User> users = userRepository.findPageableOrderByCreatedateDesc(page);

        assertNotNull(users);
        assertEquals(5, users.getTotalElements());
    }
}