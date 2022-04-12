package com.harini.oauth.controller;
/*

ORBITZ.com is a global leading travel company that uses innovative technology to enable travelers to search, plan and book
range of travel products and services including airline tickets, hotels, car rentals, cruises, and vacation packages.

Here I worked in User profile, where the user details are stored
Worked in User authentication and Authorization
Authentication: Check the login id and password . If matches, then the user is authenticated.
Authorization is done by setting the tokens and validating them to provide the user state until he/she gets logged out.

Generate tokens:
Access token , Refresh tokens are generated for every user request when logged in (after authentication) and sent back in cookies to the
requested user browser by digitally signing the token (JWT token) using private key.


Refresh the connection
If the access token expired, in order to refresh or continue the connection between the user and server, the tokens are regenerated
using the refresh token (it has more expiration time) by validating the customer id stored in the access token

Token Validation:
Token validation is carried in the micro service oauth-customerProfile .
The token in a cookie is sent in the headers by the GET API from this oauth service and the koen is validated in oauth-customerProfile  service.
The customer id is extracted from the token using public key and the respective customer profile details are returned.

Traditional Apporach:
The user state is maintained using Session management. For every user login, a unique session id is stored centrally and maintained centrally.
Since it is centralised, single point of failure happens for entire application.

Note: Locking with private key and digitally signing using RS256 algorithm.
Unlocking with public key


 */

import com.harini.oauth.model.CustomerIdentity;
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
    public ResponseEntity<Tokens> tokens(@RequestBody CustomerIdentity customerIdentity) {
        /*
        Send username and password, if authentication success, generate the tokens(access token, refresh token)
        store these tokens in the cookie , and add this cookie with tokens in response header.
         */
        Tokens tokens = tokenService.generateTokens(customerIdentity);
        String[] cookies = createCookies(tokens);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookies)
                .body(tokens);

    }

    @PostMapping("/refresh")
    public ResponseEntity<Tokens> tokens(@RequestBody RefreshRequest refreshRequest) {
        /*
        To maintain the state for subsequent user requests, refresh service regenrates the tokens
        the refresh token has more expiry time, so using refresh token, get the access token from the table which was stored after authentication
        Using access token, extract the customer id using public key and regenerate the tokens
        Add the tokens into the cookie and add this cookie in the response header
         */

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
