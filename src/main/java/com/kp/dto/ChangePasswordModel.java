package com.kp.dto;

import com.kp.domain.User;
import com.kp.service.cache.CacheService;
import com.kp.service.security.AuthenticationService;
import com.kp.service.user.UserService;
import com.kp.validator.validate.KpInfoValidator;
import com.kp.validator.validate.Validateable;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by tcan on 25/02/17.
 */
public class ChangePasswordModel implements Validateable<ChangePasswordModel>, Serializable {

    private static final int MAX_RETRY_COUNT = 3;

    @NotBlank(message = "Eski şifre alanını boş bırakmayınız..")
    private String password;

    @Size(min = 6, max = 50, message = "Şifrenin en az 6, en çok 50 karakterden oluşmalıdır.")
    @NotBlank(message = "Yeni Şifre alanını boş bırakmayınız..")
    private String newPassword;

    @NotBlank(message = "Yeni Şifre Yenile alanını boş bırakmayınız..")
    private String newPasswordRepeated;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeated() {
        return newPasswordRepeated;
    }

    public void setNewPasswordRepeated(String newPasswordRepeated) {
        this.newPasswordRepeated = newPasswordRepeated;
    }

    @Override
    public KpInfoValidator<ChangePasswordModel> validator() {

        return new KpInfoValidator<ChangePasswordModel>() {

            @Autowired
            private UserService userService;

            @Autowired
            private AuthenticationService authenticationService;

            @Autowired
            private CacheService cacheService;

            @Override
            public void validate(ChangePasswordModel target, Errors errors) {
                final User currentUser = authenticationService.getCurrentUser();
                if (currentUser == null) {
                    errors.rejectValue("logout", "", "Giriş yapınız!");
                    return;
                }

                boolean isNotValid = !userService.isPasswordValid(password, currentUser.getPassword());
                if (isNotValid) {
                    final int cacheValue = getCacheValue(currentUser.getId());
                    if (cacheValue > MAX_RETRY_COUNT) {
                        errors.rejectValue("logout", "", "Giriş yapınız!");
                    } else {
                        final int retryCountDiff = MAX_RETRY_COUNT - cacheValue;
                        if (retryCountDiff <= 0) {
                            errors.rejectValue("password", "", "Şifrenizi 3 defadan fazla hatalı denediğiniz için, 2 saat hesabınız bloklandı!!");
                            return;
                        }
                        errors.rejectValue("password", "", String.format("Şifreniz hatalı! Son {0} defa deneme hakkınız kaldı!", retryCountDiff));
                    }
                    return;
                }
            }

            private int getCacheValue(long userId) {
                String cacheKey = getCacheKey(userId);
                final Object cacheValue = cacheService.get(cacheKey);
                int cacheValueAsInt = 0;
                if (cacheValue == null) {
                    cacheService.set(cacheKey, ++cacheValueAsInt);
                } else {
                    cacheValueAsInt = Integer.parseInt(String.valueOf(cacheValue));
                    ++cacheValueAsInt;
                    cacheService.set(cacheKey, cacheValueAsInt);
                }
                return cacheValueAsInt;
            }

            private String getCacheKey(long id) {
                return String.format("invalidPassword-{0}", id);
            }
        };
    }
}
