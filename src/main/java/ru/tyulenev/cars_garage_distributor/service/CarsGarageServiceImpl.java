package ru.tyulenev.cars_garage_distributor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tyulenev.cars_garage_distributor.dao.CarRepository;
import ru.tyulenev.cars_garage_distributor.dao.GarageRepository;
import ru.tyulenev.cars_garage_distributor.model.entity.Car;
import ru.tyulenev.cars_garage_distributor.model.entity.Garage;
import ru.tyulenev.cars_garage_distributor.model.dto.GarageWithCarsForTransfer;

import java.util.List;
import java.util.Optional;

@Service
public class CarsGarageServiceImpl implements CarsGarageService{
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private GarageRepository garageRepository;

//    Cars
    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) return carOptional.get();
        else return null; //Throw Exception
    }

    @Override
    public void saveCar(Car carForSave) {
        carRepository.save(carForSave);
    }

//    Garage
    @Override
    public List<Garage> getAllGarages() {
        return  garageRepository.findAll();
    }

    @Override
    public GarageWithCarsForTransfer getGarageByIdWithCars(Long id) {
        Optional<Garage> garageOptional = garageRepository.findById(id);
        if (garageOptional.isPresent()) {
            List<Car> carsList = carRepository.findCarByGarageId(id);
            GarageWithCarsForTransfer garageWithCarsForTransfer = new GarageWithCarsForTransfer();
            garageWithCarsForTransfer.setId(id);
            garageWithCarsForTransfer.setName(garageOptional.get().getName());
            garageWithCarsForTransfer.setCarsList(carsList);
            return garageWithCarsForTransfer;
        }
        return null; //throw exception
    }

    @Override
    public void saveGarage(Garage garageForSave) {
        garageRepository.save(garageForSave);
    }


}
