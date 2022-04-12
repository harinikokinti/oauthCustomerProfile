package com.harini.oauth.model;

public class CustomerIdentity {

    String customerId;
    String username;
    String password;

    public void setCustomer_id(String customer_id)  {
     this.customerId = customer_id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
