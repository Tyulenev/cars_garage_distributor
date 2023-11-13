package ru.tyulenev.cars_garage_distributor.exceptions;

public class GarageIdNotFoundException extends  CarDataException{
    public GarageIdNotFoundException(String message) {
        super(message);
    }
}
