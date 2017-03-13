package com.kp.controller.util;


import org.junit.Test;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by tcan on 11/03/17.
 */
public class KpControllerUtilTest {

    @Test
    public void shouldBuildErrorMav(){
        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");
        errors.reject("article", "errorMessage");

        final ModelAndView mav = KpControllerUtil.buildErrorMav(errors, new ModelAndView());

        final String errorMessage = (String) mav.getModel().get("errors");
        assertEquals("errorMessage<br />", errorMessage);
    }

}