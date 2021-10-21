package com.gafful.tutorial.garaj;

import javax.persistence.*;

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


    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", slot=" + slot +
                '}';
    }
}
