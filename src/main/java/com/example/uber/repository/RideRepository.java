package com.example.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.uber.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Long> {

}