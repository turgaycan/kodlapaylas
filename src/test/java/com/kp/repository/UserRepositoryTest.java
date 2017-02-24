package com.kp.repository;

import com.kp.config.RepositoryTest;
import com.kp.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void init(){
        User user = new User("test@email.com","email", "email");
        userRepository.saveAndFlush(user);
    }

    @Test
    public void testFindOneByEmail() throws Exception {
        final User persistedOne = userRepository.findOneByUsername("email");

        assertNotNull(persistedOne);
    }
}