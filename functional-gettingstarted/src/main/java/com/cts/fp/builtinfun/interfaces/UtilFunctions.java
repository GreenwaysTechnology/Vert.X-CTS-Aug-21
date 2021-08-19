package com.cts.fp.builtinfun.interfaces;

import java.util.function.*;

public class UtilFunctions {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Hello";
        System.out.println(supplier.get());
        Consumer<String> consumer = name -> System.out.println(name);
        consumer.accept("Subramanian");

        //return only int
        IntSupplier intSupplier = () -> 100;
        System.out.println(intSupplier.getAsInt());

        Predicate<Integer> predicate = number -> number > 10;
        System.out.println(predicate.test(100));
        System.out.println(predicate.test(1));

        //input,output
        Function<String, String> function = input -> input.toUpperCase();
        System.out.println(function.apply("Hello"));

        //Bi -two
        BiPredicate<Integer, Integer> biPredicate = (a, b) -> a > b;
        System.out.println(biPredicate.test(10,20));

    }
}
