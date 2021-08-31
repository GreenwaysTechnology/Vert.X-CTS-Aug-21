package com.cts.vertx_scalling;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;

public class GreeterService extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();

    HttpServer httpServer = vertx.createHttpServer();
    httpServer.requestStream().toFlowable().subscribe(req -> {
      req.response().end("Hello!How are you : " + Thread.currentThread().getName() + " " + hashCode());
    });
    httpServer.rxListen(3000).subscribe(onSuccess -> {
      System.out.println("Server is Running on " + onSuccess.actualPort());
    });
  }
}
