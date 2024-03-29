package com.gafful.tutorial.garaj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT v FROM Vehicle v WHERE slot IS NOT NULL")
    List<Vehicle> findBySlotIsNotNull();

    @Query(value = "SELECT v FROM Vehicle v WHERE slot IS NULL")
    List<Vehicle> findBySlotIsNull();

    @Query(value = "SELECT v FROM Vehicle v WHERE v.driver.id = :id")
    List<Vehicle> findAllVehiclesByDriverId(@Param("id") long id);
}
