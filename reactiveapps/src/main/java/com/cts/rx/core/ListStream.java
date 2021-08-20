package com.cts.rx.core;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.List;

public class ListStream {
    public static void main(String[] args) {
        Integer[] items = {1, 2, 3, 4, 5, 6};
        List<Integer> list = Arrays.asList(items);
        Observable<Integer> stream = Observable.fromIterable(list);
        stream.subscribe(System.out::println, System.out::println, () -> System.out.println("done"));
    }
}