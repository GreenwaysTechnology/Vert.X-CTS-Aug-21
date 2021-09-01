package com.cts.vertx.microservice.config;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class ExternalConfigVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    System.out.println("inside config file");
//    ConfigStoreOptions options = new ConfigStoreOptions();
//    options.setType("file");
//    options.setFormat("json");
//    options.setConfig(new JsonObject().put("path", "conf/config.json"));


    ConfigStoreOptions ymlOptions = new ConfigStoreOptions()
      .setType("file")
      .setFormat("yaml")
      .setConfig(new JsonObject()
        .put("path", "myconfig.yaml")
      );

//    ConfigStoreOptions store = new ConfigStoreOptions()
//      .setType("file")
//      .setFormat("yaml")
//      .setConfig(new JsonObject()
//        .put("path", "my-config.yaml")
//      );
//    ConfigRetriever retriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(options));

    ConfigRetriever yamlRetriever = ConfigRetriever.create(vertx,
      new ConfigRetrieverOptions().addStore(ymlOptions));

    //reterive properties
//    retriever.getConfig(config -> {
//      System.out.println("get config");
//
//      if (config.succeeded()) {
//        JsonObject configObject = config.result();
//        System.out.println(configObject.encodePrettily());
//      } else {
//        System.out.println(config.cause());
//      }
//    });
//
    yamlRetriever.getConfig(config -> {
      if (config.succeeded()) {
        JsonObject configObject = config.result();
        System.out.println(configObject.encodePrettily());
      } else {
        System.out.println(config.cause());
      }
    });
  }
}
