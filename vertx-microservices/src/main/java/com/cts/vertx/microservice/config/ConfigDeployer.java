package com.cts.vertx.microservice.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ConfigDeployer {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    JsonObject serverConfig = new JsonObject()
      .put("http.port", 3000)
      .put("http.host", "locahost")
      .put("http.ssl", false);


    JsonObject applicationConfig = new JsonObject()
      .put("name", "Subramanian")
      .put("city", "Coimbatore")
      .put("state", "TN")
      .put("message", "Hello")
      .mergeIn(serverConfig);

    DeploymentOptions options = new DeploymentOptions()
      .setWorker(false)
      .setConfig(applicationConfig);
    vertx.deployVerticle(new ApplicationConfigVerticle(), options);

  }
}
