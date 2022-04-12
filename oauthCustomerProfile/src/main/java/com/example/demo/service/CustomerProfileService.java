package com.example.demo.service;

import com.example.demo.entity.CustomerProfileEntity;
import com.example.demo.model.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerProfileService {
CustomerProfileEntity customerProfileEntity;
@Autowired
CustomerProfileRepo customerProfileRepo;
private PublicKey publicKey;
CustomerProfileService() {
    publicKey = getPublicKey();
}

    public CustomerProfile getCustomerProfile(String token) {

        String customerId =  Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .setAllowedClockSkewSeconds(TimeUnit.DAYS.toSeconds(1))
                .build()
                .parseClaimsJws(token)  //  extract customerid using accessToken and the public key
                .getBody()
                .getSubject();
        System.out.println(customerId);

          CustomerProfileEntity customerProfileEntity =
                customerProfileRepo.findByCustomerId(customerId);

    CustomerProfile customerProfile = new CustomerProfile();
    customerProfile.setCustomerId(customerProfileEntity.getCustomerId());
    customerProfile.setFirstName(customerProfileEntity.getFirstName());
    customerProfile.setLastName(customerProfileEntity.getLastName());
    customerProfile.setEmail(customerProfileEntity.getEmail());

    System.out.println(customerProfile.getCustomerId() + customerProfile.getFirstName());

    return customerProfile;
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


}
