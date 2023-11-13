package ru.tyulenev.cars_garage_distributor.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tyulenev.cars_garage_distributor.exceptions.responce.ExceptionResponse;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler()
    public ResponseEntity<ExceptionResponse> handleException(CarDataException e) {
            ExceptionResponse er = new ExceptionResponse(e.toString());
            return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }
}
