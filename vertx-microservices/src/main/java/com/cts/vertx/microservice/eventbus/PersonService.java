package com.cts.vertx.microservice.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

public class PersonService extends AbstractVerticle {

  public static final String PERSON_ADDRESS = "person.save";

  @Override
  public void start() throws Exception {
    super.start();
    EventBus eventBus = vertx.eventBus();
    MessageConsumer<JsonObject> consumer = eventBus.consumer(PERSON_ADDRESS);
    //list for message
    consumer.handler(message -> {
      System.out.println(message.headers().get("id"));
      System.out.println(message.body().encodePrettily());
    });
  }
}
