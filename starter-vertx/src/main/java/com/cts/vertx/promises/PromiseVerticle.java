package com.cts.vertx.promises;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.example.util.Runner;

public class PromiseVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(PromiseVerticle.class);
  }

  public Promise<String> login() {
    String username = "admin";
    String password = "admin";
    Promise promise = Promise.promise();
    if (username.equals("admin") && password.equals("admin")) {
      promise.complete("login success");
    } else {
      promise.fail("Login failed");
    }
    return promise;
  }

  @Override
  public void start() throws Exception {
    super.start();
    login().future().onComplete(ar -> {
      if (ar.succeeded()) {
        System.out.println(ar.result());
      } else {
        System.out.println(ar.cause());
      }
    });
  }
}
