package com.insight7.ordertracking.controller.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tracking {
    private Integer id;
    private String updateTime;
    private String location;
    private String message;
}
