package com.jurisitsm.test.web.controller;

import com.jurisitsm.test.exception.UsedCarAdException;
import com.jurisitsm.test.model.AppUser;
import com.jurisitsm.test.service.TokenService;
import com.jurisitsm.test.service.UserService;
import com.jurisitsm.test.web.dto.request.LoginRequest;
import com.jurisitsm.test.web.dto.request.RefreshTokenRequest;
import com.jurisitsm.test.web.dto.request.UserRequest;
import com.jurisitsm.test.web.dto.response.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager,
                          TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody UserRequest userRequest) throws UsedCarAdException {
        if (userService.existsByEmail(userRequest.getEmail())) {
            throw new UsedCarAdException("User with email: " + userRequest.getEmail() + " already exists.",
                    HttpStatus.BAD_REQUEST);
        }
        userService.createUser(userRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest,
                                               HttpServletRequest httpServletRequest) throws UsedCarAdException {
        tokenService.blacklistAccessToken(httpServletRequest);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword());
        var auth = authenticationManager.authenticate(authToken);
        var user = (AppUser) auth.getPrincipal();
        var accessToken = tokenService.generateAccessTokenFromEmail(user);
        var refreshToken = tokenService.createRefreshToken(user);
        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken.getId()));
    }

    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal AppUser user,
                       HttpServletRequest httpServletRequest) throws UsedCarAdException {
        tokenService.blacklistAccessToken(httpServletRequest);
        tokenService.deleteRefreshTokenByUser(user);
        userService.registerLogoutTime(user);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<TokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest,
                                             HttpServletRequest httpServletRequest) throws UsedCarAdException {
        tokenService.blacklistAccessToken(httpServletRequest);
        var refreshToken = tokenService.refreshToken(refreshTokenRequest.getRefreshToken());
        var accessToken = tokenService.generateAccessTokenFromEmail(refreshToken.getUser());
        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken.getId()));
    }

}
