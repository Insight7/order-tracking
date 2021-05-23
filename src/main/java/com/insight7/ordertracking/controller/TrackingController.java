package com.insight7.ordertracking.controller;

import com.insight7.ordertracking.persistence.models.Order;
import com.insight7.ordertracking.persistence.models.Tracking;
import com.insight7.ordertracking.service.OrderService;
import com.insight7.ordertracking.service.TrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TrackingController {

    @Autowired
    private TrackingService service;

    @GetMapping("/tracking")
    public String getOrders(){
        Iterable<Tracking> orders = service.findAll();
        orders.forEach(orders1 -> {
            log.info("TRACKING: {}",orders1);
        });
        return "All orders";
    }
}
