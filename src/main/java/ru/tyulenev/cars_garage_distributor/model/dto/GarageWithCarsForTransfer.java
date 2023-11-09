package ru.tyulenev.cars_garage_distributor.model.dto;

import lombok.Data;
import ru.tyulenev.cars_garage_distributor.model.entity.Car;

import java.util.List;

@Data
public class GarageWithCarsForTransfer {
    Long id;
    String name;
    List<Car> carsList;
}
