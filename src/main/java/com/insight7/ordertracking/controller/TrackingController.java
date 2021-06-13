package com.insight7.ordertracking.controller;

import com.insight7.ordertracking.controller.models.CreateTrackingRequest;
import com.insight7.ordertracking.controller.models.MessageResponse;
import com.insight7.ordertracking.controller.models.OrderResponse;
import com.insight7.ordertracking.controller.models.TrackingResponse;
import com.insight7.ordertracking.controller.models.TrackingsResponse;
import com.insight7.ordertracking.exception.NotFoundException;
import com.insight7.ordertracking.service.TrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static com.insight7.ordertracking.controller.OrderController.validateOrderId;

@Controller
@Slf4j
public class TrackingController {

    @Autowired
    private TrackingService service;

    @GetMapping("/tracking/{orderId}")
    public TrackingsResponse getTrackings(@PathVariable String orderId) {
        //TODO: implement this method
        Integer parsedOrderId = validateOrderId(orderId);
        Pair<List<TrackingResponse>, Optional<OrderResponse>> allTrackingByOrder = service.findAllTrackingByOrder(parsedOrderId);
        Optional<OrderResponse> optionalOrderResponse = allTrackingByOrder.getSecond();
        if (optionalOrderResponse.isEmpty()) {
            throw new NotFoundException(String.format("No Tracking found for order Id = %s", orderId));
        }
        return TrackingsResponse.builder()
                .order(allTrackingByOrder.getSecond().get())
                .items(allTrackingByOrder.getFirst())
                .build();
    }

    @PostMapping("/tracking/{orderId}")
    public TrackingResponse createTracking(@PathVariable Integer orderId, @RequestBody CreateTrackingRequest request) {
        //TODO: implement this method
        return null;
    }

    @GetMapping("/tracking/{orderId}/{trackingId}")
    public TrackingResponse getTracking(@PathVariable Integer orderId, @PathVariable Integer trackingId) {
        //TODO: implement this method
        return null;
    }

    @PutMapping("/tracking/{orderId}/{trackingId}")
    public TrackingResponse updateTracking(@PathVariable Integer orderId, @PathVariable Integer trackingId, @RequestBody CreateTrackingRequest request) {
        //TODO: implement this method
        return null;
    }

    @DeleteMapping("/tracking/{orderId}/{trackingId}")
    public MessageResponse deleteTracking(@PathVariable Integer orderId, @PathVariable Integer trackingId) {
        //TODO: implement this method
        return null;
    }
}
