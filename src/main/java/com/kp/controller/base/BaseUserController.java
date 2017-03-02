package com.kp.controller.base;

import com.kp.domain.User;
import com.kp.service.security.AuthenticationService;
import com.kp.util.KpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tcan on 04/02/17.
 */
public abstract class BaseUserController {

    @Autowired
    protected AuthenticationService authenticationService;

    protected ModelAndView index(String viewName){
        final boolean isNotAuthenticated = authenticationService.isKpNotAuthenticated();
        if (isNotAuthenticated) {
            return KpUtil.redirectToMAV("/giris-yap");
        }

        ModelAndView mav = new ModelAndView(viewName);
        final User currentUser = authenticationService.getCurrentUser();
        mav.addObject("currentUser", currentUser);

        return mav;
    }
}
