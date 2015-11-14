package com.kp.exception;

import com.google.common.base.Throwables;
import com.kp.exception.model.ErrorResponse;
import com.kp.exception.model.KpErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by turgaycan on 9/22/15.
 */
@EnableWebMvc
@ControllerAdvice
public class KPExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(KPExceptionHandlerControllerAdvice.class);

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoSuchElementException e) {
        e.printStackTrace();
//        ModelAndView mav = new ModelAndView("/error");
//        mav.addObject("error", Throwables.getRootCause(e));
        return "redirect:/error";
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseEntity<Object> handleControllerException(HttpServletRequest req, Throwable ex) {
        LOGGER.error("error stack trace {}, ", ex.fillInStackTrace().toString());
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setStackTrace();
//        if (ex instanceof ServiceException) {
//            errorResponse.setDetails(((ServiceException) ex).getDetails());
//        }
//        if (ex instanceof ServiceHttpException) {
//            return new ResponseEntity<Object>(errorResponse, ((ServiceHttpException) ex).getStatus());
//        } else {
        return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("path", request.getContextPath());
        responseBody.put("message", "The URL you have reached is not in service at this time (404).");
        return new ResponseEntity<Object>(responseBody, HttpStatus.NOT_FOUND);
    }
}
