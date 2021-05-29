package com.insight7.ordertracking.controller;

import com.insight7.ordertracking.controller.models.CreateTrackingRequest;
import com.insight7.ordertracking.controller.models.Message;
import com.insight7.ordertracking.controller.models.Tracking;
import com.insight7.ordertracking.controller.models.Trackings;
import com.insight7.ordertracking.service.TrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class TrackingController {

    @Autowired
    private TrackingService service;

    @GetMapping("/tracking/{orderId}")
    public Trackings getTrackings(@PathVariable Integer orderId){
        //TODO: implement this method
        return null;
    }

    @PostMapping("/tracking/{orderId}")
    public Tracking createTracking(@PathVariable Integer orderId, @RequestBody CreateTrackingRequest request){
        //TODO: implement this method
        return null;
    }

    @GetMapping("/tracking/{orderId}/{trackingId}")
    public Tracking getTracking(@PathVariable Integer orderId, @PathVariable Integer trackingId){
        //TODO: implement this method
        return null;
    }

    @PutMapping("/tracking/{orderId}/{trackingId}")
    public Tracking updateTracking(@PathVariable Integer orderId, @PathVariable Integer trackingId, @RequestBody CreateTrackingRequest request){
        //TODO: implement this method
        return null;
    }

    @DeleteMapping("/tracking/{orderId}/{trackingId}")
    public Message deleteTracking(@PathVariable Integer orderId, @PathVariable Integer trackingId){
        //TODO: implement this method
        return null;
    }
}
