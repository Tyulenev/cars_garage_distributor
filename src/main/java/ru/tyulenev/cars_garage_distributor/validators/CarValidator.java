package ru.tyulenev.cars_garage_distributor.validators;

import ru.tyulenev.cars_garage_distributor.exceptions.CarDataNotValidException;
import ru.tyulenev.cars_garage_distributor.model.entity.Car;

public class CarValidator {
    private Car car;

    public CarValidator(Car car) {
        this.car = car;
    }

    public void validate() throws CarDataNotValidException {
        final String regExp = "^[a-zA-Z]*$";
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
