package com.insight7.ordertracking.controller.models;

import com.insight7.ordertracking.persistence.models.Order;

public enum OrderStatus {

    PLACED("PLACED"),
    DECLINED("DECLINED"),
    IN_TRANSIT("IN_TRANSIT"),
    DELIVERED("DELIVERED");

    private final String value;

    OrderStatus(String status) {
        this.value = status;
    }

    public static OrderStatus fromDbOrderStatus(Order.ORDER_STATUS status) {
        switch (status.name()) {
            case "PLACED":
                return PLACED;
            case "DECLINED":
                return DECLINED;
            case "IN_TRANSIT":
                return IN_TRANSIT;
            case "DELIVERED":
                return DELIVERED;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return this.value;
    }

    public Order.ORDER_STATUS toDbOrderStatus() {
        switch (this.toString()) {
            case "PLACED":
                return Order.ORDER_STATUS.PLACED;
            case "DECLINED":
                return Order.ORDER_STATUS.DECLINED;
            case "IN_TRANSIT":
                return Order.ORDER_STATUS.IN_TRANSIT;
            case "DELIVERED":
                return Order.ORDER_STATUS.DELIVERED;
            default:
                return null;
        }
    }

}
