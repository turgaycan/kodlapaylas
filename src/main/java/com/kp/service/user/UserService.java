package com.kp.service.user;

import com.kp.domain.User;
import com.kp.domain.model.UserStatus;
import com.kp.dto.UserModel;
import com.kp.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by turgaycan on 9/20/15.
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<User> getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll(new Sort("email"));
    }

    @Transactional
    public User create(UserModel userModel) {
        LOGGER.info("Create user={}", userModel.getEmail());
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setCreatedate(new Date());
//        user.setPasswordsalt(UUID.randomUUID().toString());
//        user.setPassword(passwordEncoder.encode(userModel.getPassword() + user.getPasswordsalt()));
        return userRepository.save(user);
    }

    @Transactional
    public User createGuest(String email) {
        Optional<User> foundUser = getUserByEmail(email);
        if (foundUser.isPresent()) {
            LOGGER.info("Founded user={}", email);
            return foundUser.get();
        }
        LOGGER.info("Create user={}", email);
        User user = new User();
        user.setEmail(email.toLowerCase());
        user.setUsername(email.toLowerCase());
        user.setCreatedate(new Date());
        user.setUserStatus(UserStatus.GUEST);
//        user.setPasswordsalt(UUID.randomUUID().toString());
//        user.setPassword(passwordEncoder.encode(RandomStringUtils.random(8) + user.getPasswordsalt()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findAndAuthenticateUser(String email, String providedPassword) {
        User user = userRepository.findOneByEmail(email);
        if (user == null) {
            return null;
        }

//        String saltedPassword = providedPassword + user.getPasswordsalt();
//        if (passwordEncoder.matches(saltedPassword, user.getPassword())) {
//            return user;
//        }

        return null;
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findOneByEmail(email.toLowerCase()));
    }
}
