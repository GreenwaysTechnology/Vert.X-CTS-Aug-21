package com.cts.vertx.microservice.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;


public class JsonHttpVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    //old style
//    JsonObject jsonObject = new JsonObject();
//    jsonObject .put("id", 1);
//    jsonObject .put("name", "Subramanian");
//    jsonObject  .put("city", "coimbatore");

    //fluent style
//    JsonObject user = new JsonObject()
//      .put("id", 1)
//      .put("name", "Subramanian")
//      .put("city", "coimbatore");

    //Map object can be converted into json
//    Map<String, Object> map = new HashMap<>();
//    map.put("id", 1);
//    map.put("name", "Subramanian");
//    map.put("city", "coimbatore");
//    JsonObject user = new JsonObject(map);

    User userModel = new User(1, "Subramanian", "Coimbatore");
    JsonObject user = new JsonObject()
      .put("id", userModel.getId())
      .put("name", userModel.getName())
      .put("city", userModel.getCity());

    //return list of users:JsonArray
    JsonArray users = new JsonArray()
      .add(user)
      .add(new JsonObject().put("id", 2).put("name", "Ram").put("city", "Coimbatore"));

    //Fluent Api pattern
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "application/json")
        .putHeader("message", "Hello")
        .end(users.encodePrettily());
    }).listen(8080, ar -> {
      if (ar.succeeded()) {
        System.out.println("HTTP Server started on " + ar.result().actualPort());
      }
    });

  }
}
