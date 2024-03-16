package com.jurisitsm.test.service;

import com.jurisitsm.test.configuration.security.JwtConfigValues;
import com.jurisitsm.test.exception.UsedCarAdException;
import com.jurisitsm.test.model.AppUser;
import com.jurisitsm.test.model.RefreshToken;
import com.jurisitsm.test.repository.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {
    private static final String TOKEN_PREFIX = "Bearer ";
    private final JwtConfigValues jwtConfigValues;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public TokenService(JwtConfigValues jwtConfigValues, RefreshTokenRepository refreshTokenRepository) {
        this.jwtConfigValues = jwtConfigValues;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String generateAccessTokenFromEmail(String email) {
        if (!StringUtils.hasText(email)) {
            return null;
        }

        var key = getSecretKey();
        LocalDateTime issueDate = LocalDateTime.now();
        var expirationDate = issueDate.plusMinutes(jwtConfigValues.getAccessToken().getExpiresMin());

        return Jwts.builder()
                .subject(email)
                .issuedAt(Date.from(issueDate.atZone(ZoneId.systemDefault()).toInstant()))
                .expiration(Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key)
                .compact();
    }

    public RefreshToken createRefreshToken(AppUser user) throws UsedCarAdException {
        if (user == null) {
            throw new UsedCarAdException("Missing user on login.", HttpStatus.UNAUTHORIZED);
        }

        refreshTokenRepository.findByUser(user)
                .ifPresent(refreshTokenRepository::delete);

        LocalDateTime refreshTokenExpiryDate = LocalDateTime.now()
                .plusMinutes(jwtConfigValues.getRefreshToken().getExpiresMin());

        RefreshToken refreshToken = new RefreshToken(refreshTokenExpiryDate, user);

        return refreshTokenRepository.save(refreshToken);
    }

    public void deleteRefreshTokenByUser(AppUser user) throws UsedCarAdException {
        if (user == null) {
            throw new UsedCarAdException("Missing user on logout.", HttpStatus.UNAUTHORIZED);
        }

        refreshTokenRepository.deleteByUser(user);
    }

    public boolean validateAccessToken(String token) {
        var key = getSecretKey();
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAccessTokenFromRequest(HttpServletRequest request) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith(TOKEN_PREFIX)) {
            return null;
        }

        var token = authHeader.replace(TOKEN_PREFIX, "");
        if (!StringUtils.hasText(token)) {
            return null;
        }

        return token;
    }

    public String getEmailFromAccessToken(String accessToken) {
        if (!StringUtils.hasText(accessToken)) {
            return null;
        }

        var key = getSecretKey();
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload()
                .getSubject();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfigValues.getSecret()));
    }
}
