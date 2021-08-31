package com.cts.reactive;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;

public class HelloWorldReactiveVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    //reactive http server
    HttpServer httpServer = vertx.createHttpServer();

    //handling request
    httpServer.requestStream().toFlowable().subscribe(request -> {

      //      HttpServerResponse response = request.response();
//      //enables streaming of data
//      response.setChunked(true);
////      request.toFlowable().subscribe(
////        onNext -> {
////          response.write("Hello Reactive");
////        },
////        onError -> {
////          System.out.println("err");
////        },
////        () -> {
////          response.end();
////        }
////      );

//      request.toFlowable().subscribe(
//        onNext -> {
//          response.write("Hello Reactive");
//        },
//        onError -> {
//          System.out.println("err");
//        },
//        () -> {
//          response.end();
//        }
//      );
//      request.response()
//        .setChunked(true);
      request.toFlowable().subscribe(
        onNext -> {
          // response.write("Hello Reactive");
          request.response()
            .setChunked(true).write("Hello Reactive!!!!");
        },
        System.out::println,
        () -> {
          request.response()
            .end();
        }
      );

    });

    httpServer.rxListen(3000).subscribe(httpServer1 -> {
      System.out.println("Reactive Server is running" + httpServer1.actualPort());
    }, err -> {
      System.out.println("Server startup faild");
    });
  }
}
