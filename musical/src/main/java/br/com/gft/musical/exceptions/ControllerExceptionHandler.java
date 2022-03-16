package br.com.gft.musical.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler( { NotFoundException.class } )
    public ResponseEntity<Error> handleNotFoundException(NotFoundException ex, WebRequest request){
        Error error = new Error("Entidade não encontrada", ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity(error, new HttpHeaders(), error.getStatus());
    }

//    @ExceptionHandler( { EmptyResultDataAccessException.class } )
//    public ResponseEntity<Error> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
//        Error error = new Error("Entidade não existente", ex.getMessage(), HttpStatus.NOT_FOUND);
//        return new ResponseEntity(error, new HttpHeaders(), error.getStatus());
//    }
}
