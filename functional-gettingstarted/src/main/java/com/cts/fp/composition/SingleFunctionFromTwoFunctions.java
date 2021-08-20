package com.cts.fp.composition;

import java.util.function.Predicate;

public class SingleFunctionFromTwoFunctions {
    public static void main(String[] args) {
        Predicate<String> startsWith = text -> text.startsWith("A");
        Predicate<String> endsWith = text -> text.endsWith("x");

        //coimbine with && Operator : which is not readable,looks like imperative
        //Predicate<String> startsWithAndEndsWith = text -> startsWith.test(text) && endsWith.test(text);
        Predicate<String> startsWithAndEndsWith = startsWith.and(endsWith);

        Predicate<String> composedOR = startsWith.or(startsWithAndEndsWith);

        boolean result = startsWithAndEndsWith.test("A hardworking person must relax");
        if (result) {
            System.out.println("Text Starts With A and Ends with e");
        } else {
            System.out.println("No Match found");

        }
        //or
        String matchFound = composedOR.test("Hello how are you") ? "Match found" : "Match Not Found";
        System.out.println(matchFound);


    }
}
