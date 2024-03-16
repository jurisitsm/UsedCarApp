package com.jurisitsm.test.web.dto.response;

public class LoginResponse {
    private String accesToken;
    private String refreshToken;

    public LoginResponse(String accesToken, String refreshToken) {
        this.accesToken = accesToken;
        this.refreshToken = refreshToken;
    }
}
