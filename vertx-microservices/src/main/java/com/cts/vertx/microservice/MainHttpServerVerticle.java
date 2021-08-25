package com.cts.vertx.microservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainHttpServerVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    //Fluent Api pattern
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .putHeader("message", "Hello")
        .end("Hello");
    }).listen(8080, ar -> {
      if (ar.succeeded()) {
        System.out.println("HTTP Server started on " + ar.result().actualPort());
      }
    });

  }
}
