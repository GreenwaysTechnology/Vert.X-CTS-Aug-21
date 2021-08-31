package com.cts.reactive;

import io.vertx.reactivex.core.Vertx;

public class EventBusDeployer {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.rxDeployVerticle(new ReactiveEventBus()).subscribe(System.out::println, System.out::println);
  }
}
