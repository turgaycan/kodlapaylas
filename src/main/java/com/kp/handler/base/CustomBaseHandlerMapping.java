package com.kp.handler.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tcan on 14/10/15.
 */
public class CustomBaseHandlerMapping implements HandlerMapping, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBaseHandlerMapping.class);

    private Map<String, CustomBaseHandler> baseHandlerMappingMap = new HashMap<>();

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request)
            throws Exception {
        LOGGER.info("this -> {}", request.getPathInfo());
        CustomBaseHandler controller = baseHandlerMappingMap.get(request.getPathInfo());
        return controller == null ? null : new HandlerExecutionChain(controller);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx)
            throws BeansException {
        Map<String, CustomBaseHandler> controllers = ctx.getBeansOfType(CustomBaseHandler.class);
        for (CustomBaseHandler controller : controllers.values()) {
            LOGGER.info("paths.. -> {}", controller.getPath());
            baseHandlerMappingMap.put(controller.getPath(), controller);
        }

    }
}
