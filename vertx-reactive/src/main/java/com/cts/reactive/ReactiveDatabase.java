package com.cts.reactive;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.jdbc.JDBCClient;

public class ReactiveDatabase extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    String url = "jdbc:hsqldb:mem:test?shutdown=true";
    String driver = "org.hsqldb.jdbcDriver";
    String CRATE_TABLE = "CREATE TABLE IF NOT EXISTS products(id int IDENTITY, name VARCHAR(255), price FLOAT, weight INT)";
    String INSERT_TABLE = "INSERT INTO products (name,price,weight) VALUES('phone',10000,100)";
    String SELECT = "SELECT * From products";
    JsonObject jsonObject = new JsonObject().put("url", url).put("driver_class", driver);
    JDBCClient jdbcClient = JDBCClient.createShared(vertx, jsonObject);

    System.out.println("Reactive Database connection starts");
    jdbcClient.rxGetConnection().flatMapObservable(conn -> {
      System.out.println("connection");
      //chaining : create table,insert row,select rows
      //return should return Observable Type-Flowable/Observable
      return conn.rxUpdate(CRATE_TABLE).flatMap(insertResult -> {
        System.out.println("INSERT TABLE OPERATION BEGINS");
        return conn.rxUpdate(INSERT_TABLE);
      }).flatMap(updateResult -> {
        System.out.println("SELECT TABLE OPERATION BEGINS");
        return conn.rxQueryStream(SELECT);
      }).toObservable();
    }).subscribe(
      row -> {
        System.out.println(row.toFlowable().subscribe(System.out::println));
      }
    );

  }
}
