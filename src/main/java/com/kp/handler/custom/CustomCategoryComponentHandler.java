package com.kp.handler.custom;

import com.kp.handler.base.CustomBaseHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by turgaycan on 9/27/15.
 */
@Component
public class CustomCategoryComponentHandler implements CustomBaseHandler {


    @Override
    public ModelAndView handle(Map<String, String> parameters, Map<String, Object> model) {
        return null;
    }

    @Override
    public String getPath() {
        return "/kategoriler";
    }
}
