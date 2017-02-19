package com.kp.controller.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by tcan on 09/02/17.
 */
public final class KpControllerUtil {

    public static ModelAndView buildErrorMav(BindingResult bindingResult, ModelAndView mav) {
        final List<ObjectError> allErrors = bindingResult.getAllErrors();
        final StringBuilder errorsAsString = new StringBuilder();
        allErrors.forEach(objectError -> errorsAsString.append(objectError.getDefaultMessage()).append("<br />"));
        mav.addObject("errors", errorsAsString.toString());
        return mav;
    }
}
