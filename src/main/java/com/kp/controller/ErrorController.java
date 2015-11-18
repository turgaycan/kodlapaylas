package com.kp.controller;

import com.kp.dto.enumurations.KpErrors;
import com.kp.exception.model.KpErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by turgaycan on 9/23/15.
 */
@Controller
public class ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error() {
        LOGGER.info("hasnicktir bea..");
        ModelAndView mav = new ModelAndView("/error");
        mav.addObject("error", new KpErrorResponse(KpErrors.NOT_FOUND_SEARCH));
        return mav;
    }

}
