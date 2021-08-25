package com.cts.vertx.microservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

public class HttpClientRequestHandling extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    //Fluent Api pattern
    vertx.createHttpServer().requestHandler(req -> {
      //how to read incoming request data
//      req.bodyHandler(bodyHandler -> {
//        System.out.println(bodyHandler.toString());
//        req.response()
//          .putHeader("content-type", "text/plain")
//          .putHeader("message", "Hello")
//          .end(bodyHandler.toString());
//      });

      //how to read json
      req.bodyHandler(bodyHandler -> {
        JsonObject jsonObject = bodyHandler.toJsonObject();
        System.out.println(jsonObject.encodePrettily());
        //Map with user
//        User user = jsonObject.mapTo(User.class);
//        System.out.println(user);
        req.response()
          .putHeader("content-type", "text/plain")
          .putHeader("message", "Hello")
          .setStatusCode(201)
          .end("Created");
      });

    }).listen(8080, ar -> {
      if (ar.succeeded()) {
        System.out.println("HTTP Server started on " + ar.result().actualPort());
      }
    });
  }
}
