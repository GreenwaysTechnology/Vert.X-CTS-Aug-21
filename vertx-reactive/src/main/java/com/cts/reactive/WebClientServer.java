package com.cts.reactive;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;

public class WebClientServer extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();

    //Server Object Creation
    HttpServer httpServer = vertx.createHttpServer();

//    //Request-Response Handling
    httpServer.requestStream().toFlowable().subscribe(req -> {
      System.out.println("Request has come");
      req.response().end("Hello!How are you");
    });

    //Start Server: add Socket in the Kernal Fd, and ensures that which is ready to emit events
    httpServer.rxListen(3000).subscribe(onSuccess -> {
      System.out.println("Server is Running on " + onSuccess.actualPort());
    });


  }
}
