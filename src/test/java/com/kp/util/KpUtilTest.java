package com.kp.util;


import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

/**
 * Created by tcan on 09/03/17.
 */
public class KpUtilTest {

    @Test
    public void shouldRedirectToMavByUrl() {
        final ModelAndView redirectMav = KpUtil.redirectToMAV("/error");

        assertEquals("redirect:/error", redirectMav.getViewName());
    }

    @Test
    public void shouldRedirectToMAVWithMavAndRedirectUrl() {
        final ModelAndView redirectToMAV = KpUtil.redirectToMAV(new ModelAndView(), "error");

        assertEquals("redirect:/error", redirectToMAV.getViewName());
    }
}