package com.example.uber.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.uber.dto.CreateRideRequest;
import com.example.uber.dto.RideResponse;
import com.example.uber.entity.Ride;
import com.example.uber.entity.RideStatus;

@Service
public class RideService {

    private final Map<Long, Ride> rides = new HashMap<>();
    private Long nextId = 1L;

     public String getServiceStatus(){
        return "Ride service is working";
    }

    public RideResponse createRide(CreateRideRequest request){
        Ride ride = new Ride(nextId, request.getPickupLocation(), request.getDropoffLocation(), RideStatus.REQUESTED);

        rides.put(nextId, ride);
        nextId++;

        return new RideResponse(ride.getId(),ride.getPickupLocation(), ride.getDropoffLocation(), ride.getStatus());
    }

    public RideResponse getRideById(Long id){
        Ride ride = rides.get(id);

        if (ride == null) {
            throw new RuntimeException("Ride not found with id: " + id);
        }

        return new RideResponse(ride.getId(),ride.getPickupLocation(), ride.getDropoffLocation(), ride.getStatus());
    }

    public RideResponse assigRide(Long id){
        Ride ride = rides.get(id);

       if (ride == null) {
            throw new RuntimeException("Ride not found with id: " + id);
        }

        if (ride.getStatus() != RideStatus.REQUESTED){
            throw new RuntimeException("Only REQUESTED rides can be assigned");
        }

        ride.setStatus(RideStatus.ASSIGNED);
        return new RideResponse(ride.getId(),ride.getPickupLocation(), ride.getDropoffLocation(), ride.getStatus());
    }

    public RideResponse startRide(Long id) {
    Ride ride = rides.get(id);

    if (ride == null) {
        throw new RuntimeException("Ride not found with id: " + id);
    }

    if (ride.getStatus() != RideStatus.ASSIGNED) {
        throw new RuntimeException("Only ASSIGNED rides can be started");
    }

    ride.setStatus(RideStatus.IN_PROGRESS);
        return new RideResponse(ride.getId(),ride.getPickupLocation(), ride.getDropoffLocation(), ride.getStatus());
    }

    public RideResponse completeRide(Long id) {
        Ride ride = rides.get(id);

        if (ride == null) {
            throw new RuntimeException("Ride not found with id: " + id);
        }

        if (ride.getStatus() != RideStatus.IN_PROGRESS) {
            throw new RuntimeException("Only IN_PROGRESS rides can be completed");
        }

        ride.setStatus(RideStatus.COMPLETED);
            return new RideResponse(ride.getId(),ride.getPickupLocation(), ride.getDropoffLocation(), ride.getStatus());
    }
}
