package com.cts.vertx.future;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class CallbackNestingVerticle extends AbstractVerticle {

  //getUser
  public Future<String> getUser() {
    System.out.println("GetUser is called");
    String userName = "admin";
    if (userName != null) {
      return Future.succeededFuture(userName);
    }
    return Future.failedFuture("User not Found");
  }

  //login
  public Future<String> login(String userName) {
    System.out.println("Login is called");
    if (userName.equals("admin")) {
      return Future.succeededFuture("Login successFull"); //trigger event
    }
    return Future.failedFuture("Login Failed");
  }

  //showpage
  public Future<String> showpage(String status) {
    System.out.println("showPage is called");
    if (status.equals("Login successFull")) {
      return Future.succeededFuture("You are Admin");
    }
    return Future.failedFuture("You are guest");
  }

  public void callbackHell() {
    getUser().onComplete(event -> {
      if (event.succeeded()) {
        login(event.result()).onComplete(loginEvent -> {
          if (loginEvent.succeeded()) {
            System.out.println(loginEvent.result());
            //call show page
            showpage(loginEvent.result()).onComplete(pageEvent -> {
              if (pageEvent.succeeded()) {
                System.out.println(pageEvent.result());
              } else {
                System.out.println(pageEvent.cause());
              }
            });
          } else {
            System.out.println(loginEvent.cause());
          }
        });
      } else {
        System.out.println(event.cause());
      }
    });
  }

  @Override
  public void start() throws Exception {
    super.start();
    //callbackHell();
    //compose ; based on function composition.
    getUser().compose(user -> {
        return login(user); //return Future
      })
      .compose(status -> {
        return showpage(status); //return Future
      })
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
    //"hello".trim().toUpperCase().startsWith("")

    //code refactoring
    getUser().compose(user -> login(user))
      .compose(status -> showpage(status))
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
    //method reference
    getUser()
      .compose(this::login)
      .compose(this::showpage)
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
  }
}
