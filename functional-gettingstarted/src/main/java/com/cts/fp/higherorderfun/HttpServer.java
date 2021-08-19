package com.cts.fp.higherorderfun;


public class HttpServer {

    public void handleRequest(HttpHandler<String> httpHandler){
        httpHandler.handle("Hello HTTP Response");
    }
}
