package com.kp.service.user;

import com.kp.domain.User;
import com.kp.domain.model.Role;
import com.kp.domain.model.UserStatus;
import com.kp.domain.spec.PageSpec;
import com.kp.dto.UserModel;
import com.kp.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by turgaycan on 9/20/15.
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<User> getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public User getOne(long id) {
        LOGGER.debug("Getting user={}", id);
        return userRepository.findOne(id);
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
        user.setUsername(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        user.setCreatedate(new Date());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User createGuest(String email) {
        User foundUser = getUserByEmail(email);
        if (foundUser != null) {
            LOGGER.info("Founded user={}", email);
            return foundUser;
        }
        LOGGER.info("Create user={}", email);
        User user = new User();
        user.setEmail(email.toLowerCase());
        user.setUsername(email.toLowerCase());
        user.setCreatedate(new Date());
        user.setUserStatus(UserStatus.GUEST);
        final String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(8);
        LOGGER.info("Password={}", randomAlphanumeric);
        user.setPassword(passwordEncoder.encode(randomAlphanumeric));
        return userRepository.save(user);
    }

    @Transactional
    public User merge(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        final User currentUser = userRepository.findOneByEmail(email.toLowerCase());
        return currentUser;
    }

    public boolean isPasswordValid(String noneHashedPassword, String hashedPassword) {
        return passwordEncoder.matches(noneHashedPassword, hashedPassword);
    }

    @Transactional(readOnly = true)
    public long countOfTotalUsers() {
        return userRepository.count();
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Transactional(readOnly = true)
    public User getUserByUsernameOrEmail(String username) {
        return userRepository.findByUsernameOrEmail(username, username);
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Cacheable(value = "kpCache", key = "'pageable-' + #pageNum + '-' + #size", unless = "#result == null")
    public Page<User> findUsersAsPageable(int pageNum, int size) {
        return findUsersAsPageableNotCacheable(pageNum, size);
    }

    @Transactional(readOnly = true)
    public Page<User> findUsersAsPageableNotCacheable(int pageNum, int size) {
        final Pageable page = PageSpec.buildPageSpecificationByFieldDesc(pageNum, size, "createdate");
        return userRepository.findPageableOrderByCreatedateDesc(page);
    }

}
