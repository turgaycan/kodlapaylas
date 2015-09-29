package com.kp.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;


/**
 * Created by turgaycan on 9/20/15.
 */
public class UserModel implements Serializable {

    private static final long serialVersionUID = -7683933371854136134L;
    @NotEmpty
    private String email = "";

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

    @NotEmpty
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + "'\n'" +
                "email='" + email.replaceFirst("@.+", "@***") + '\'' +
                ", password=***" + '\'' +
                ", passwordRepeated=***" + '\'' +
                '}';
    }
}
