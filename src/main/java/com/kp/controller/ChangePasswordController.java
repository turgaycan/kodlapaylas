package com.kp.controller;

import com.kp.controller.base.BaseUserController;
import com.kp.controller.util.KpControllerUtil;
import com.kp.domain.User;
import com.kp.dto.ChangePasswordModel;
import com.kp.service.user.UserService;
import com.kp.util.KpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by tcan on 02/03/17.
 */
@Controller
public class ChangePasswordController extends BaseUserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @RequestMapping(value = {"/sifre-degistir"}, method = RequestMethod.GET)
    public ModelAndView changePassword() {
        return super.index("/user/change-password");
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @RequestMapping(value = "/sifre-degistir", method = RequestMethod.POST)
    public ModelAndView changePassword(@Valid @ModelAttribute("changePasswordModel") ChangePasswordModel changePasswordModel, BindingResult bindingResult) {
        final ModelAndView modelAndView = super.index("/user/change-password");
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                for (String code : objectError.getCodes()) {
                    if (code.contains("logout")) {
                        authenticationService.logout();
                        return KpUtil.redirectToMAV("/login");
                    }
                }
            }
            return KpControllerUtil.buildErrorMav(bindingResult, modelAndView);
        }
        final User currentUser = (User) modelAndView.getModel().get("currentUser");
        currentUser.setPassword(changePasswordModel.getNewPasswordRepeated());
        final User mergedUser = userService.mergeWithPassword(currentUser);
        modelAndView.addObject("currentUser", mergedUser);
        modelAndView.addObject("success", "Başarılı şekilde şifre değişikliği yapıldı!");
        return modelAndView;
    }
}
