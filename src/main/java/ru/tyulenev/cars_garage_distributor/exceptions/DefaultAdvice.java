package ru.tyulenev.cars_garage_distributor.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler({CarDataNotValidException.class})
    public ResponseEntity<ExceptionResponse> handleException(CarDataNotValidException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
