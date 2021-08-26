package com.cts.vertx.microservice.communication;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class MessageServiceProvider extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    Router router = Router.router(vertx);
    //send plain message
    router.get("/message").handler(rc -> {
      rc.response().setStatusCode(200).end("Hello! Provider");
    });
    vertx.createHttpServer().requestHandler(router).listen(3000);
  }
}
