package com.cts.vertx.microservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    //create WebServer
    HttpServer httpServer = vertx.createHttpServer();
    //Handle Client Request : on event loop thread
    httpServer.requestHandler(request -> {
      //how to send response
      HttpServerResponse response = request.response();
      //set status code
      response.setStatusCode(200);
      //set content type
      response.putHeader("content-type", "text/plain");
      //set custom header
      response.putHeader("greeting", "Hello");
      //content length header
      response.putHeader("Content-Length", "100");
      //write data into output stream
      response.write("Hello");
      //close the connection
      response.end();
    });

    //Start Server
    httpServer.listen(8080, ar -> {
      if (ar.succeeded()) {
        System.out.println("HTTP Server started on " + ar.result().actualPort());
      }
    });

  }
}
