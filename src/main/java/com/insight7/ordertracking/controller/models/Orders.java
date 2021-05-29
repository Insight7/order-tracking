package com.insight7.ordertracking.controller.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Orders {
    private Pagination pagination;
    private List<Order> items;
}
