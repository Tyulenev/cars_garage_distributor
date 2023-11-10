package ru.tyulenev.cars_garage_distributor.model.entity;

import lombok.Data;
import org.intellij.lang.annotations.RegExp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity (name = "Car")
@Data
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "driver_name")
//    @Pattern(regexp = "[a-zA-Z]")
    String driverName;

    @Column(name = "brand")
    String brand;

    @Column(name = "garage_id")
    Long garageId;

}
