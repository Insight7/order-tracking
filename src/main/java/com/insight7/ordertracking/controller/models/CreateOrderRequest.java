package com.insight7.ordertracking.controller.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderRequest {
    private String destination;
    private String source;
    private Integer noOfPkgs;
    private Integer weight;
    private Integer volume;
}
