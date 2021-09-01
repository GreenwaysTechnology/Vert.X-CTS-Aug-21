package com.cts.vertx.microservice.config;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class ApplicationConfigVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    JsonObject myConfig = config();
    System.out.println(myConfig.encodePrettily());

    //system propertiess
    System.out.println(System.getProperty("java.home"));
    System.out.println(System.getProperty("path.separator"));
    //env variables
    System.out.println(System.getenv("path"));

    vertx.createHttpServer()
      .requestHandler(request -> {
        request.response().end(config().getString("message", "You are lucky!!"));
      })
      .listen(config().getInteger("http.port", 3000));

  }
}
