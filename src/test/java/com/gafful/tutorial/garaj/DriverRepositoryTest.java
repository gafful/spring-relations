package com.gafful.tutorial.garaj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DriverRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DriverRepository driverRepository;

    @Test
    public void givenDrivers_whenFetchDrivers_thenReturnAllDrivers() {
        // given driver with vehicle
        Driver driverBo1 = new Driver("D1");
        Vehicle vehicleBo1 = new Vehicle("V1");
        Vehicle vehicleBo11 = new Vehicle("V11");
        // driverBo1.setVehicles(new HashSet<>(List.of(vehicleBo1, vehicleBo11)));
        driverBo1.addVehicle(vehicleBo1);
        driverBo1.addVehicle(vehicleBo11);

        // given driver without vehicle
        Driver driverBo2 = new Driver("D2");

        entityManager.persist(driverBo1);
        entityManager.persist(driverBo2);
        entityManager.flush();

        // when fetch all drivers
        List<Driver> drivers = driverRepository.findAll();

        for (Driver driver : drivers) {
            System.out.println(driver);
        }

        // then return all drivers
        assertThat(drivers.size())
                .isEqualTo(2);
        assertThat(drivers.get(0).getName())
                .isEqualTo(driverBo1.getName());
        assertThat(drivers.get(1).getName())
                .isEqualTo(driverBo2.getName());
    }

}
