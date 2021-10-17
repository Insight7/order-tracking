package com.insight7.ordertracking.service;

import com.insight7.ordertracking.controller.models.OrderResponse;
import com.insight7.ordertracking.exception.NotFoundException;
import com.insight7.ordertracking.persistence.models.Order;
import com.insight7.ordertracking.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.insight7.ordertracking.controller.models.OrderStatus.fromDbOrderStatus;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public static OrderResponse orderToResponseModel(Order order) {
        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .source(order.getSource())
                .destination(order.getDestination())
                .status(fromDbOrderStatus(order.getStatus()))
                .noOfPkgs(order.getNoOfPkgs())
                .weight(order.getWeight())
                .volume(order.getVolume())
                .build();
        if (order.getDeliveryTime() != null) {
            orderResponse.setDeliveryTime(order.getDeliveryTime().toString());
        }

        if (order.getUpdateTime() != null) {
            orderResponse.setUpdateTime(order.getUpdateTime().toString());
        }

        if (order.getCreateTime() != null) {
            orderResponse.setCreateTime(order.getCreateTime().toString());
        }

        return orderResponse;
    }

    public List<OrderResponse> findWithFilters(@Nullable String source, @Nullable String destination, @Nullable String status,
                                               @NonNull Integer limit, @NonNull Integer offset) {

        Iterable<Order> orderIterable = repository.findAll();
        List<OrderResponse> orderResponseList = new ArrayList<>();
        orderIterable.forEach(order -> {
            orderResponseList.add(orderToResponseModel(order));
        });
        return orderResponseList;
    }

    public OrderResponse createOrder(@NonNull String source, @NonNull String destination, @NonNull Order.ORDER_STATUS orderStatus,
                                     @NonNull Integer noOdPkgs, @Nullable Integer weight, @Nullable Integer volume) {
        Order orderPersistence = new Order();
        orderPersistence.setSource(source);
        orderPersistence.setDestination(destination);
        orderPersistence.setStatus(orderStatus);
        orderPersistence.setCreateTime(new Timestamp(System.currentTimeMillis()));
        orderPersistence.setDeliveryTime(null); //TODO: set delivery time
        orderPersistence.setNoOfPkgs(noOdPkgs);
        orderPersistence.setWeight(weight);
        orderPersistence.setVolume(volume);
        orderPersistence.setUpdateTime(null);
        return orderToResponseModel(repository.save(orderPersistence));
    }

    public OrderResponse getOrderById(@NonNull Integer orderId) {
        Optional<Order> optionalOrder = repository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new NotFoundException(String.format("Order not found for id = %d", orderId));
        }
        return orderToResponseModel(optionalOrder.get());
    }

    public void updateOrder(@NonNull String orderId, String location, String message){

    }

    public void deleteOrderById(@NonNull Integer orderId) {
        try {
            repository.deleteById(orderId);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException(String.format("Order not found for id = %d", orderId));
        }
    }
}
