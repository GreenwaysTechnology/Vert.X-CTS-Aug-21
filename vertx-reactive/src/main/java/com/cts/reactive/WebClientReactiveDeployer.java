package com.cts.reactive;

import io.vertx.reactivex.core.Vertx;

public class WebClientReactiveDeployer {
  public static void main(String[] args) {
    Vertx vertx =Vertx.vertx();
    vertx.rxDeployVerticle(new WebClientServer()).subscribe();
    vertx.rxDeployVerticle(new WebClientReactive()).subscribe();

  }
}
