package com.gafful.tutorial.garaj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    // Returns all slots whether vehicle is null or not
    List<Slot> findByVehicleIsNull();

    // Returns an empty list
    List<Slot> findByVehicleIsNotNull();
}
