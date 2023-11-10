package ru.tyulenev.cars_garage_distributor.exceptions;

public class CarDataNotValidException extends  Exception{
    public CarDataNotValidException(String message) {
        super(message);
    }
}
