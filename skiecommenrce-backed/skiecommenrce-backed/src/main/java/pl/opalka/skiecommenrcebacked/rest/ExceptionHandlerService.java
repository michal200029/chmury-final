package pl.opalka.skiecommenrcebacked.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pl.opalka.skiecommenrcebacked.rest.expception.QuantityException;


@ControllerAdvice
public class ExceptionHandlerService {

    @ExceptionHandler(QuantityException.class )
    public final ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}
