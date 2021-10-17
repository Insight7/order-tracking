package com.insight7.ordertracking.service;

import com.insight7.ordertracking.controller.models.OrderResponse;
import com.insight7.ordertracking.controller.models.TrackingResponse;
import com.insight7.ordertracking.exception.NotFoundException;
import com.insight7.ordertracking.persistence.models.Tracking;
import com.insight7.ordertracking.persistence.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository repository;

    public Pair<List<TrackingResponse>, Optional<OrderResponse>> findAllTrackingByOrder(@NonNull Integer orderId) {
        List<Tracking> trackingIterable = repository.findByOrderId(orderId);
        List<TrackingResponse> trackingResponseList = new ArrayList<>();
        trackingIterable.forEach(tracking -> {
            trackingResponseList.add(trackingToResponseModel(tracking));
        });
        OrderResponse orderResponse = null;
        if (trackingIterable.size() > 0) {
            orderResponse = OrderService.orderToResponseModel(trackingIterable.get(0).getOrder());
        }
        return Pair.of(trackingResponseList, Optional.ofNullable(orderResponse));
    }

    private TrackingResponse trackingToResponseModel(Tracking tracking) {

        TrackingResponse response = TrackingResponse.builder()
                .id(tracking.getId())
                .message(tracking.getMessage())
                .location(tracking.getLocation())
                .build();
        if (tracking.getUpdateTime() != null) {
            response.setUpdateTime(tracking.getUpdateTime().toString());
        }
        return response;
    }

    public void deleteTrackingAndOrderById(@NonNull Integer orderId, @NonNull Integer trackingId) {
        try {
            repository.deleteById(trackingId);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException(String.format("Order not found for orderId = %d and trackingId", orderId, trackingId));
        }
    }

}
