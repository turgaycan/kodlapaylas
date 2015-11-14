package com.kp.handler.base;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by tcan on 14/10/15.
 */
public interface CustomBaseHandler {

    ModelAndView handle(Map<String, String> parameters, Map<String, Object> model);

    String getPath();
}
