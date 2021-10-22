package com.gafful.tutorial.garaj;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vehicle> vehicles = new HashSet<>();


    public Driver(String name) {
        this.name = name;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        vehicle.setDriver(this);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
        vehicle.setDriver(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", vehicles=" + vehicles +
                '}';
    }
}
