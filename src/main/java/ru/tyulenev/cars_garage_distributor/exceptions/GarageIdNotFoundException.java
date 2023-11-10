package ru.tyulenev.cars_garage_distributor.exceptions;

public class GarageIdNotFoundException extends  Exception{
    public GarageIdNotFoundException(String message) {
        super(message);
    }
}
