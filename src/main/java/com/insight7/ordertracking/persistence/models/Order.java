package com.insight7.ordertracking.persistence.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "ORDERS", schema="public")
@ToString
public class Order {

    public static enum ORDER_STATUS {ORDERED, IN_TRANSIT, DELIVERED};

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ORDER_STATUS status;

    @Column(name = "DELIVERY_TIME")
    private Timestamp deliveryTime;

    @Column(name = "NO_OF_PKGS")
    private Integer noOfPkgs;

    @Column(name = "WEIGHT")
    private Integer WEIGHT;

    @Column(name = "VOLUME")
    private Integer volume;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

}
