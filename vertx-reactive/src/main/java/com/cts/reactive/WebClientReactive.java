package com.cts.reactive;

import io.reactivex.Single;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.codec.BodyCodec;

public class WebClientReactive extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    WebClient webClient = WebClient.create(vertx);

    HttpServer httpServer = vertx.createHttpServer();
    httpServer.requestStream().toFlowable().subscribe(req -> {
      //Reactive is lazy
      Single<HttpResponse<String>> request = webClient
        .get(3000, "localhost", "/").as(BodyCodec.string()).rxSend();
      //until subscribe method is called , which never calls api
      request.subscribe(res -> {
        System.out.println("Server response " + res.body() + " " + res.statusCode());
         req.response().end("Hello!How are you");
      });
    });
    httpServer.rxListen(3001).subscribe(onSuccess -> {
      System.out.println("Client is Running on " + onSuccess.actualPort());
    });

  }
}
