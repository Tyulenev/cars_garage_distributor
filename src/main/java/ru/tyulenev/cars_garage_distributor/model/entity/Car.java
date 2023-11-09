package ru.tyulenev.cars_garage_distributor.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity (name = "Car")
@Data
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "driver_name")
    String driverName;

    @Column(name = "brand")
    String brand;

    @Column(name = "garage_id")
    Long garageId;

}
