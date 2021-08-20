package com.cts.rx.core;

import io.reactivex.rxjava3.core.Observable;

public class NumberSequenceStream {
    public static void main(String[] args) {
        //Pubublisher
//        Observable<Integer> stream = Observable.create(observer -> {
//            observer.onNext(1);
//            observer.onNext(2);
//            observer.onNext(3);
//            observer.onNext(4);
//            observer.onNext(5);
//            observer.onNext(6);
//            observer.onNext(7);
//            observer.onNext(8);
//            observer.onNext(9);
//            observer.onNext(10);
//            observer.onComplete();
//        });
        Observable<Integer> stream = Observable.just(1,2,3,4,5,6,7,8,9,10);
        //create Subscriber and add Listener
        stream.subscribe(data -> {
            System.out.println("Event is fired " + data);
        }, error -> {
            System.out.println("Error event is fired " + error);
        }, () -> {
            System.out.println("stream closed");
        });
    }
}
