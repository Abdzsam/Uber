package com.example.uber.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.uber.dto.CreateRideRequest;
import com.example.uber.dto.RideResponse;
import com.example.uber.service.RideService;

@RestController
@RequestMapping("/rides")
public class RideController {

   private final RideService rideService;

   public RideController(RideService rideService){
        this.rideService = rideService;
   }

   @GetMapping("/test")
   public String testRideApi(){
    return rideService.getServiceStatus();
   }

   @PostMapping
   public RideResponse createRide(@RequestBody CreateRideRequest request){
    return rideService.createRide(request);
   }

   @GetMapping("/{id}")
   public RideResponse getRideById(@PathVariable Long id){
    return rideService.getRideById(id);
   }

   @PatchMapping("/{id}/assign")
   public RideResponse assignRide(@PathVariable Long id){
      return rideService.assigRide(id);
   }

   @PatchMapping("/{id}/start")
   public RideResponse startRide(@PathVariable Long id) {
      return rideService.startRide(id);
   }

   @PatchMapping("/{id}/complete")
   public RideResponse completeRide(@PathVariable Long id) {
      return rideService.completeRide(id);
   }
    
}
