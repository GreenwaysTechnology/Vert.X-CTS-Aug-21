package com.cts.fp.returns;

public class ReturnsMain {
    public static void main(String[] args) {
        Adder adder = null;
        adder = (a, b) -> {
            return a + b;
        };
        System.out.println(adder.add(10, 10));

        //
        adder = (a, b) -> a + b;
        System.out.println(adder.add(10, 10));
    }
}
