package ru.tyulenev.cars_garage_distributor.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "garage")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    String name;
}
