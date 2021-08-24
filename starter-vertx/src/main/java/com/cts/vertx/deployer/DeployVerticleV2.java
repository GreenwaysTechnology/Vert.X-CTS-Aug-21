package com.cts.vertx.deployer;

import com.cts.vertx.HelloWorldVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.example.util.Runner;

public class DeployVerticleV2 extends AbstractVerticle {

  public static void main(String[] args) {
    //Utiltity class.
    Runner.runExample(DeployVerticleV2.class);
  }

  @Override
  public void start() throws Exception {
    super.start();
    //Vertx vertx = Vertx.vertx();
    //via code
    vertx.deployVerticle(new HelloWorldVerticle());
  }
}
