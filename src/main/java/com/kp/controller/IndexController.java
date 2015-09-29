package com.kp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by turgaycan on 9/20/15.
 */
@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = {"/index", "/", ""})
    public ModelAndView index() {
        LOGGER.info("Getting home page");
        return new ModelAndView("index");
    }
}
