package com.kp.controller;

import com.kp.domain.Subscriber;
import com.kp.dto.SubscriberModel;
import com.kp.exception.model.KPException;
import com.kp.service.SubscriberService;
import com.kp.validator.SubscriberModelValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by turgaycan on 9/28/15.
 */
@RestController
public class SubscriberController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberController.class);

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private SubscriberModelValidator subscriberModelValidator;


    @RequestMapping(value = "/abone/ol", method = RequestMethod.POST)
    @ResponseBody
    public String addSubscriber(@ModelAttribute("subscriberModel") @Valid SubscriberModel subscriberModel, BindingResult bindingResult) {
        LOGGER.info(subscriberModel.getEmail());
        try {
            subscriberModelValidator.validate(subscriberModel, bindingResult);
        } catch (KPException kpe) {
            return kpe.getMessage();
        }
        if (bindingResult.hasErrors()) {
            return bindingResult.toString();
        }
        LOGGER.info("successfull");
        return subscriberService.addSubscriber(new Subscriber(subscriberModel.getEmail())).toString();
    }
}
