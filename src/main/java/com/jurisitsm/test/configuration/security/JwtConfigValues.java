package com.jurisitsm.test.configuration.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app.jwt")
public class JwtConfigValues {
    private String secret;
    private TokenConfiguration refreshToken;
    private TokenConfiguration accessToken;
    public static class TokenConfiguration {
        private int expiresMin;

        public int getExpiresMin() {
            return expiresMin;
        }

        public void setExpiresMin(int expiresMin) {
            this.expiresMin = expiresMin;
        }
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public TokenConfiguration getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(TokenConfiguration refreshToken) {
        this.refreshToken = refreshToken;
    }

    public TokenConfiguration getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(TokenConfiguration accessToken) {
        this.accessToken = accessToken;
    }
}
