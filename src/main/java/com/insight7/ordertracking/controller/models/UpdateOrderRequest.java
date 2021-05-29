package com.insight7.ordertracking.controller.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateOrderRequest {
    private String destination;
    private OrderStatus status;
    private Integer noOfPkgs;
    private Integer weight;
    private Integer volume;
}
