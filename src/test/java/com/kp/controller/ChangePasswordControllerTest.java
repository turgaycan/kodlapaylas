package com.kp.controller;


import com.kp.domain.User;
import com.kp.dto.ChangePasswordModel;
import com.kp.service.security.AuthenticationService;
import com.kp.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 02/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordControllerTest {

    @InjectMocks
    private ChangePasswordController controller;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void shouldChangePasswordGet() {
        when(authenticationService.isKpNotAuthenticated()).thenReturn(false);
        final User user = new User(1l, "email", "pass");
        when(authenticationService.getCurrentUser()).thenReturn(user);

        final ModelAndView mav = controller.changePassword();

        assertEquals("/user/change-password", mav.getViewName());
        assertEquals(user, mav.getModel().get("currentUser"));
    }

    @Test
    public void shouldNotChangePasswordPostIfNotValidate() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");
        errors.rejectValue("pass", "", "Şifre yanlış");
        when(authenticationService.isKpNotAuthenticated()).thenReturn(false);
        final User user = new User(1l, "email", "pass");
        when(authenticationService.getCurrentUser()).thenReturn(user);

        final ModelAndView mav = controller.changePassword(new ChangePasswordModel(), errors);

        assertEquals("/user/change-password", mav.getViewName());
        assertEquals(user, mav.getModel().get("currentUser"));
        assertEquals("Şifre yanlış<br />", mav.getModel().get("errors"));
    }

    @Test
    public void shouldNotChangePasswordPostIfNotValidateForLogOut() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");
        errors.rejectValue("logout", "", "Giriş yapınız!");
        when(authenticationService.isKpNotAuthenticated()).thenReturn(false);
        final User user = new User(1l, "email", "pass");
        when(authenticationService.getCurrentUser()).thenReturn(user);

        final ModelAndView mav = controller.changePassword(new ChangePasswordModel(), errors);

        verify(authenticationService, atLeastOnce()).logout();

        assertEquals("redirect:/login", mav.getViewName());
    }

    @Test
    public void shouldChangePasswordPost() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");
        when(authenticationService.isKpNotAuthenticated()).thenReturn(false);
        final User user = new User(1l, "email", "pass");
        when(authenticationService.getCurrentUser()).thenReturn(user);
        when(userService.mergeWithPassword(user)).thenReturn(user);

        final ChangePasswordModel changePasswordModel = new ChangePasswordModel();
        changePasswordModel.setNewPasswordRepeated("repeatedNewPass");
        final ModelAndView mav = controller.changePassword(changePasswordModel, errors);

        assertEquals("/user/change-password", mav.getViewName());
        assertEquals(user, mav.getModel().get("currentUser"));
        assertEquals("Başarılı şekilde şifre değişikliği yapıldı!", mav.getModel().get("success"));
    }

}