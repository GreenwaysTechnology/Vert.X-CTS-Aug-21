package com.cts.fp.builtinfun.interfaces;


@FunctionalInterface
interface Greeter {
    Hello sayHello(String message);
}

class Hello {
    private String message;

    public Hello() {

    }

    public Hello(String message) {
        this.message = message;
        System.out.println(this.message);
    }

    public String getMessage() {
        return message;
    }
}

public class ConstructorReferences {
    public static void main(String[] args) {
        Greeter hello = Hello::new;
        hello.sayHello("Hello");
        //how to access getMessage
        // System.out.println();
    }
}
