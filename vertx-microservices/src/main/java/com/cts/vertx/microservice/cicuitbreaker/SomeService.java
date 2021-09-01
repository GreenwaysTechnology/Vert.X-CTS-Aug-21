package com.cts.vertx.microservice.cicuitbreaker;

import io.vertx.core.AbstractVerticle;

public class SomeService extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    vertx.createHttpServer().requestHandler(r -> {
      vertx.setTimer(2000, ar -> {
        r.response().end("I am fine but delayed!");
      });
    }).listen(3000);
  }
}
