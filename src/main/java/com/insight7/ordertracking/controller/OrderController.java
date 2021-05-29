package com.insight7.ordertracking.controller;

import com.insight7.ordertracking.controller.models.CreateOrderRequest;
import com.insight7.ordertracking.controller.models.Message;
import com.insight7.ordertracking.controller.models.Orders;
import com.insight7.ordertracking.persistence.models.Order;
import com.insight7.ordertracking.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/orders")
    public Orders getOrders(@RequestParam String source, @RequestParam String destination, @RequestParam Order.ORDER_STATUS status,
                            @RequestParam Integer limit, @RequestParam Integer offset){
        //TODO: implement this method
        return null;
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody CreateOrderRequest request){
        //TODO: implement this method
        return null;
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrder(@PathVariable Integer orderId){
        //TODO: implement this method
        return null;
    }

    @PutMapping("/orders/{orderId}")
    public Order updateOrder(@PathVariable Integer orderId){
        //TODO: implement this method
        return null;
    }

    @DeleteMapping("/orders/{orderId}")
    public Message deleteOrder(@PathVariable Integer orderId){
        //TODO: implement this method
        return null;
    }
}
