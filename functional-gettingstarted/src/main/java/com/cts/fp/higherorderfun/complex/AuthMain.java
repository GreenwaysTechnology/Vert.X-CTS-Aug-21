package com.cts.fp.higherorderfun.complex;

public class AuthMain {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        authService.login("admin", "admin", response -> {
            System.out.println(response);
        }, error -> {
            System.out.println(error);
        });
        authService.login("foo", "bar", response -> {
            System.out.println(response);
        }, error -> {
            System.out.println(error);
        });
        authService.login("admin", "admin", response -> System.out.println(response)
                , error -> System.out.println(error));
    }
}
