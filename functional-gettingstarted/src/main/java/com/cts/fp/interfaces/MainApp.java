package com.cts.fp.interfaces;

public class MainApp {
    public static void main(String[] args) {
        Greeter greeter = null;
        //implmentation
        greeter = new HelloImpl();
        System.out.println(greeter.sayGreet());

        greeter = new HaiImpl();
        System.out.println(greeter.sayGreet());

        //Anonmous Innerclasses
        greeter = new Greeter() {
            @Override
            public String sayGreet() {
                return "Hello Again";
            }
        };
        System.out.println(greeter.sayGreet());

        greeter = new Greeter() {
            @Override
            public String sayGreet() {
                return "Hai Again";
            }
        };
        System.out.println(greeter.sayGreet());

        //Lambda : function is first class citizen.
        greeter = () -> {
            return "Hello Lambda ";
        };
        System.out.println(greeter.sayGreet());
        greeter = () -> {
            return "Hai Lambda ";
        };
        System.out.println(greeter.sayGreet());

    }
}
