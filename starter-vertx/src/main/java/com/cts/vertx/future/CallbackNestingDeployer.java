package com.cts.vertx.future;

import io.vertx.core.AbstractVerticle;
import io.vertx.example.util.Runner;

public class CallbackNestingDeployer extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(CallbackNestingDeployer.class);
  }

  @Override
  public void start() throws Exception {
    super.start();
    vertx.deployVerticle(new CallbackNestingVerticle());
  }
}
