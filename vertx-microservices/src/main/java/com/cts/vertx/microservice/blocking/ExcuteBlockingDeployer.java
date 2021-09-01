package com.cts.vertx.microservice.blocking;

import io.vertx.core.Vertx;

public class ExcuteBlockingDeployer {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new ExecuteBlockingVerticle());
  }
}
