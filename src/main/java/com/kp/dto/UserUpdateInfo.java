package com.kp.dto;

import com.kp.domain.User;
import com.kp.service.user.UserService;
import com.kp.validator.validate.KpInfoValidator;
import com.kp.validator.validate.Validateable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import java.io.Serializable;

/**
 * Created by tcan on 05/02/17.
 */
public class UserUpdateInfo extends User implements Validateable<UserUpdateInfo>, Serializable {

    @Override
    public KpInfoValidator<UserUpdateInfo> validator() {
        return new KpInfoValidator<UserUpdateInfo>() {
            @Autowired
            private UserService userService;

            @Override
            public void validate(UserUpdateInfo target, Errors errors) {

                if (target == null) {
                    errors.rejectValue("global-error", "", "Kullanıcı değerleri boş olamaz!");
                    return;
                }

                if (StringUtils.isBlank(target.getUsername())) {
                    errors.rejectValue("username", "", "Kullanıcı ad alanını boş bırakmayınız!");
                    return;
                }

                final User user = userService.getUserByUsername(target.getUsername());

                if (user != null && !target.getUsername().equals(user.getUsername())) {
                    errors.rejectValue("username", "", "Kullanıcı adı farklı bir kullanıcı tarafından kullanılmaktadır!");
                }
            }
        };
    }

    public User decorateUser(User persisted) {
        persisted.setUserStatus(getUserStatus());
        persisted.setWebsite(getWebsite());
        persisted.setRole(getRole());
        persisted.setFullname(getFullname());

        return persisted;
    }
}
