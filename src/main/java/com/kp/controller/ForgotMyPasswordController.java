package com.kp.controller;

import com.kp.controller.util.KpControllerUtil;
import com.kp.dto.ForgotMyPasswordModel;
import com.kp.exception.model.KPException;
import com.kp.service.user.ForgotMyPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by tcan on 26/02/17.
 */
@Controller
public class ForgotMyPasswordController {

    @Autowired
    private ForgotMyPasswordService forgotMyPasswordService;

    @RequestMapping(value = "/sifremi-unuttum", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("/forgot-password");
    }

    @RequestMapping(value = "/sifremi-unuttum", method = RequestMethod.POST)
    public ModelAndView forgotMyPassword(@Valid @ModelAttribute("forgotMyPasswordModel") ForgotMyPasswordModel forgotMyPasswordModel, BindingResult bindingResult) {
        final ModelAndView mav = new ModelAndView("/forgot-password");
        mav.addObject("success", false);
        if (bindingResult.hasErrors()) {
            return KpControllerUtil.buildErrorMav(bindingResult, mav);
        }

        try {
            forgotMyPasswordService.execute(forgotMyPasswordModel.getUsername());
        } catch (KPException kpe) {
            mav.addObject("message", kpe.getMessage());
            return mav;
        }

        mav.addObject("success", true);
        mav.addObject("message", "Mailinize başarılı şekilde yeni şifreniz gönderildi!");
        return mav;
    }
}
