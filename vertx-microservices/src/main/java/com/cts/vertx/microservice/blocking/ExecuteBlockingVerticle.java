package com.cts.vertx.microservice.blocking;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class ExecuteBlockingVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    vertx.executeBlocking(blockingHandler -> {
      //blocking code will go here
      System.out.println("start blocking" + Thread.currentThread().getName());
      try {
        Thread.sleep(10000);
        //send data to result handler
        blockingHandler.complete("This is blocking api response");
      } catch (InterruptedException e) {
        blockingHandler.fail("Someting went wrong");
      }

    }, resultHandler -> {
      System.out.println("Got Result from the  blocking Handler " + Thread.currentThread().getName());
      if (resultHandler.succeeded()) {
        System.out.println(resultHandler.result());
      }
    });

    Router router = Router.router(vertx);

    router.get("/nonblocking").handler(rc -> rc.response().end("non blocking"));

    router.get("/blocking").blockingHandler(rc -> {
      try {
        //call blocking api from this end
        //here you can call hibernate api call
        Thread.sleep(10000);
        System.out.println("inside hello");
        rc.response().end("Hello,I am blocking Response");
      } catch (Exception es) {

      }
    });

    vertx.createHttpServer().requestHandler(router).listen(8080);

  }
}
