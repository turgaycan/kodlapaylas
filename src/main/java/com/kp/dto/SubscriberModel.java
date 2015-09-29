package com.kp.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by turgaycan on 9/28/15.
 */
public class SubscriberModel implements Serializable {
    private static final long serialVersionUID = -4153747064409716344L;

    @NotEmpty(message = "Email alan\u0131n\u0131 bo\u015F b\u0131rakmay\u0131n\u0131z..")
    @Size(min = 8, max = 50, message = "Email min 8, max 50 karakter olmal\u0131d\u0131r..")
    @Email(message = "Email address not valid")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
