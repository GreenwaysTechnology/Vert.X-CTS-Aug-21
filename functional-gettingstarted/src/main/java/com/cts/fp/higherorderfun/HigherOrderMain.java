package com.cts.fp.higherorderfun;

public class HigherOrderMain {
    public static void main(String[] args) {
        Hello hello = new Hello();
        //anonmous class
        hello.sayHello(new Greeter() {
            @Override
            public void sayHello() {
                System.out.println("Hello");
            }
        });
        //functional style
        Greeter greeter = () -> System.out.println("Hello Higher order External");
        hello.sayHello(greeter);
        hello.sayHello(() -> System.out.println("Hello Higher Order inline"));

        Socket socket = new Socket();
        socket.requestHandler(() -> System.out.println("Socket Handler"));

        //Get Data from the higher order function
        HttpServer httpServer = new HttpServer();
        httpServer.handleRequest((String response) -> System.out.println(response));
        httpServer.handleRequest(response -> System.out.println(response));


    }
}
