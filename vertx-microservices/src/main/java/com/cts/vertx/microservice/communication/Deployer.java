package com.cts.vertx.microservice.communication;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class Deployer extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(MessageServiceConsumer.class.getName(), ar -> {
      if (ar.succeeded()) {
        System.out.println(MessageServiceConsumer.class.getName() + " " + ar.result());
      } else {
        System.out.println(ar.cause());
      }
    });
    vertx.deployVerticle(MessageServiceProvider.class.getName(), ar -> {
      if (ar.succeeded()) {
        System.out.println(MessageServiceProvider.class.getName() + " " + ar.result());
      } else {
        System.out.println(ar.cause());
      }
    });
  }
}
