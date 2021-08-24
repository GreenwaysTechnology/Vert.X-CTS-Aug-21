package com.cts.vertx.future;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.example.util.Runner;

public class FutureHigherOrderVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(FutureHigherOrderVerticle.class);
  }

  public void startDbServer(Handler<AsyncResult<String>> aHandler) {
    //how to encapsulate data into future and send it back to caller
    aHandler.handle(Future.succeededFuture("callback"));
  }

  @Override
  public void start() throws Exception {
    super.start();
    startDbServer(ar->{
       if(ar.succeeded()){
         System.out.println(ar.result());
       }
    });

  }
}
