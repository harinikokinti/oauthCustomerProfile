package com.harini.oauth.service;

import com.harini.oauth.entity.CustomerIdentityEntity;
import com.harini.oauth.entity.TokenEntity;
import com.harini.oauth.exception.AuthenticationException;
import com.harini.oauth.model.CustomerIdentity;
import com.harini.oauth.model.Tokens;
import com.harini.oauth.repository.CustomerIdentityRepo;
import com.harini.oauth.repository.TokenRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private CustomerIdentityRepo customerIdentityRepo;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public TokenService() {
        privateKey = getPrivateKey();
        publicKey = getPublicKey();
    }

    public Tokens generateTokens(CustomerIdentity customerIdentity) throws AuthenticationException {

       CustomerIdentityEntity customerIdentityEntity =  customerIdentityRepo.findByUsername(customerIdentity.getUsername());
       System.out.println(customerIdentityEntity.getCustomerId());
        System.out.println(customerIdentityEntity.getUsername());
        System.out.println(customerIdentityEntity.getPassword());

        if (customerIdentity.getUsername().equals(customerIdentityEntity.getUsername()) &&
                customerIdentity.getPassword().equals(customerIdentityEntity.getPassword())) {
            String accessToken = generateAccessToken(customerIdentityEntity.getCustomerId());
            String refreshToken = UUID.randomUUID().toString(); // Universally Unique Identifier
            Tokens tokens = new Tokens(accessToken, refreshToken);
            addTokens(tokens);  //  also adding this generated tokens into the table token
            return tokens;
        }
        throw new AuthenticationException("Invalid User Credential");
    }

    private String generateAccessToken(String customerId) {
        System.out.println(customerId);
        return Jwts.builder()  // create token with
                .setSubject(String.valueOf(customerId))
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.systemDefault().getRules().getOffset(Instant.now()))))
                .setId(UUID.randomUUID().toString())
                .setIssuer("Orbitz")
                .signWith(privateKey, SignatureAlgorithm.RS256)  // generate access token with private key signed to it
                .compact();  // gets converted into string


    }

    public Tokens addTokens(Tokens tokens) {

        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setRefreshToken(tokens.getRefreshToken());
        tokenEntity.setAccessToken(tokens.getAccessToken());
        tokenRepo.save(tokenEntity);
        return tokens;

    }

    public Tokens refreshToken(String refreshToken) {
        TokenEntity tokenEntity = tokenRepo.findByRefreshToken(refreshToken);
        String previousAccessToken = tokenEntity.getAccessToken(); // get accessToken from table using refreshToken

        String customerId = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .setAllowedClockSkewSeconds(TimeUnit.DAYS.toSeconds(1))
                .build()
                .parseClaimsJws(previousAccessToken)  //  extract customerid using accessToken and the public key
                .getBody()
                .getSubject();
        System.out.println(customerId);

        String accessToken = generateAccessToken(customerId);   //  generate new access token
        Tokens tokens = new Tokens();
        tokens.setRefreshToken(UUID.randomUUID().toString());  // generate new refresh token
        tokens.setAccessToken(accessToken);
        addTokens(tokens); // add the newly generated token into the table token after refresh

        return tokens;
    }





    private PrivateKey getPrivateKey() {

        RSAPrivateKey privateKey = null;
        try {
            String privateKeyContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("private_key.pem").toURI())));
            privateKeyContent = privateKeyContent.replaceAll("\\n", "")
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
            privateKey = (RSAPrivateKey) kf.generatePrivate(keySpecPKCS8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return privateKey;
    }

    private PublicKey getPublicKey() {
        RSAPublicKey publicKey = null;
        try {
            String publicKeyContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("public_key.pem").toURI())));

            publicKeyContent = publicKeyContent.replaceAll("\\n", "")
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
            publicKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return publicKey;
    }


/*
    public List<Tokens> getTokens() {
        List<TokenEntity> tokenEntities = tokenRepo.findAll();
        List<Tokens> tokens = new ArrayList<>();

        for(TokenEntity t : tokenEntities) {
            Tokens token = new Tokens();
            token.setAccessToken(t.getAccessToken());
            token.setRefreshToken(t.getRefreshToken());
            tokens.add(token);
        }

        return tokens;

    }
    */





}


