package ru.tyulenev.cars_garage_distributor.service;

import ru.tyulenev.cars_garage_distributor.model.entity.Car;
import ru.tyulenev.cars_garage_distributor.model.entity.Garage;
import ru.tyulenev.cars_garage_distributor.model.dto.GarageWithCarsForTransfer;

import java.util.List;

public interface CarsGarageService {
//    Cars
    public List<Car> getAllCars();
    public Car getCarById(Long id);
    public Car saveCar(Car carForSave);

//    Garages
    public List<Garage> getAllGarages();
    public GarageWithCarsForTransfer getGarageByIdWithCars(Long id);
    public Garage saveGarage(Garage garageForSave);

}
