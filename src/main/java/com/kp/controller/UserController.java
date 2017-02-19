package com.kp.controller;

import com.kp.controller.base.BaseUserController;
import com.kp.controller.util.KpControllerUtil;
import com.kp.domain.User;
import com.kp.dto.UserModel;
import com.kp.dto.UserUpdateInfo;
import com.kp.service.auth.KpAuthenticationProvider;
import com.kp.service.security.AuthenticationService;
import com.kp.service.user.UserService;
import com.kp.util.KpUtil;
import com.kp.validator.UserModelValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by turgaycan on 9/22/15.
 */
@Controller
public class UserController extends BaseUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelValidator userModelValidator;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private KpAuthenticationProvider kpAuthenticationProvider;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userModelValidator);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @RequestMapping(value = {"/user", "/user/index"})
    public ModelAndView index() {
        return super.index("/user/index");
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/info-update")
    public ModelAndView updateUserInfo(@Valid @ModelAttribute("userUpdateInfo") UserUpdateInfo userUpdateInfo, BindingResult bindingResult) {
        final ModelAndView modelAndView = super.index("/user/index");
        final User currentUser = (User) modelAndView.getModel().get("currentUser");
        if (bindingResult.hasErrors()) {
            return KpControllerUtil.buildErrorMav(bindingResult, modelAndView);
        }
        currentUser.setFullname(userUpdateInfo.getFullname());
        currentUser.setWebsite(userUpdateInfo.getWebsite());
        final User mergedUser = userService.merge(currentUser);
        modelAndView.addObject("currentUser", mergedUser);
        modelAndView.addObject("success", "Başarılı şekilde güncellendi!");
        return KpUtil.redirectToMAV("/user");
    }

    @PreAuthorize("@currentUserService.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        LOGGER.debug("Getting user page for user={}", id);
        return new ModelAndView("user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @RequestMapping(value = "/kayit-ol", method = RequestMethod.GET)
    public ModelAndView newUser() {
        final boolean isAuthenticatedUser = authenticationService.isKpAuthenticated();
        if (isAuthenticatedUser) {
            return KpUtil.redirectToMAV("/user");
        }
        return new ModelAndView("/new-user");
    }

    @RequestMapping(value = "/kayit-ol", method = RequestMethod.POST)
    public ModelAndView newUser(@Valid @ModelAttribute("userModel") UserModel userModel, BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", userModel, bindingResult);

        ModelAndView mav = new ModelAndView("new-user");
        if (bindingResult.hasErrors()) {
            return KpControllerUtil.buildErrorMav(bindingResult, mav);
        }
        User persistedUser;
        try {
            persistedUser = userService.create(userModel);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return KpUtil.redirectToMAV("/hata");
        }
        LOGGER.info("Successfully registered");
        redirectAttributes.addFlashAttribute("success", "Başarılı şekilde üyeliğiniz oluştu!");
        kpAuthenticationProvider.login(persistedUser, persistedUser.getEmail(), userModel.getPasswordRepeated());
        return KpUtil.redirectToMAV("/index");
    }

}
