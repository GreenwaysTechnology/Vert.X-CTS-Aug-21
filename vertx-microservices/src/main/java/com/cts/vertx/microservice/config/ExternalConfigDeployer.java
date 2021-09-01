package com.cts.vertx.microservice.config;

import io.vertx.core.Vertx;

public class ExternalConfigDeployer {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new ExternalConfigVerticle());
  }
}
