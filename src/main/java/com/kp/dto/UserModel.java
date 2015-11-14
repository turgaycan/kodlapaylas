package com.kp.dto;

import com.kp.repository.UserRepository;
import com.kp.validator.validate.KpInfoValidator;
import com.kp.validator.validate.Validateable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * Created by turgaycan on 9/20/15.
 */
public class UserModel implements Validateable<UserModel>, Serializable {

    private static final long serialVersionUID = -7683933371854136134L;

    @Email(message = "Email formatı hatalı!")
    @NotBlank(message = "Email adresini boş bırakmayınız..")
    private String email;

    @Size(min = 6, max = 50, message = "Şifrenin en az 6, en çok 50 karakterden oluşmalıdır.")
    @NotBlank(message = "Şifre alanını boş bırakmayınız..")
    private String password;

    @NotBlank(message = "Şifre Yenile alanını boş bırakmayınız..")
    private String passwordRepeated;

    @NotBlank(message = "Ad Soyad alanını boş bırakmayınız..")
    private String fullname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + fullname + "'\n'" +
                "email='" + email.replaceFirst("@.+", "@***") + '\'' +
                ", password=***" + '\'' +
                ", passwordRepeated=***" + '\'' +
                '}';
    }

    @Override
    public KpInfoValidator<UserModel> validator() {
        return new KpInfoValidator<UserModel>() {
            @Autowired
            UserRepository userRepository;

            @Override
            public void validate(UserModel target, Errors errors) {
                if (!target.getPassword().equals(target.getPasswordRepeated())) {
                    errors.rejectValue("password", "", "Password must match password confirmation");
                }

                if (userRepository.findOneByEmail(target.getEmail()) != null) {
                    errors.rejectValue("email", "", "Email address is already taken");
                }
            }

        };
    }
}
