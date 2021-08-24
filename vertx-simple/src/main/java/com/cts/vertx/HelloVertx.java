package com.cts.vertx;

import io.vertx.core.Vertx;

public class HelloVertx {
    public static void main(String[] args) {
        // Create Vertx Engine
        Vertx vertxEngine = Vertx.vertx();
        //How to create simple non blocking webserver
        vertxEngine.createHttpServer().requestHandler(req -> {
            req.response().end("Hello Vertx");
        }).listen(8080);

    }
}
