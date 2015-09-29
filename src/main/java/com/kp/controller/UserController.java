package com.kp.controller;

import com.kp.dto.UserModel;
import com.kp.service.user.UserService;
import com.kp.validator.UserModelValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by turgaycan on 9/22/15.
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelValidator userModelValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userModelValidator);
    }

    @PreAuthorize("@currentUserService.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        LOGGER.debug("Getting user page for user={}", id);
        return new ModelAndView("user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("Getting user create form");
        return new ModelAndView("user_create", "form", new UserModel());
    }

    @RequestMapping(value = "/yeni-uye", method = RequestMethod.GET)
    public ModelAndView newUser() {
        return new ModelAndView("yeni-uye");
    }

    @RequestMapping(value = "/uye/kayit-ol", method = RequestMethod.POST)
    public ModelAndView newUser(@Valid @ModelAttribute("form") UserModel form, BindingResult bindingResult) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);

        ModelAndView mav = new ModelAndView("/yeni-uye");
        if (bindingResult.hasErrors()) {
            // failed validation
            mav.addAllObjects(bindingResult.getModel());
            return mav;
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return mav;
        }
        LOGGER.info("Successfully registered");
        // ok, redirect
        return new ModelAndView("/profil");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/users")
    public ModelAndView getUsersPage() {
        LOGGER.debug("Getting users page");
        return new ModelAndView("users", "users", userService.getAllUsers());
    }
}
