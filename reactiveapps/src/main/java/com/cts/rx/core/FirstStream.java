package com.cts.rx.core;

import io.reactivex.rxjava3.core.Observable;

public class FirstStream {
    public static void main(String[] args) {
        //Pubublisher
        Observable<String> stream = Observable.create(observer -> {
            //push data,error,complete
            observer.onNext("Hello"); //emit data event ---listener will be notified
            observer.onNext("Hai");
           // observer.onError(new RuntimeException("oops!!"));
            observer.onNext("Welcome");
            observer.onComplete();
        });


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
