package com.example.uber.dto;

import com.example.uber.entity.RideStatus;

public class RideResponse {

    private Long id;
    private String pickupLocation;
    private String dropoffLocation;
    private RideStatus status;

    public RideResponse() {
    }

    public RideResponse(Long id, String pickupLocation, String dropoffLocation, RideStatus status) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }
}
