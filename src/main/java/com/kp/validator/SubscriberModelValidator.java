package com.kp.validator;

import com.kp.dto.SubscriberModel;
import com.kp.exception.model.KPException;
import com.kp.service.SubscriberService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by turgaycan on 9/28/15.
 */
@Component
public class SubscriberModelValidator implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberModelValidator.class);

    @Autowired
    private SubscriberService subscriberService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SubscriberModel.class);
    }

    @Override
    public void validate(Object target, Errors errors) throws KPException {
        SubscriberModel model = (SubscriberModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty", " Email alanını boş bırakmayınız..");
        String email = model.getEmail();
        if (StringUtils.isNotBlank(email) && subscriberService.isExists(email)) {
            throw new KPException("Email adresi daha önce kaydedilmiştir..");
        }
    }
}
