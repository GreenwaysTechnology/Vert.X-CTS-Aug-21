package com.cts.reactive;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;

public class ReactiveEventBus extends AbstractVerticle {

  String ADDRESS = "greeting.message";

  @Override
  public void start() throws Exception {
    super.start();
    EventBus eventBus = vertx.eventBus();

    //consumer
    eventBus.consumer(ADDRESS).toFlowable().subscribe(message -> {
      System.out.println("Received : " + message.body());
      message.reply("PONG");  //ack
    });

    //publish message using timer

    vertx.setTimer(5000, v -> {
      eventBus.rxRequest(ADDRESS, "PING").subscribe(
        message -> {
          System.out.println(message.body());
        }
      );
    });

  }
}
