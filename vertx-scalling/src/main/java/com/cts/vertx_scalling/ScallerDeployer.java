package com.cts.vertx_scalling;

import io.vertx.core.DeploymentOptions;
import io.vertx.reactivex.core.Vertx;

public class ScallerDeployer {
  //mai
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    DeploymentOptions options = new DeploymentOptions().setInstances(3);

    vertx.rxDeployVerticle(GreeterService.class.getName(), options).subscribe(res -> {
      System.out.println(GreeterService.class.getName() + " " + res);
    }, err -> {
      System.out.println(err);
    });

    vertx.rxDeployVerticle(ApiGateWayService.class.getName(), options).subscribe(res -> {
      System.out.println(ApiGateWayService.class.getName() + " " + res);
    }, err -> {
      System.out.println(err);
    });
  }
}
