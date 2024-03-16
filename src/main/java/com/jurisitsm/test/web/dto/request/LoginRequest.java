package com.jurisitsm.test.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "The email of the user must not be blank.")
    @Email(message = "Invalid email format.")
    private String email;
    @NotBlank(message = "The password of the user must not be blank.")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
