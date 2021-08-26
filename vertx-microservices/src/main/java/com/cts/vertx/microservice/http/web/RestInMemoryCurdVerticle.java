package com.cts.vertx.microservice.http.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.HashMap;
import java.util.Map;

public class RestInMemoryCurdVerticle extends AbstractVerticle {

  //in memeory db
  private Map<String, JsonObject> products = new HashMap<>();

  @Override
  public void start() throws Exception {
    setupIntialData();
    //create Router object
    Router router = Router.router(vertx);

    //expose api

    //This handler is called for every request and every url
    //middlewares/filters/interceptors
    router.route().handler(rc -> {
      System.out.println(rc.request().method() + " - " + rc.request().path());
      rc.next();
    });
    //Middleware for this url
    router.route(HttpMethod.GET, "/api/products").handler(rc -> {
      System.out.println("Products api called");
      rc.next();
    });
    //handling Body payloads : Middlewares
    router.route().handler(BodyHandler.create());

    router.get("/api/products").handler(rc -> {
      JsonArray arr = new JsonArray();
      products.forEach((k, v) -> arr.add(v));
      rc.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
    });
    router.get("/api/products/:productId").handler(rc -> {
      String productId = rc.request().getParam("productId");
      HttpServerResponse response = rc.response();
      //if product id is null
      if (productId == null) {
        sendError(400, response);
      } else {
        JsonObject product = products.get(productId);
        if (product == null) {
          sendError(400, response);
        } else {
          response.putHeader("content-type", "application/json").end(product.encodePrettily());
        }
      }

    });
    router.post("/api/products/:productId").handler(rc -> {
      String productId = rc.request().getParam("productId");
      HttpServerResponse response = rc.response();
      if (productId == null) {
        sendError(400, response);
      } else {
        JsonObject product = rc.getBodyAsJson();
        if (product == null) {
          sendError(400, response);
        } else {
          //add a new product
          products.put(productId, product);
          response.setStatusCode(201).end("Product is saved");
        }
      }
    });
    //todo--you have to implement update
    router.put("/api/products/:productId").handler(rc -> {
      rc.response().end("update Product By Id");
    });
    //todo--you have to implement update
    router.delete("/api/products/:productId").handler(rc -> {
      rc.response().end("delete Product By Id");
    });

    vertx.createHttpServer().requestHandler(router).listen(8080, ar -> System.out.println(
      "Server is Runnin in " + ar.result().actualPort()
    ));

  }

  private void sendError(int statuscode, HttpServerResponse response) {
    response.setStatusCode(statuscode).end();
  }

  private void setupIntialData() {
    addProduct(new JsonObject().put("id", "prod1234").put("name", "Tv").put("price", 10000));
    addProduct(new JsonObject().put("id", "prod1235").put("name", "watch").put("price", 234));
    addProduct(new JsonObject().put("id", "prod1236").put("name", "radio").put("price", 1000));
    addProduct(new JsonObject().put("id", "prod1237").put("name", "computer").put("price", 345));
    addProduct(new JsonObject().put("id", "prod1238").put("name", "tableandchair").put("price", 8999));

  }

  private void addProduct(JsonObject jsonObject) {
    products.put(jsonObject.getString("id"), jsonObject);
  }
}
