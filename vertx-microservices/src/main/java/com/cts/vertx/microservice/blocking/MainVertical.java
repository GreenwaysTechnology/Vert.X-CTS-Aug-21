package com.cts.vertx.microservice.blocking;

import io.vertx.core.Vertx;

public class MainVertical {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
     for (int i = 1; i <=26; i++) {
      vertx.deployVerticle(new NonBlockingVertical());
    }
  }
}
