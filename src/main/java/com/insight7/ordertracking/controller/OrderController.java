package com.insight7.ordertracking.controller;

import com.insight7.ordertracking.persistence.models.Order;
import com.insight7.ordertracking.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/orders")
    public String getOrders(){
        Iterable<Order> orders = service.findAll();
        orders.forEach(orders1 -> {
            log.info("ORDER: {}",orders1);
        });
        return "All orders";
    }
}
