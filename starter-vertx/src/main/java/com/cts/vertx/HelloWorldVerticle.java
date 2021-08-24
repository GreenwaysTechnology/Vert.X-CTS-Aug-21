package com.cts.vertx;

import io.vertx.core.AbstractVerticle;

public class HelloWorldVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
      super.start();
      //is called when verticle is being deployed
    System.out.println("Hello world verticle is deployed");
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    System.out.println("Hello world verticle is undeployed");
  }
}
