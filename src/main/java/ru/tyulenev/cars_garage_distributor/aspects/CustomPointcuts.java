package ru.tyulenev.cars_garage_distributor.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CustomPointcuts {
    @Pointcut("execution(public * addNewCar(*))")
    public void addCarMethod() {}
}
