package com.cts.reactive;

import io.vertx.reactivex.core.Vertx;

public class DatabseDeployer {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.rxDeployVerticle(new ReactiveDatabase()).subscribe(result -> {
        System.out.println(result);
      }, err -> {
        System.out.println(err);
      }
    );
  }
}
