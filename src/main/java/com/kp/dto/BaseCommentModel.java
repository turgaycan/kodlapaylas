package com.kp.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by tcan on 10/10/15.
 */
public class BaseCommentModel extends BaseModel {

    private static final long serialVersionUID = -4079627745153093107L;
    @NotBlank
    @Size(min = 2, max = 50, message = "Ad soyad alanı en az 2, en çok 50 karakter olmalıdır..")
    private String fullname;
    @Email
    @NotBlank(message = "Email alanını boş bırakmayınız..")
    private String email;
    @NotBlank
    @Size(min = 10, max = 1000, message = "Mesajınız en az 10, en çok 1000 karakter olmalıdır..")
    private String message;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
