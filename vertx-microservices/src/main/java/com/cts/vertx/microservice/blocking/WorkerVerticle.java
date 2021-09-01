package com.cts.vertx.microservice.blocking;

import io.vertx.core.AbstractVerticle;

public class WorkerVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    System.out.println(Thread.currentThread().getName());
    //work verticle , we can write blocking code
    Thread.sleep(10000);
    vertx.createHttpServer().requestHandler(ar -> {
      System.out.println("Inside Http Server" + Thread.currentThread().getName());
      ar.response().end("hello");
    }).listen(8080);
    System.out.println("Hello");
  }
}
