package com.insight7.ordertracking.controller.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pagination {
    private Integer count;
    private Integer limit;
    private Integer offset;
    private Integer total;
}
