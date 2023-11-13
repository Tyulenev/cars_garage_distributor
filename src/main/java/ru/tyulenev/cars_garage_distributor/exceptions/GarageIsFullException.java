package ru.tyulenev.cars_garage_distributor.exceptions;

public class GarageIsFullException extends  CarDataException{
    public GarageIsFullException(String message) {
        super(message);
    }
}
