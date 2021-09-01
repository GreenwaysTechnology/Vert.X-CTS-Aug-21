package com.cts.vertx.microservice.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class ApiGateWayService extends AbstractVerticle {
  public static final String MESSAGE_ADDRESS = "greeting.message";
  public static final String PERSON_ADDRESS = "person.save";
  public static final String CUSTOMER_ADDRESS = "customer.save";

  @Override
  public void start() throws Exception {
    super.start();
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    EventBus eventBus = vertx.eventBus();
    router.get("/api/greeter/:message").handler(rc -> {
      //Send to to event Bus
      String message = rc.request().getParam("message");
      //one to many : pub sub pattern
      eventBus.publish(MESSAGE_ADDRESS, message);
      rc.response().end("Message has been sent");
    });

    //router for sending messsage to only consumer
    router.post("/api/person").handler(rc -> {
      JsonObject person = rc.getBodyAsJson();
      eventBus.send(PERSON_ADDRESS, person, new DeliveryOptions().addHeader("id", person.getString("id")));
      rc.response().setStatusCode(201).end();
    });

    //sending message to only one consumer and get ack.
    router.post("/api/customer").handler(rc -> {
      JsonObject customer = rc.getBodyAsJson();
      eventBus.request(CUSTOMER_ADDRESS, customer, new DeliveryOptions().addHeader("id", customer.getString("id")), ar -> {
        String ack = ar.result().body().toString();
        rc.response().setStatusCode(201).end(ack);
      });
    });

    vertx.createHttpServer().requestHandler(router).listen(3000, ar -> {
      System.out.println("Server is Running @ " + ar.result().actualPort());
    });

  }
}
