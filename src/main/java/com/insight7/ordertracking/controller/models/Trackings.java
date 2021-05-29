package com.insight7.ordertracking.controller.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trackings {
    private Pagination pagination;
    private Order order;
    private List<Tracking> items;
}
