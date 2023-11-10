package ru.tyulenev.cars_garage_distributor.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tyulenev.cars_garage_distributor.dao.CarRepository;
import ru.tyulenev.cars_garage_distributor.dao.GarageRepository;
import ru.tyulenev.cars_garage_distributor.exceptions.GarageIdNotFoundException;
import ru.tyulenev.cars_garage_distributor.model.entity.Car;

import java.util.stream.Collectors;

@Component
@Aspect
public class CarValidateAspect {
    @Autowired
    CarRepository carRepository;
    @Autowired
    GarageRepository garageRepository;

//    @Pointcut("execution(public void addNewCar(*))")
//    private void addCarMethod() {}

    @Before("ru.tyulenev.cars_garage_distributor.aspects.CustomPointcuts.addCarMethod()")
    public void beforeCarAddCheckGarageIdAdvice(JoinPoint joinPoint) throws GarageIdNotFoundException {
        System.out.println("beforeCarAddCheckGarageIdAdvice: added car valid");
        Object[] arguments = joinPoint.getArgs();
        for (Object obj:arguments) {
            if (obj instanceof Car) {
                Car car = (Car) obj;
                Long newCarGarageId = car.getGarageId();
                System.out.println("Garage ID = " + car.getGarageId());
                if (garageRepository.findAll().stream()
                        .filter(cars -> {
                            return (cars.getId() == newCarGarageId);
                        })
                        .collect(Collectors.toList())
                        .size()==0) {
                    throw new GarageIdNotFoundException("Garage with id = " + newCarGarageId
                            + " not exist");
                }
            }
        }
    }

//    @Before("execution(public void addNewCar(*))")
    @Before("ru.tyulenev.cars_garage_distributor.aspects.CustomPointcuts.addCarMethod()")
    public void beforeCarAddCheckCountCarInGarageAdvice(){
        System.out.println("beforeCarAddCheckCountCarInGarageAdvice: ");
        System.out.println("Total cars" + carRepository.findAll().size());
    }
}
