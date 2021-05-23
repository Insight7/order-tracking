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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "TRACKING", schema="public")
@ToString
public class Tracking {

    public static enum TRACKING_STATUS {TRACKING_STATUS, IN_TRANSIT, DELIVERED};

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TRACKING_STATUS status;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "MESSAGE")
    private String message;

    @ManyToOne
    //@JoinColumn(name = "ID")
    private Order order;
}
