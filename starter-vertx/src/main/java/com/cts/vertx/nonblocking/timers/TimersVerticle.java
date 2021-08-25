package com.cts.vertx.nonblocking.timers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.example.util.Runner;

import java.util.Date;

public class TimersVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(TimersVerticle.class);
  }

  public void blockMe(String message) {
    System.out.println(message);
  }

  public void delay() {
    vertx.setTimer(1000, ar -> {
      System.out.println("Hello , I am delayed");
    });
  }

  public void tick() {
    vertx.setPeriodic(1000, ar -> {
      System.out.println(new Date());
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    blockMe("start");
    delay();
    tick();
    blockMe("end");
  }
}
