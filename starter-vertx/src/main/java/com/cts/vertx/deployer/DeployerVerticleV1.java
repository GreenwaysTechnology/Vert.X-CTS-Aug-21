package com.cts.vertx.deployer;

import com.cts.vertx.HelloWorldVerticle;
import io.vertx.core.Vertx;

public class DeployerVerticleV1 {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    //via code
    vertx.deployVerticle(new HelloWorldVerticle());
    //terminating vertx engine
    vertx.close();
  }
}
