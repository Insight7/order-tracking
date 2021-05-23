package com.insight7.ordertracking.service;

import com.insight7.ordertracking.persistence.models.Order;
import com.insight7.ordertracking.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Iterable<Order> findAll(){
        return repository.findAll();
    };
}
