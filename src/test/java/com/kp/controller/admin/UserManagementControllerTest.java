package com.kp.controller.admin;

import com.kp.domain.User;
import com.kp.dto.UserUpdateInfo;
import com.kp.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 11/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserManagementControllerTest {

    @InjectMocks
    private UserManagementController controller;

    @Mock
    private UserService userService;

    private List<User> userList = new ArrayList<>();

    @Before
    public void init() {
        for (long index = 1; index < 5; index++) {
            final User user = new User(index, "email" + index, "pass" + index);
            userList.add(user);
        }
    }

    @Test
    public void shouldListUsers() {
        when(userService.getAll()).thenReturn(userList);
        final ModelAndView mav = controller.list();

        assertEquals("/admin/users", mav.getViewName());
        assertEquals(userList, mav.getModel().get("users"));
    }

    @Test
    public void shouldListUsersAsPageable() {
        when(userService.getUsersAsPageable(0, 10)).thenReturn(new PageImpl<>(userList));
        final ModelAndView mav = controller.list(1, 10);

        assertEquals("/admin/users", mav.getViewName());
        assertEquals(userList, mav.getModel().get("users"));
    }

    @Test
    public void shouldShowOne() {
        final User user = new User(1l, "email", "pass");
        when(userService.getOne(1l)).thenReturn(user);
        final ModelAndView mav = controller.showOne(1l);

        assertEquals("/admin/user", mav.getViewName());
        assertEquals(user, mav.getModel().get("user"));
    }

    @Test
    public void shouldNotUpdateIfValidationOneOfErrors() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");
        errors.reject("article", "errorMessage");

        final UserUpdateInfo userUpdateInfo = new UserUpdateInfo();
        userUpdateInfo.setId(1l);
        final User user = new User(1l, "email", "pass");
        when(userService.getOne(1l)).thenReturn(user);

        final ModelAndView mav = controller.updateOne(userUpdateInfo, errors);

        assertEquals("/admin/user", mav.getViewName());
        final String errorMessage = (String) mav.getModel().get("errors");
        assertEquals("errorMessage<br />", errorMessage);
        assertEquals(user, mav.getModel().get("user"));
    }

    @Test
    public void shouldUpdateOne() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");

        final UserUpdateInfo userUpdateInfo = new UserUpdateInfo();
        userUpdateInfo.setId(1l);
        final User user = new User(1l, "email", "pass");
        when(userService.getOne(1l)).thenReturn(user);
        when(userService.merge(any(User.class))).thenReturn(user);

        final ModelAndView mav = controller.updateOne(userUpdateInfo, errors);

        assertEquals("redirect:/show/user/1", mav.getViewName());
    }

}