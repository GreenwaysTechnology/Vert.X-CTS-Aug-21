package com.cts.fp.methodreferences;

@FunctionalInterface
interface UpperCase {
    String convertToUpper(String message);
}

public class MethodReferences {
    public static void main(String[] args) {
        Welcome welcome = null;
        welcome = name -> System.out.println(name);
        welcome.sayWelcome("Subramanian");
        //method reference
        welcome = System.out::println;
        welcome.sayWelcome("Subramanian");
        ////////////////////////////////////////////////////////////////////////
        UpperCase upperCase = null;
        upperCase = message -> message.toUpperCase();
        System.out.println(upperCase.convertToUpper("subramanian"));
        upperCase = String::toUpperCase;
        System.out.println(upperCase.convertToUpper("subramanian"));


    }
}
