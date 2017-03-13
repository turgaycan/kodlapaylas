package com.kp.dto;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

/**
 * Created by tcan on 04/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordModelTest {


    @Test
    public void should(){
        ChangePasswordModel model = new ChangePasswordModel();

        final MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), "");

        model.validator().validate(model, errors);
    }
}