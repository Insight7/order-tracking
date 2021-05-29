package com.insight7.ordertracking.controller.models;

public enum OrderStatus {

    PLACED("PLACED"),
    APPROVED("APPROVED"),
    IN_TRANSIT("IN_TRANSIT"),
    DELIVERED("DELIVERED");

    private final String value;

    OrderStatus(String status) {
        this.value = status;
    }

    @Override
    public String toString(){return this.value;}

}
