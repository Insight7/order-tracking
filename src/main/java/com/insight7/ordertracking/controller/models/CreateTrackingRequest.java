package com.insight7.ordertracking.controller.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTrackingRequest {
    private String location;
    private String message;
}
