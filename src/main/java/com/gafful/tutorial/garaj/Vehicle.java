package com.gafful.tutorial.garaj;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String registrationNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "vehicle_slot",
            joinColumns =
                    {@JoinColumn(name = "vehicle_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "slot_id", referencedColumnName = "id")})
    private Slot slot;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Driver driver;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id) &&
                Objects.equals(registrationNumber, vehicle.registrationNumber)
                && Objects.equals(slot, vehicle.slot) &&
                Objects.equals(driver, vehicle.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationNumber, slot, driver);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", slot=" + slot +
                ", driver=" + driver +
                '}';
    }
}
