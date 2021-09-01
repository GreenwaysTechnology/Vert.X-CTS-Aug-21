package com.cts.vertx.microservice.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

public class CustomerService extends AbstractVerticle {

  public static final String CUSTOMER_ADDRESS = "customer.save";

  @Override
  public void start() throws Exception {
    super.start();
    EventBus eventBus = vertx.eventBus();
    MessageConsumer<JsonObject> consumer = eventBus.consumer(CUSTOMER_ADDRESS);
    //list for message
    consumer.handler(message -> {
      System.out.println(message.headers().get("id"));
      System.out.println(message.body().encodePrettily());
      message.reply("Customer Saved Successfully");
    });
  }
}
