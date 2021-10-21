package com.gafful.tutorial.garaj;

import javax.persistence.*;

@Entity
@Table(name = "slots")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToOne(mappedBy = "slot")
    private Vehicle vehicle;


    public Slot(String name) {
        this.name = name;
    }

    public Slot(String name, Vehicle vehicle) {
        this.name = name;
        this.vehicle = vehicle;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", vehicle=" + vehicle +
                '}';
    }
}
