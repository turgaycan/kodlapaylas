package com.kp.dto;

import com.kp.service.user.UserService;
import com.kp.validator.validate.KpInfoValidator;
import com.kp.validator.validate.Validateable;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import java.io.Serializable;

/**
 * Created by tcan on 26/02/17.
 */
public class ForgotMyPasswordModel implements Validateable<ForgotMyPasswordModel>, Serializable {

    @NotBlank(message = "Kullanıcı Ad alanını boş bırakmayınız..")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public KpInfoValidator<ForgotMyPasswordModel> validator() {
        return new KpInfoValidator<ForgotMyPasswordModel>() {

            @Autowired
            private UserService userService;

            @Override
            public void validate(ForgotMyPasswordModel target, Errors errors) {
                final String username = target.getUsername();
                if (StringUtils.isNotBlank(username)) {
                    errors.rejectValue("username", "", "Kullanıcı Ad alanını boş bırakmayınız!");
                    return;
                }

                if (userService.getUserByUsernameOrEmail(username) == null) {
                    errors.rejectValue("username", "", String.format("Kullanıcı Ad/E-Posta : {0}, ait kayıtlı kullanıcı bulunamadı!", username));
                    return;
                }
            }
        };
    }
}
