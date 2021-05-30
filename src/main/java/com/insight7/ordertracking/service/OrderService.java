package com.insight7.ordertracking.service;

import com.insight7.ordertracking.persistence.models.Order;
import com.insight7.ordertracking.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.insight7.ordertracking.controller.models.OrderStatus.fromDbOrderStatus;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;


    public List<com.insight7.ordertracking.controller.models.Order> findWithFilters(String source, String destination, String status, Integer limit, Integer offset ){

        Iterable<Order> orderIterable = repository.findAll();
        List<com.insight7.ordertracking.controller.models.Order> orderList = new ArrayList<>();
        orderIterable.forEach(order -> {
            orderList.add(orderToResponseModel(order));
        });
        return orderList;
    };

    public com.insight7.ordertracking.controller.models.Order createOrder(com.insight7.ordertracking.controller.models.Order order) {
        Order orderPersistence = new Order();
        orderPersistence.setSource(order.getSource());
        orderPersistence.setDestination(order.getDestination());
        orderPersistence.setStatus(order.getStatus().toDbOrderStatus());
        orderPersistence.setCreateTime(new Timestamp(System.currentTimeMillis()));
        orderPersistence.setDeliveryTime(null);
        orderPersistence.setNoOfPkgs(order.getNoOfPkgs());
        orderPersistence.setWeight(order.getWeight());
        orderPersistence.setVolume(order.getVolume());
        orderPersistence.setUpdateTime(null);
        return orderToResponseModel(repository.save(orderPersistence));
    }

    public com.insight7.ordertracking.controller.models.Order orderToResponseModel(Order order){
        com.insight7.ordertracking.controller.models.Order order1 = com.insight7.ordertracking.controller.models.Order.builder()
                .id(order.getId())
                .source(order.getSource())
                .destination(order.getDestination())
                .status(fromDbOrderStatus(order.getStatus()))
                .noOfPkgs(order.getNoOfPkgs())
                .weight(order.getWeight())
                .volume(order.getVolume())
                .build();
        if(order.getDeliveryTime() != null){
            order1.setDeliveryTime(order.getDeliveryTime().toString());
        }

        if(order.getUpdateTime() != null){
            order1.setUpdateTime(order.getUpdateTime().toString());
        }

        if(order.getCreateTime() != null){
            order1.setCreateTime(order.getCreateTime().toString());
        }

        return order1;
    }
}
