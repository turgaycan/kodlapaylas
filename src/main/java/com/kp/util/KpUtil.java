package com.kp.util;

import org.springframework.web.servlet.ModelAndView;

/**
 * Created by turgaycan on 9/26/15.
 */
public class KpUtil {


    private KpUtil() {
    }

    public static ModelAndView redirectToMAV(String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + url);
        return modelAndView;
    }

    public static ModelAndView redirectToMAV(ModelAndView modelAndView, String redirectUrl) {
        modelAndView.setViewName("redirect:/" + redirectUrl);
        return modelAndView;
    }

}
