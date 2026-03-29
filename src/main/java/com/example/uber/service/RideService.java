package com.example.uber.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.uber.dto.CreateRideRequest;
import com.example.uber.dto.RideResponse;
import com.example.uber.entity.Ride;
import com.example.uber.entity.RideStatus;
import com.example.uber.repository.RideRepository;

import com.example.uber.messaging.RideEventProducer;


@Service
public class RideService {

    private final RideRepository rideRepository;
    private final RideEventProducer rideEventProducer;

    public RideService(RideRepository rideRepository, RideEventProducer rideEventProducer) {
        this.rideRepository = rideRepository;
        this.rideEventProducer = rideEventProducer;
    }

     public String getServiceStatus(){
        return "Ride service is working";
    }

    public RideResponse createRide(CreateRideRequest request){
        Ride ride = new Ride();
        ride.setPickupLocation(request.getPickupLocation());
        ride.setDropoffLocation(request.getDropoffLocation());
        ride.setStatus(RideStatus.REQUESTED);

        Ride savedRide = rideRepository.save(ride);
        rideEventProducer.sendRideCreatedEvent(
        "Ride created with id: " + savedRide.getId());

        return new RideResponse(savedRide.getId(),savedRide.getPickupLocation(), savedRide.getDropoffLocation(), savedRide.getStatus());
    }

    public RideResponse getRideById(Long id){
      Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));

        if (ride == null) {
            throw new RuntimeException("Ride not found with id: " + id);
        }

        return new RideResponse(ride.getId(),ride.getPickupLocation(), ride.getDropoffLocation(), ride.getStatus());
    }

    public RideResponse assignRide(Long id){
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));

        if (ride.getStatus() != RideStatus.REQUESTED) {
            throw new RuntimeException("Only REQUESTED rides can be assigned");
        }

        ride.setStatus(RideStatus.ASSIGNED);
        Ride savedRide = rideRepository.save(ride);

        return new RideResponse(savedRide.getId(),savedRide.getPickupLocation(), savedRide.getDropoffLocation(), savedRide.getStatus());
    }

    public RideResponse startRide(Long id) {
    Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));

        if (ride.getStatus() != RideStatus.ASSIGNED) {
            throw new RuntimeException("Only ASSIGNED rides can be started");
        }

        ride.setStatus(RideStatus.IN_PROGRESS);
        Ride savedRide = rideRepository.save(ride);

        return new RideResponse(savedRide.getId(),savedRide.getPickupLocation(), savedRide.getDropoffLocation(), savedRide.getStatus());
    }

    public RideResponse completeRide(Long id) {
       Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));

        if (ride.getStatus() != RideStatus.IN_PROGRESS) {
            throw new RuntimeException("Only IN_PROGRESS rides can be completed");
        }

        ride.setStatus(RideStatus.COMPLETED);
        Ride savedRide = rideRepository.save(ride);

        return new RideResponse(savedRide.getId(),savedRide.getPickupLocation(), savedRide.getDropoffLocation(), savedRide.getStatus());
    }
}
