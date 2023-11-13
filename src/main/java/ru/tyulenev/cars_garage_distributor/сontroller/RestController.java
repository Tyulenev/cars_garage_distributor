package ru.tyulenev.cars_garage_distributor.сontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import ru.tyulenev.cars_garage_distributor.exceptions.CarDataNotValidException;
import ru.tyulenev.cars_garage_distributor.model.entity.Car;
import ru.tyulenev.cars_garage_distributor.model.entity.Garage;
import ru.tyulenev.cars_garage_distributor.service.CarsGarageService;
import ru.tyulenev.cars_garage_distributor.model.dto.GarageWithCarsForTransfer;


import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private CarsGarageService carsGarageService;

    @GetMapping("/car")
    public List<Car> getAllCars() {
        return carsGarageService.getAllCars();
    }

    @GetMapping("/car/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carsGarageService.getCarById(id);
    }

    @PostMapping("/car")
    public Car addNewCar(@RequestBody Car carForSave) throws CarDataNotValidException {
            return  carsGarageService.saveCar(carForSave);
    }

    @GetMapping("/garage")
    public List<Garage> getAllGarages() {
        return carsGarageService.getAllGarages();
    }

    @GetMapping("/garage/{garageId}")
    public GarageWithCarsForTransfer getAllGarages(@PathVariable Long garageId) {
        return carsGarageService.getGarageByIdWithCars(garageId);
    }

    @PostMapping("/garage")
    public Garage addNewGarage(@RequestBody Garage garageForSave) {
        return carsGarageService.saveGarage(garageForSave);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.TOO_EARLY)
//    public Response handleException(MethodArgumentNotValidException e) {
//        return new Response();
//    }
}
