package com.insight7.ordertracking.controller.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    private Integer id;
    private String source;
    private String destination;
    private OrderStatus status;
    private String createTime;
    private String updateTime;
    private String deliveryTime;
    private Integer noOfPkgs;
    private Integer weight;
    private Integer volume;
}
