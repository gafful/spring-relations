package com.gafful.tutorial.garaj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void givenVehicles_whenFetchVehicles_thenReturnAllVehicles() {
        // given vehicle with slot
        Vehicle vehicleBo1 = new Vehicle("A 10");
        Slot slotBo1 = new Slot("A1", vehicleBo1);
        vehicleBo1.setSlot(slotBo1);

        // given vehicle without slot
        Vehicle vehicleBo2 = new Vehicle("A 11");

        entityManager.persist(vehicleBo1);
        entityManager.persist(vehicleBo2);
        entityManager.flush();

        // when fetch all vehicles
        List<Vehicle> vehicles = vehicleRepository.findAll();

        // then return all vehicles
        assertThat(vehicles.size())
                .isEqualTo(2);
        assertThat(vehicles.get(0).getRegistrationNumber())
                .isEqualTo(vehicleBo1.getRegistrationNumber());
        assertThat(vehicles.get(1).getRegistrationNumber())
                .isEqualTo(vehicleBo2.getRegistrationNumber());
    }

    @Test
    public void givenSomeVehiclesWithSlots_whenFetchVehiclesWithSlots_thenReturnVehiclesWithSlots() {
        // given vehicle with slot
        Vehicle vehicleBo1 = new Vehicle("A 10");
        Slot slotBo1 = new Slot("A1", vehicleBo1);
        vehicleBo1.setSlot(slotBo1);

        // given vehicle without slot
        Vehicle vehicleBo2 = new Vehicle("A 11");

        entityManager.persist(vehicleBo1);
        entityManager.persist(vehicleBo2);
        entityManager.flush();

        // when fetch vehicles with slots
        List<Vehicle> vehicles = vehicleRepository.findBySlotIsNotNull();

        // then return vehicles with slots
        assertThat(vehicles.size())
                .isEqualTo(1);
        assertThat(vehicles.get(0).getRegistrationNumber())
                .isEqualTo(vehicleBo1.getRegistrationNumber());
    }

    @Test
    public void givenSomeVehiclesWithoutSlots_whenFetchVehiclesWithoutSlots_thenReturnVehiclesWithoutSlots() {
        // given vehicle with slot
        Vehicle vehicleBo1 = new Vehicle("A 10");
        Slot slotBo1 = new Slot("A1", vehicleBo1);
        vehicleBo1.setSlot(slotBo1);

        // given vehicle without slot
        Vehicle vehicleBo2 = new Vehicle("A 11");

        entityManager.persist(vehicleBo1);
        entityManager.persist(vehicleBo2);
        entityManager.flush();

        // when fetch vehicles without slots
        List<Vehicle> vehicles = vehicleRepository.findBySlotIsNull();

        // then return vehicles without slots
        assertThat(vehicles.size())
                .isEqualTo(1);
        assertThat(vehicles.get(0).getRegistrationNumber())
                .isEqualTo(vehicleBo2.getRegistrationNumber());
    }

}
