package com.cts.vertx.microservice.http.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class SimpleRest extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    //create Router object
    Router router = Router.router(vertx);

    //expose api
    router.get("/api/products").handler(rc -> {
      rc.response().end("findAll products");
    });
    router.get("/api/products/:productId").handler(rc -> {
      rc.response().end("Get Product By Id");
    });
    router.post("/api/products").handler(rc -> {
      rc.response().end("Product Saved");
    });
    router.put("/api/products/:productId").handler(rc -> {
      rc.response().end("update Product By Id");
    });
    router.delete("/api/products/:productId").handler(rc -> {
      rc.response().end("delete Product By Id");
    });

    vertx.createHttpServer().requestHandler(router).listen(8080, ar -> System.out.println(
      "Server is Runnin in " + ar.result().actualPort()
    ));

  }
}
