package com.insight7.ordertracking.controller;

import com.insight7.ordertracking.TestOrderTrackingApplication;
import com.insight7.ordertracking.controller.models.OrderResponse;
import com.insight7.ordertracking.controller.models.OrderStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;


import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestOrderTrackingApplication.class})
public class OrderControllerIT {

    private static final Logger log = LoggerFactory.getLogger(OrderControllerIT.class);
    private static final String BASE_ORDER_URL = "/orders";
    private static final String ORDER_BY_ID = BASE_ORDER_URL+"/%s";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = String.format("%s:%s",RestAssured.DEFAULT_URI,port);
        RestAssured.port = port;
        log.info("setup: baseUrl: {}",RestAssured.baseURI);
    }

    @Test
    public void createOrderTest(){

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"source\":\"Bangalore\",\"destination\":\"Delhi\",\"noOfPkgs\":2,\"weight\":40,\"volume\":1000}")
                .when()
                .post(BASE_ORDER_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();
        OrderResponse orderResponse = from(response.asString()).getObject("", OrderResponse.class);
        Assertions.assertEquals(orderResponse.getSource(),"Bangalore","Order.source compare");
        Assertions.assertEquals(orderResponse.getDestination(),"Delhi","Order.destination compare");
        Assertions.assertEquals(orderResponse.getStatus(), OrderStatus.PLACED,"Order.status compare");
        Assertions.assertEquals(orderResponse.getNoOfPkgs(),2,"Order.noOfPkgs compare");
        Assertions.assertEquals(orderResponse.getWeight(),40,"Order.weight compare");
        Assertions.assertEquals(orderResponse.getVolume(),1000,"Order.volume compare");
        Assertions.assertNotNull(orderResponse.getId(),"Order.id not null");
        Assertions.assertNotNull(orderResponse.getCreateTime(),"Order.creationTime not null");

        // without source
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"destination\":\"Delhi\",\"noOfPkgs\":2,\"weight\":40,\"volume\":1000}")
                .when()
                .post(BASE_ORDER_URL)
                .then()
                .statusCode(400); // bad request

        // without destination
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"source\":\"Bangalore\",\"noOfPkgs\":2,\"weight\":40,\"volume\":1000}")
                .when()
                .post(BASE_ORDER_URL)
                .then()
                .statusCode(400); // bad request

        // without noOfPkgs
       given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"source\":\"Bangalore\",\"destination\":\"Delhi\",\"weight\":40,\"volume\":1000}")
                .when()
                .post(BASE_ORDER_URL)
                .then()
                .statusCode(400); // bad request

        // with negative noOfPkgs
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"source\":\"Bangalore\",\"destination\":\"Delhi\",\"noOfPkgs\":-2,\"weight\":40,\"volume\":1000}")
                .when()
                .post(BASE_ORDER_URL)
                .then()
                .statusCode(400); // bad request

        // with negative weight
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"source\":\"Bangalore\",\"destination\":\"Delhi\",\"noOfPkgs\":2,\"weight\":-40,\"volume\":1000}")
                .when()
                .post(BASE_ORDER_URL)
                .then()
                .statusCode(400); // bad request

        // with negative volume
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"source\":\"Bangalore\",\"destination\":\"Delhi\",\"noOfPkgs\":2,\"weight\":40,\"volume\":-1000}")
                .when()
                .post(BASE_ORDER_URL)
                .then()
                .statusCode(400); // bad request

        //without weight
        response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"source\":\"Bangalore\",\"destination\":\"Delhi\",\"noOfPkgs\":2,\"volume\":1000}")
                .when()
                .post(BASE_ORDER_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();
        orderResponse = from(response.asString()).getObject("", OrderResponse.class);
        Assertions.assertEquals(orderResponse.getSource(),"Bangalore","Order.source compare");
        Assertions.assertEquals(orderResponse.getDestination(),"Delhi","Order.destination compare");
        Assertions.assertEquals(orderResponse.getStatus(), OrderStatus.PLACED,"Order.status compare");
        Assertions.assertEquals(orderResponse.getNoOfPkgs(),2,"Order.noOfPkgs compare");
        Assertions.assertNull(orderResponse.getWeight(), "Order.weight compare");
        Assertions.assertEquals(orderResponse.getVolume(),1000,"Order.volume compare");
        Assertions.assertNotNull(orderResponse.getId(),"Order.id not null");
        Assertions.assertNotNull(orderResponse.getCreateTime(),"Order.creationTime not null");

        //without volume
        response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"source\":\"Bangalore\",\"destination\":\"Delhi\",\"noOfPkgs\":2,\"weight\":40}")
                .when()
                .post(BASE_ORDER_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();
        orderResponse = from(response.asString()).getObject("", OrderResponse.class);
        Assertions.assertEquals(orderResponse.getSource(),"Bangalore","Order.source compare");
        Assertions.assertEquals(orderResponse.getDestination(),"Delhi","Order.destination compare");
        Assertions.assertEquals(orderResponse.getStatus(), OrderStatus.PLACED,"Order.status compare");
        Assertions.assertEquals(orderResponse.getNoOfPkgs(),2,"Order.noOfPkgs compare");
        Assertions.assertEquals(orderResponse.getWeight(),40,"Order.weight compare");
        Assertions.assertNull(orderResponse.getVolume(), "Order.volume compare");
        Assertions.assertNotNull(orderResponse.getId(),"Order.id not null");
        Assertions.assertNotNull(orderResponse.getCreateTime(),"Order.creationTime not null");
    }

    @Test
    public void getOrderListTest(){

    }

    @Test
    public void getOrderListPaginationTest(){


    }

    @Test
    public void getOrderByIdTest(){
        OrderResponse order = createOrder("Bangalore", "Delhi", 1);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format(ORDER_BY_ID,order.getId()))
                .then()
                .statusCode(200)
                .extract().response();
        OrderResponse orderResponse = from(response.asString()).getObject("", OrderResponse.class);
        assertOrder(order,orderResponse);

        // not found
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format(ORDER_BY_ID,"random"))
                .then()
                .statusCode(404);
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format(ORDER_BY_ID,"23456789"))
                .then()
                .statusCode(404);

    }

    @Test
    public void updateOrderTest(){
        // test flow PLACED --> DECLINED
        OrderResponse orderResponse = createOrder("Mumbai", "West Bengal", 5);

        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"destination\":\"Goa\",\"status\":\"DELIVERED\"}")
                .when()
                .put(String.format(ORDER_BY_ID, orderResponse.getId()))
                .then()
                .statusCode(400); // bad request

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"destination\":\"Goa\",\"status\":\"DECLINED\"}")
                .when()
                .put(String.format(ORDER_BY_ID, orderResponse.getId()))
                .then()
                .statusCode(200)
                .extract().response();

        OrderResponse declinedOrderResponse = from(response.asString()).getObject("", OrderResponse.class);
        Assertions.assertEquals(orderResponse.getId(), declinedOrderResponse.getId(),"Order.id compare");
        Assertions.assertEquals(OrderStatus.DECLINED, declinedOrderResponse.getStatus(),"Order.status declined compare");

        // test flow PLACED --> IN_TRANSIT --> DELIVERED

        orderResponse = createOrder("Mumbai", "West Bengal", 5);

    }

    @Test
    public void deleteOrderTest(){
        OrderResponse orderResponse = createOrder("Delhi", "Bangalore", 2);

        // not found
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format(ORDER_BY_ID,"random"))
                .then()
                .statusCode(404);
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format(ORDER_BY_ID,"23456789"))
                .then()
                .statusCode(404);

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format(ORDER_BY_ID, orderResponse.getId()))
                .then()
                .statusCode(200)
                .extract().response();
    }

    private OrderResponse createOrder(String source, String destination, Integer noOfPkgs){
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(String.format("{\"source\":\"%s\",\"destination\":\"%s\",\"noOfPkgs\":%d}",source,destination,noOfPkgs))
                .when()
                .post(BASE_ORDER_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();
        OrderResponse orderResponse = from(response.asString()).getObject("", OrderResponse.class);
        Assertions.assertEquals(orderResponse.getSource(),source,"Order.source compare");
        Assertions.assertEquals(orderResponse.getDestination(),destination,"Order.destination compare");
        Assertions.assertEquals(orderResponse.getStatus(), OrderStatus.PLACED,"Order.status compare");
        Assertions.assertEquals(orderResponse.getNoOfPkgs(),noOfPkgs,"Order.noOfPkgs compare");
        return orderResponse;
    }

    private void assertOrder(OrderResponse expected, OrderResponse actual ){
        Assertions.assertEquals(expected.getSource(),actual.getSource(),"Order.source compare");
        Assertions.assertEquals(expected.getDestination(),actual.getDestination(),"Order.destination compare");
        Assertions.assertEquals(expected.getStatus(), actual.getStatus(),"Order.status compare");
        Assertions.assertEquals(expected.getNoOfPkgs(),actual.getNoOfPkgs(),"Order.noOfPkgs compare");
        Assertions.assertEquals(expected.getWeight(),actual.getWeight(),"Order.weight compare");
        Assertions.assertEquals(expected.getVolume(), actual.getVolume(),"Order.volume compare");
        Assertions.assertEquals(expected.getId(),actual.getId(),"Order.id compare");
        Assertions.assertEquals(expected.getCreateTime(),actual.getCreateTime(),"Order.creationTime compare");
    }

}
