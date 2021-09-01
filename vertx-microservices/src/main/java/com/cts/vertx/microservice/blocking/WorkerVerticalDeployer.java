package com.cts.vertx.microservice.blocking;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class WorkerVerticalDeployer {
  public static void main(String[] args) {
    //convert normal vertical into worker vertical
    Vertx vertx = Vertx.vertx();
    //How to configure Verticle : Verticle configuration
    //control verticle configurations
    DeploymentOptions options = new DeploymentOptions().setWorker(true);
    vertx.deployVerticle(new WorkerVerticle(), options);
   // vertx.deployVerticle(new WorkerVerticle());


  }
}
