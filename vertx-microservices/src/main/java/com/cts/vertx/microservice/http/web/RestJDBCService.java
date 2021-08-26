package com.cts.vertx.microservice.http.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.jdbcclient.JDBCPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlResult;
import io.vertx.sqlclient.templates.SqlTemplate;
import io.vertx.sqlclient.templates.TupleMapper;

import java.util.Arrays;
import java.util.Map;

public class RestJDBCService extends AbstractVerticle {

  //jdbc connection pool object
  private JDBCPool client;

  //SQL Template api
  private SqlTemplate<Map<String, Object>, RowSet<JsonObject>> getProductTemplate;
  private SqlTemplate<JsonObject, SqlResult<Void>> addProductTemplate;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    //establish connection
    client = JDBCPool.pool(vertx, new JsonObject()
      .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
      .put("dirver_class", "org.hsqldb.jdbcDriver"));

    //Test Connection
    client.getConnection().onSuccess(System.out::println).onFailure(System.out::println);

    //Getting Products
    String selectQuery = "SELECT id,name,price,weight FROM products WHERE id= #{id}";
    getProductTemplate = SqlTemplate.forQuery(client, selectQuery).mapTo(Row::toJson);

    //insert
    String insertQuery = "INSERT INTO  products(name,price,weight) VALUES (#{name},#{price},#{weight})";
    addProductTemplate = SqlTemplate.forUpdate(client, insertQuery).mapFrom(TupleMapper.jsonObject());

    //How to poulate some mock data
    String CRATE_TABLE = "CREATE TABLE IF NOT EXISTS products(id int IDENTITY, name VARCHAR(255), price FLOAT, weight INT)";

    client.query(CRATE_TABLE)
      .execute()
      .compose(res -> addProductTemplate.executeBatch(
        Arrays.asList(
          new JsonObject().put("name", "Tv").put("price", 10000).put("weight", 2),
          new JsonObject().put("name", "watch").put("price", 234).put("weight", 2),
          new JsonObject().put("name", "radio").put("price", 1000).put("weight", 2),
          new JsonObject().put("name", "computer").put("price", 345).put("weight", 2),
          new JsonObject().put("name", "tableandchair").put("price", 8999).put("weight", 2))))
      .compose(v -> {
        //After inserting data : start web part
        Router router = Router.router(vertx);
        //This handler is called for every request and every url
        //middlewares/filters/interceptors
        router.route().handler(rc -> {
          System.out.println(rc.request().method() + " - " + rc.request().path());
          rc.next();
        });
        //Middleware for this url
        router.route(HttpMethod.GET, "/api/products").handler(rc -> {
          System.out.println("Products api called");
          rc.next();
        });

        //handling Body payloads : Middlewares
        router.route().handler(BodyHandler.create());
        //Get all the products from the database
        router.get("/api/products").handler(this::handleGetProducts);
        router.post("/api/products").handler(this::handleAddProducts);

        return vertx.createHttpServer().requestHandler(router).listen(8080);

      }).<Void>mapEmpty().onComplete(startPromise);

  }

  private void handleAddProducts(RoutingContext rc) {
    HttpServerResponse response = rc.response();
    JsonObject product = rc.getBodyAsJson();
    addProductTemplate.execute(product)
      .onSuccess(res -> {
        response.setStatusCode(201).end("Created");
      }).onFailure(err -> {
        response.setStatusCode(500);
      });
  }

  private void handleGetProducts(RoutingContext rc) {
    HttpServerResponse response = rc.response();
    String selectProducts = "SELECT * From products";
    client.query(selectProducts)
      .execute(rowSet -> {
        if (rowSet.failed()) {
          rc.fail(500);
        } else {
          JsonArray arr = new JsonArray();
          rowSet.result().forEach(row -> {
            arr.add(row.toJson());
          });
          rc.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
        }
      });


  }
}
