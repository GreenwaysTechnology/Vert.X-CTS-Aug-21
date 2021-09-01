package com.cts.vertx.microservice.eventbus;

import io.vertx.core.AbstractVerticle;

public class Deployer extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    vertx.deployVerticle(ApiGateWayService.class.getName(), ar -> {
      if (ar.succeeded()) {
        System.out.println(ApiGateWayService.class.getName() + " " + ar.result());
      } else {
        System.out.println(ApiGateWayService.class.getName() + " " + ar.cause());
      }
    });
    vertx.deployVerticle(GreeterService.class.getName(), ar -> {
      if (ar.succeeded()) {
        System.out.println(GreeterService.class.getName() + " " + ar.result());
      } else {
        System.out.println(GreeterService.class.getName() + " " + ar.cause());
      }
    });
    vertx.deployVerticle(HelloService.class.getName(), ar -> {
      if (ar.succeeded()) {
        System.out.println(HelloService.class.getName() + " " + ar.result());
      } else {
        System.out.println(HelloService.class.getName() + " " + ar.cause());
      }
    });
    vertx.deployVerticle(PersonService.class.getName(), ar -> {
      if (ar.succeeded()) {
        System.out.println(PersonService.class.getName() + " " + ar.result());
      } else {
        System.out.println(PersonService.class.getName() + " " + ar.cause());
      }
    });

    vertx.deployVerticle(CustomerService.class.getName(), ar -> {
      if (ar.succeeded()) {
        System.out.println(CustomerService.class.getName() + " " + ar.result());
      } else {
        System.out.println(CustomerService.class.getName() + " " + ar.cause());
      }
    });
  }
}
