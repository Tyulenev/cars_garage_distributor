package ru.tyulenev.cars_garage_distributor.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.tyulenev.cars_garage_distributor.dao.CarRepository;
import ru.tyulenev.cars_garage_distributor.dao.GarageRepository;
import ru.tyulenev.cars_garage_distributor.exceptions.CarDataNotValidException;
import ru.tyulenev.cars_garage_distributor.exceptions.GarageIdNotFoundException;
import ru.tyulenev.cars_garage_distributor.exceptions.GarageIsFullException;
import ru.tyulenev.cars_garage_distributor.model.entity.Car;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Aspect
public class CarValidateAspect {
    @Autowired
    CarRepository carRepository;
    @Autowired
    GarageRepository garageRepository;
    @Value("${app.params.maxCarsInGarage}")
    Integer maxCountCarsInGarage;

//    Validation 1
    @Before("ru.tyulenev.cars_garage_distributor.aspects.CustomPointcuts.addCarMethod()")
    public void beforeCarAddCheckBrandAndDrNameIsLetter(JoinPoint joinPoint)
        throws CarDataNotValidException {
    final String regExp = "^[a-zA-Z]*$"; //Только буквы латиница
    Object[] arguments = joinPoint.getArgs();
    for (Object obj : arguments) {
        if (obj instanceof Car) {
            Car car = (Car) obj;
            boolean brandIsOk = car.getBrand().matches(regExp);
            boolean driverNameIsOk = car.getDriverName().matches(regExp);
            if (!brandIsOk && !driverNameIsOk)
                throw new CarDataNotValidException("Brand and driverName is not valid");
            else if (brandIsOk && !driverNameIsOk)
                throw new CarDataNotValidException("DriverName is not valid");
            else if (!brandIsOk && driverNameIsOk)
                throw new CarDataNotValidException("Brand is not valid");
        }
    }

}

//    Validation 2
    @Before("ru.tyulenev.cars_garage_distributor.aspects.CustomPointcuts.addCarMethod()")
    public void beforeCarAddCheckGarageIdAdvice(JoinPoint joinPoint) throws GarageIdNotFoundException {
        Object[] arguments = joinPoint.getArgs();
        for (Object obj : arguments) {
            if (obj instanceof Car) {
                Car car = (Car) obj;
                Long newCarGarageId = car.getGarageId();
                if (!garageRepository.existsById(newCarGarageId))
                    throw new GarageIdNotFoundException("Garage with id = " + newCarGarageId
                            + " not exist");
            }
        }
    }

    //    Validation 3
    @Before("ru.tyulenev.cars_garage_distributor.aspects.CustomPointcuts.addCarMethod()")
    public void beforeCarAddCheckCountCarInGarageAdvice(JoinPoint joinPoint) throws GarageIsFullException {
//        System.out.println("props param maxCountCarsInGarage = " + maxCountCarsInGarage);
        Object[] arguments = joinPoint.getArgs();
        for (Object obj : arguments) {
            if (obj instanceof Car) {
                Car car = (Car) obj;
                Long newCarGarageId = car.getGarageId();
                List<Car> carsList = carRepository.findCarByGarageId(newCarGarageId);
                if (carsList.size()>=maxCountCarsInGarage)
                    throw new GarageIsFullException("Garage with id = " + newCarGarageId +
                            " is full. Impossible to add new car.");
            }
        }
    }
}
