package com.cts.fp.params;

public class ParamsMain {
    public static void main(String[] args) {
        //Without Param
        Welcome welcome = null;
        welcome = () -> {
            //functionbody
            System.out.println("Welcome");
        };
        welcome.sayWelcome();
        //Body has only one line of code
        welcome = welcome = () -> System.out.println("Welcome");
        welcome.sayWelcome();

        //params and args :single
        Name myname = null;
        //(name)-args
        myname = (String name) -> System.out.println(name);
        //"Subramanian" - params
        myname.setName("Subramanian");

        //Type inference
        myname = (name) -> System.out.println(name);
        //"Subramanian" - params
        myname.setName("Subramanian");

        myname = name -> System.out.println(name);
        //"Subramanian" - params
        myname.setName("Subramanian");

        //two args
        Adder adder = null;

        adder = (int a, int b) -> {
            int c = a + b;
            System.out.println("Add " + c);
        };
        adder.add(10, 10);
        adder = (a, b) -> {
            int c = a + b;
            System.out.println("Add " + c);
        };
        adder.add(10, 10);
        ///Object
        LoggerService loggerService = null;

        loggerService = (Log logger) -> {
            logger.info("Something new");
        };
        loggerService.log(new Log());
        //type inference
        loggerService = logger ->  logger.info("Something new");
        loggerService.log(new Log());


    }
}
