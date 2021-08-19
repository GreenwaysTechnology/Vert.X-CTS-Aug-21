package com.cts.fp.higherorderfun.complex;

public class AuthService {
    public void login(String username, String password, Resolve resolver, Reject rejector) {
        //biz
        if (username.equals("admin") && password.equals("admin")) {
            resolver.resolve("Login Success");
        } else {
            rejector.reject("Login Failed");
        }
    }
}
