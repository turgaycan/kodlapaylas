package com.kp.validator;

import com.kp.dto.SubscriberModel;
import com.kp.exception.model.KPException;
import com.kp.service.SubscriberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by tcan on 09/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SubscriberModelValidatorTest {

    @InjectMocks
    private SubscriberModelValidator validator;

    @Mock
    private SubscriberService subscriberService;

    @Mock
    private Errors errors;

    @Test(expected = KPException.class)
    public void shouldThrowExceptionIfNotValidate() {
        final SubscriberModel subscriberModel = new SubscriberModel();

        when(subscriberService.isExists(any())).thenReturn(true);

        validator.validate(subscriberModel, errors);
    }

    @Test
    public void shouldValidate() {
        final SubscriberModel subscriberModel = new SubscriberModel();
        subscriberModel.setEmail("email");

        when(subscriberService.isExists("email")).thenReturn(false);

        validator.validate(subscriberModel, errors);
    }

}