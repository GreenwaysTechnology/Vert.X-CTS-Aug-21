package com.cts.vertx.microservice.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

public class HelloService extends AbstractVerticle {

  public static final String MESSAGE_ADDRESS = "greeting.message";

  @Override
  public void start() throws Exception {
    super.start();
    EventBus eventBus = vertx.eventBus();
    MessageConsumer<String> consumer = eventBus.consumer(MESSAGE_ADDRESS);
    //list for message
    consumer.handler(message -> {
      System.out.println(HelloService.class.getName() + "  -  " + message.body());
    });
  }
}
