package com.harini.oauth.controller;
/*

ORBITZ.com is a online booking system
Here I worked in User profile, where the user details are stored
Worked in User authentication and Authorization
Authorization is done by setting the tokens and validating them to provide the user state until he/she gets logged out.

Generate tokens:
Access token , Refresh tokens are generated for every user request when logged in (after authentication) and sent back in cookies to the
requested user browser by digitally signing the token (JWT token).

Refresh the connection
If the access token expired, in order to refresh or continue the connection between the user and server, the tokens are regenerated
using the refresh token (it has more expiration time) by validating the customer id stored in the access token

 */

import com.harini.oauth.model.LoginRequest;
import com.harini.oauth.model.RefreshRequest;
import com.harini.oauth.model.Tokens;
import com.harini.oauth.repository.TokenRepo;
import com.harini.oauth.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenRepo tokenRepo;

    @Value("${token-cookie.expiration.access-token}")
    private Long accessTokenCookieExpirationTime;

    @Value("${token-cookie.expiration.refresh-token}")
    private Long refreshTokenCookieExpirationTime;

    @PostMapping("/tokens")
    public ResponseEntity<Tokens> tokens(@RequestBody LoginRequest loginRequest) {

        Tokens tokens = tokenService.generateTokens(loginRequest);
        String[] cookies = createCookies(tokens);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookies)
                .body(tokens);

    }

    @PostMapping("/refresh")
    public ResponseEntity<Tokens> tokens(@RequestBody RefreshRequest refreshRequest) {
        Tokens tokens = tokenService.refreshToken(refreshRequest.getRefreshToken());

        String[] cookies = createCookies(tokens);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookies)
                .body(tokens);
    }


    public String[] createCookies(Tokens tokens) {

        String accessToken = tokens.getAccessToken();
        String refreshToken = tokens.getRefreshToken();

        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(accessTokenCookieExpirationTime)  //  30 minutes in seconds
                .domain("localhost")
                .build();


        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenCookieExpirationTime)  //  1 day in seconds
                .domain("localhost")
                .build();


        return new String[]{accessTokenCookie.toString(), refreshTokenCookie.toString()};

        /*
        String[] cookies = new String[2];
        cookies[0] = accessTokenCookie.toString();
        cookies[1] = refreshTokenCookie.toString();
        return cookies;
        */

    }
}
