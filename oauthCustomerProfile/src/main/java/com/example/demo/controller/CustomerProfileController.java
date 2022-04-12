package com.example.demo.controller;

import com.example.demo.model.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepo;
import com.example.demo.service.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*

Here we validate the JWT token which comes in  a header (key,value) key = header name, value= token name(access token)
The JWT token is validated using public key and customerId is extracted from the token.
The JWT token's expiry date is also validated by  io.jsonwebtoken.security internally.
Using customerID, the customer profile details are returned from the database.

Note: While passing a request in postman, pass the token as value in header tab, token name in key tab

 */


@RestController
public class CustomerProfileController {

    @Autowired
    CustomerProfileService customerProfileService;

    @RequestMapping("/customer")
    CustomerProfile getCustomerProfile(@RequestHeader("token")  String token) {
        CustomerProfile customerProfile =  customerProfileService.getCustomerProfile(token);
        return customerProfile;
    }

}