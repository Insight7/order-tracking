package com.insight7.ordertracking.persistence.repository;

import com.insight7.ordertracking.persistence.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
