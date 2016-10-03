package com.ht.home.bo.request;

/**
 * Created by tibi on 28/09/16.
 */
public class LoginRequest {

    public String username;
    public String password;

    public LoginRequest(String _username, String _password) {

        this.username = _username;
        this.password = _password;

    }
}
