package com.cts.vertx.microservice.blocking;

import io.vertx.core.AbstractVerticle;

public class BlockingVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    System.out.println(Thread.currentThread().getName());
    Thread.sleep(10000);
    System.out.println("Sleep over");
  }
}
