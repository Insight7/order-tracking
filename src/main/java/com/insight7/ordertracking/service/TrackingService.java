package com.insight7.ordertracking.service;

import com.insight7.ordertracking.persistence.models.Order;
import com.insight7.ordertracking.persistence.models.Tracking;
import com.insight7.ordertracking.persistence.repository.OrderRepository;
import com.insight7.ordertracking.persistence.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository repository;

    public Iterable<Tracking> findAll(){
        return repository.findAll();
    };
}
