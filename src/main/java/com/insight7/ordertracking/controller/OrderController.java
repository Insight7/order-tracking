package com.insight7.ordertracking.controller;

import com.insight7.ordertracking.controller.models.CreateOrderRequest;
import com.insight7.ordertracking.controller.models.Message;
import com.insight7.ordertracking.controller.models.OrderStatus;
import com.insight7.ordertracking.controller.models.Orders;
import com.insight7.ordertracking.controller.models.Pagination;
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

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/orders")
    public Orders getOrders(@RequestParam(required = false) String source, @RequestParam(required = false) String destination, @RequestParam(required = false) Order.ORDER_STATUS status,
                            @RequestParam(defaultValue = "20") Integer limit, @RequestParam(defaultValue = "0") Integer offset){
        //TODO: implement filters
        List<com.insight7.ordertracking.controller.models.Order> orderList = service.findWithFilters(source, destination, null, limit, offset);
        Orders orders = Orders.builder()
                .items(orderList)
                .pagination(Pagination.builder()
                        .offset(offset)
                        .limit(limit)
                        .count(orderList.size())
                        //TODO: set total
                        .build()
                )
                .build();
        return orders;
    }

    @PostMapping("/orders")
    public com.insight7.ordertracking.controller.models.Order createOrder(@RequestBody CreateOrderRequest request){
        com.insight7.ordertracking.controller.models.Order order = com.insight7.ordertracking.controller.models.Order.builder()
                .source(request.getSource())
                .destination(request.getDestination())
                .noOfPkgs(request.getNoOfPkgs())
                .weight(request.getWeight())
                .status(OrderStatus.PLACED)
                .volume(request.getVolume())
                .build();

        return service.createOrder(order);
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
