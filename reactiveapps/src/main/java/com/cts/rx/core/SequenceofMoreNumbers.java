package com.cts.rx.core;

import io.reactivex.rxjava3.core.Observable;

public class SequenceofMoreNumbers {
    public static void main(String[] args) {
        Observable<Integer> stream = Observable.range(1, 100);
        stream.subscribe(data -> {
            System.out.println("Event is fired " + data);
        }, error -> {
            System.out.println("Error event is fired " + error);
        }, () -> {
            System.out.println("stream closed");
        });

    }
}
