package com.jurisitsm.test.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {

    @NotBlank(message = "The name of the user must not be blank.")
    @Size(max = 50, message = "The name of the user cannot be longer than 50 characters.")
    private String name;
    @NotBlank(message = "The email of the user must not be blank.")
    @Email(message = "Invalid email format.")
    private String email;
    @NotBlank(message = "The password of the user must not be blank.")
    @Size(min = 8, message = "Password should be at least 8 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "Password must contain at least one lowercase letter, one uppercase letter and a number")
    private String password;

    public UserRequest() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
