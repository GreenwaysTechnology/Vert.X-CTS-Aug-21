package com.cts.vertx.microservice.blocking;

import io.vertx.core.AbstractVerticle;

public class NonBlockingVertical extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    super.start();
    System.out.println(this.hashCode() + " - " + Thread.currentThread().getName());
  }
}
