package com.insight7.ordertracking.persistence.repository;

import com.insight7.ordertracking.persistence.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
