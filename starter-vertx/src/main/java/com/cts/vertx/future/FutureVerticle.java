package com.cts.vertx.future;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class FutureVerticle extends AbstractVerticle {

  //Callee : Here we are not going to return data,rather Future Object
  public Future<String> createSuccessFuture() {
    //Create Future Object
    return Future.future(handler -> {
      handler.complete("Hello!");
    });
  }

  public Future<String> createFailureFuture() {
    //Create Future Object
    return Future.future(handler -> {
      handler.fail("Something Went Wrong");
    });
  }

  //How to send Future based on biz logic
  public Future<String> login(String username, String password) {
    if (username.equals("admin") && password.equals("admin")) {
      return Future.future(handler -> {
        handler.complete("Login success");
      });
    }
    return Future.future(handler -> {
      handler.fail("Login failed");
    });

  }


  @Override
  public void start() throws Exception {
    super.start();
    //Caller
//    createSuccessFuture().onComplete(new Handler<AsyncResult<String>>() {
//      @Override
//      public void handle(AsyncResult<String> event) {
//        if (event.succeeded()) {
//            //Grab result
//          System.out.println(event.result());
//        } else {
//
//        }
//      }
//    });
    createSuccessFuture().onComplete(event -> {
      if (event.succeeded()) {
        //Grab result
        System.out.println(event.result());
      } else {
        System.out.println(event.cause());
      }
    });
    //The above code can be reduced
    createSuccessFuture().onSuccess(response -> {
      System.out.println(response);
    });
    createSuccessFuture().onSuccess(System.out::println);
    ///////////////////////////////////////////
    createFailureFuture().onComplete(event -> {
      if (event.failed()) {
        System.out.println(event.cause());
      }
    });
    createFailureFuture().onFailure(System.out::println);
    ///////////////////////////////////////////////////////////////////////////////////////

    login("admin", "admin").onComplete(event -> {
      if (event.succeeded()) {
        System.out.println(event.result());
      } else {
        System.out.println(event.cause());
      }
    });

    login("admin", "admin")
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
  }
}
