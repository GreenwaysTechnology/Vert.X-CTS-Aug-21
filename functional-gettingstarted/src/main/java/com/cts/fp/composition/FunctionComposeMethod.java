package com.cts.fp.composition;

import java.util.function.Function;

public class FunctionComposeMethod {
    public static void main(String[] args) {
        Function<Integer, Integer> multiply = value -> value * 2;
        Function<Integer, Integer> add = value -> value + 3;

        //compose execute the methods right to left
        Function<Integer, Integer> addThenMultiply = multiply.compose(add);

        //andThen execute the methods left to right
        Function<Integer, Integer> multiplyAndAdd = multiply.andThen(add);

        System.out.println(addThenMultiply.apply(10));
        System.out.println(multiplyAndAdd.apply(10));

    }
}
