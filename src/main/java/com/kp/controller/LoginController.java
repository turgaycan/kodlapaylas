package com.kp.controller;

import com.kp.service.security.AuthenticationService;
import com.kp.util.KpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by turgaycan on 9/22/15.
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/giris-yap", method = RequestMethod.GET)
    public ModelAndView loginIndex(Model model, String error) {
        LOGGER.debug("Getting login page, error={}", error);
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
            return new ModelAndView("login");

        }

        final boolean isAuthenticatedUser = authenticationService.isKpAuthenticated();
        if (isAuthenticatedUser) {
            return KpUtil.redirectToMAV("/user");
        }

        return new ModelAndView("login");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(Model model) {
        final UserDetails loggeduser = authenticationService.getLoggedUser();
        if (loggeduser != null) {
            model.addAttribute("message", "You have been logged out successfully.");
            authenticationService.logout();
            return "index";
        }
        return "error";
    }
}
