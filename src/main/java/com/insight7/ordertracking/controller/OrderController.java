package com.insight7.ordertracking.controller;

import com.insight7.ordertracking.controller.models.CreateOrderRequest;
import com.insight7.ordertracking.controller.models.MessageResponse;
import com.insight7.ordertracking.controller.models.OrderResponse;
import com.insight7.ordertracking.controller.models.OrdersResponse;
import com.insight7.ordertracking.controller.models.Pagination;
import com.insight7.ordertracking.exception.BadRequestException;
import com.insight7.ordertracking.exception.NotFoundException;
import com.insight7.ordertracking.persistence.models.Order;
import com.insight7.ordertracking.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService service;

    public static Integer validateOrderId(String orderId) {
        try {
            return Integer.parseInt(orderId);
        } catch (NumberFormatException exception) {
            // In case of Invalid order Id, just say that order not found
            throw new NotFoundException(String.format("Order not found for id = %s. Invalid order id", orderId));
        }
    }

    @GetMapping("/orders")
    public OrdersResponse getOrders(@RequestParam(required = false) String source, @RequestParam(required = false) String destination, @RequestParam(required = false) Order.ORDER_STATUS status,
                                    @RequestParam(defaultValue = "20") Integer limit, @RequestParam(defaultValue = "0") Integer offset) {
        //TODO: implement filters
        List<OrderResponse> orderResponseList = service.findWithFilters(source, destination, null, limit, offset);
        return OrdersResponse.builder()
                .items(orderResponseList)
                .pagination(Pagination.builder()
                        .offset(offset)
                        .limit(limit)
                        .count(orderResponseList.size())
                        //TODO: set total
                        .build()
                )
                .build();
    }

    @PostMapping("/orders")
    public OrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        //validate the request param
        String source = request.getSource();
        String destination = request.getDestination();
        Integer noOfPkgs = request.getNoOfPkgs();
        Integer weight = request.getWeight();
        Integer volume = request.getVolume();
        if (source == null || source.isEmpty() || source.trim().isEmpty()) {
            throw new BadRequestException("Source can not be empty.");
        }
        if (destination == null || destination.isEmpty() || destination.trim().isEmpty()) {
            throw new BadRequestException("Destination can not be empty.");
        }
        if (noOfPkgs == null || noOfPkgs <= 0) {
            throw new BadRequestException("Number of packages needs to be positive integer.");
        }
        if (weight != null && weight <= 0) {
            throw new BadRequestException("Weight needs to be positive integer.");
        }
        if (volume != null && volume <= 0) {
            throw new BadRequestException("Volume needs to be positive integer.");
        }

        return service.createOrder(source, destination, Order.ORDER_STATUS.PLACED, request.getNoOfPkgs(), request.getWeight(), request.getVolume());
    }

    @GetMapping("/orders/{orderId}")
    public OrderResponse getOrder(@PathVariable String orderId) {
        return service.getOrderById(validateOrderId(orderId));
    }

    @PutMapping("/orders/{orderId}")
    public OrderResponse updateOrder(@PathVariable String orderId) {
        //TODO: implement this method
        return null;
    }

    @DeleteMapping("/orders/{orderId}")
    public MessageResponse deleteOrder(@PathVariable String orderId) {
        service.deleteOrderById(validateOrderId(orderId));
        return MessageResponse.builder()
                .statusCode(HttpStatus.OK.toString())
                .message(String.format("Order with orderId = %s is deleted.", orderId))
                .build();
    }
}
