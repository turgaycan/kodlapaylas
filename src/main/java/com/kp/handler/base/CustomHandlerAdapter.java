package com.kp.handler.base;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tcan on 14/10/15.
 */
public class CustomHandlerAdapter implements HandlerAdapter {

    @Override
    public ModelAndView handle(HttpServletRequest request,
                               HttpServletResponse response, Object handler) throws Exception {
        CustomBaseHandler controller = (CustomBaseHandler) handler;
        Map<String, String> parameters = new HashMap();
        for (Object key : request.getParameterMap().keySet()) {
            parameters.put(key.toString(), extractParameter(request.getParameterValues(key.toString())));
        }
        Map<String, Object> model = new HashMap();
        return controller.handle(parameters, model);
//        return new ModelAndView(viewName, model);
    }

    @Override
    public boolean supports(Object handler) {
        return handler instanceof CustomBaseHandler;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1;
    }

    private String extractParameter(String[] values) {
        if (values == null || values.length == 0) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        } else if (values.length == 1) {
            return values[0];
        } else {
            return StringUtils.arrayToCommaDelimitedString(values);
        }
    }
}
