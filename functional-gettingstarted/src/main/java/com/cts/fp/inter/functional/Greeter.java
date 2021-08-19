package com.cts.fp.inter.functional;

@FunctionalInterface
public interface Greeter {
    static void sayHai() {
        System.out.println("Static Hai method");
    }

    void sayHello();
    // void sayHai();
    //default methods
    default void saySomething() {
        System.out.println("Something !!!");
    }

    default void doSomething() {
        System.out.println("Do something!!");
    }
}
