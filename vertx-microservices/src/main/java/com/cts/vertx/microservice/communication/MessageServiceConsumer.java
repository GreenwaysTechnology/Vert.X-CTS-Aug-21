package com.cts.vertx.microservice.communication;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;


public class MessageServiceConsumer extends AbstractVerticle {

  private WebClient webClient;

  @Override
  public void start() throws Exception {
    Router router = Router.router(vertx);
    webClient = WebClient.create(vertx);
    //send plain message
    router.get("/hello").handler(rc -> {
      webClient.get(3000, "localhost", "/message")
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            rc.response().setStatusCode(200).end(response.bodyAsString());
          } else {
            rc.response().setStatusCode(500).end(ar.cause().getMessage());
          }
        });

      //webClient.getAbs("http://www.example.com/rest/api/v1/someresource")
    });

    vertx.createHttpServer().requestHandler(router).listen(3001);
  }

}
