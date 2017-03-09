package com.kp.service.user;

import com.kp.domain.User;
import com.kp.domain.model.Role;
import com.kp.dto.UserModel;
import com.kp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 08/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldGetOneById() {
        User user = new User(1l, "mail", "pass");
        when(userRepository.findOne(1l)).thenReturn(user);

        final User actualOne = service.getOne(1l);

        assertNotNull(actualOne);
    }

    @Test
    public void shouldCreateOne() {
        UserModel userModel = new UserModel();
        userModel.setUsername("username");
        userModel.setEmail("email");
        userModel.setFullname("Turgay Can");
        userModel.setPassword("pass");
        when(passwordEncoder.encode(userModel.getPassword())).thenReturn("hashedPassword");

        service.create(userModel);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        Mockito.verify(userRepository, atLeastOnce()).save(userCaptor.capture());

        final User captorTransientUser = userCaptor.getValue();

        assertEquals(Role.USER, captorTransientUser.getRole());
        assertEquals("hashedPassword", captorTransientUser.getPassword());
        assertEquals("email", captorTransientUser.getEmail());
        assertEquals("username", captorTransientUser.getUsername());
        assertEquals("Turgay Can", captorTransientUser.getFullname());
    }

    @Test
    public void shouldCreateGuestUser() {
        when(userRepository.findOneByEmail("email")).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

        service.createGuest("email");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        Mockito.verify(userRepository, atLeastOnce()).save(userCaptor.capture());

        final User captorTransientUser = userCaptor.getValue();

        assertEquals(Role.GUEST, captorTransientUser.getRole());
        assertEquals("hashedPassword", captorTransientUser.getPassword());
        assertEquals("email", captorTransientUser.getEmail());
        assertEquals("email", captorTransientUser.getUsername());
        assertNull(captorTransientUser.getFullname());
    }

    @Test
    public void shouldNotCreateGuestUserIfEmailIsRegistered() {
        when(userRepository.findOneByEmail("email")).thenReturn(new User());

        final User persistedGuest = service.createGuest("email");

        Mockito.verify(passwordEncoder, never()).encode(anyString());

        assertNotNull(persistedGuest);
    }

    @Test
    public void shouldMergeWithPassword() {
        User user = new User(1l, "mail", "pass");
        when(passwordEncoder.encode("pass")).thenReturn("hashedPassword");

        service.mergeWithPassword(user);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository, atLeastOnce()).save(userCaptor.capture());
        final User captorTransientUser = userCaptor.getValue();
        assertEquals(Role.GUEST, captorTransientUser.getRole());
        assertEquals("hashedPassword", captorTransientUser.getPassword());
    }

    @Test
    public void shouldMerge() {
        User user = new User(1l, "mail", "pass");

        service.merge(user);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository, atLeastOnce()).save(userCaptor.capture());
        final User captorTransientUser = userCaptor.getValue();
        assertEquals(Role.GUEST, captorTransientUser.getRole());
        assertEquals("pass", captorTransientUser.getPassword());
        assertEquals("mail", captorTransientUser.getEmail());
    }

    @Test
    public void shouldGetUserByEmail() {
        final User user = new User(1l, "email", "pass");
        when(userRepository.findOneByEmail("email")).thenReturn(user);

        final User found = service.getUserByEmail("Email");

        assertEquals(Long.valueOf(1), found.getId());
        assertEquals("email", found.getEmail());
    }

    @Test
    public void shouldReturnNullIfEmailNotFound() {
        when(userRepository.findOneByEmail("email")).thenReturn(null);

        final User found = service.getUserByEmail("Email");

        assertNull(found);
    }

    @Test
    public void shouldReturnFalseIfPasswordNotMatched() {
        when(passwordEncoder.matches("pass", "hashPass")).thenReturn(false);
        final boolean notMatch = service.isPasswordValid("pass", "hashPass");

        assertFalse(notMatch);
    }

    @Test
    public void shouldReturnTrueIfPasswordMatched() {
        when(passwordEncoder.matches("pass", "hashPass")).thenReturn(true);
        final boolean isMatch = service.isPasswordValid("pass", "hashPass");

        assertTrue(isMatch);
    }

    @Test
    public void shouldReturnAllCountOfRegisteredUsers() {
        when(userRepository.count()).thenReturn(10l);

        final long totalUsersCount = service.countOfTotalUsers();

        assertEquals(10l, totalUsersCount);
    }

    @Test
    public void shouldGetUserByUsername() {
        User user = new User(1l, "mail", "pass", "username");
        when(userRepository.findOneByUsername("username")).thenReturn(user);

        final User found = service.getUserByUsername("username");

        assertNotNull(found);
        assertEquals(Long.valueOf(1l), found.getId());
    }

    @Test
    public void shouldNotGetUserByUsername() {
        when(userRepository.findOneByUsername("username")).thenReturn(null);

        final User found = service.getUserByUsername("username");

        assertNull(found);
    }

    @Test
    public void shouldGetUserByUsernameOrEmail() {
        User user = new User(1l, "mail", "pass", "username");
        when(userRepository.findByUsernameOrEmail("username", "username")).thenReturn(user);

        final User found = service.getUserByUsernameOrEmail("username");

        assertNotNull(found);
        assertEquals(Long.valueOf(1l), found.getId());
    }

    @Test
    public void shouldNotGetUserByUsernameOrEmail() {
        when(userRepository.findByUsernameOrEmail("username", "username")).thenReturn(null);

        final User found = service.getUserByUsernameOrEmail("username");

        assertNull(found);
    }

    @Test
    public void shouldGetAll() {
        User user = new User(1l, "mail", "pass", "username");
        when(userRepository.findAll()).thenReturn(newArrayList(user));

        final List<User> users = service.getAll();

        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void shouldGetUsersAsPageable() {
        User user = new User(1l, "mail", "pass", "username");
        when(userRepository.findPageableOrderByCreatedateDesc(any())).thenReturn(new PageImpl<>(newArrayList(user)));

        final Page<User> users = service.getUsersAsPageable(1, 10);

        assertNotNull(users);
        assertEquals(1, users.getNumberOfElements());
    }

    @Test
    public void shouldGetUsersAsPageableNotCacheable() {
        User user = new User(1l, "mail", "pass", "username");
        when(userRepository.findPageableOrderByCreatedateDesc(any())).thenReturn(new PageImpl<>(newArrayList(user)));

        final Page<User> users = service.getUsersAsPageableNotCacheable(1, 10);

        assertNotNull(users);
        assertEquals(1, users.getNumberOfElements());
    }
}