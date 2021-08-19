package com.cts.fp.inter.functional;


public class LambdaRulesMain {
    public static void main(String[] args) {
        Greeter greeter = null;
        greeter = () -> {
            System.out.println("Hello");
        };
        greeter.sayHello();
        greeter.doSomething();
        greeter.saySomething();
        Greeter.sayHai();
    }
}
